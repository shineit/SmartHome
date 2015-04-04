package cn.fuego.smart.home.ui.enterprise.company;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.service.BageNumDataCache;
import cn.fuego.smart.home.ui.enterprise.alarm.DeviceStatusActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.FireAlarmActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckLogActivity;
import cn.fuego.smart.home.webservice.up.model.base.BageNumJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCompanyListReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCompanyListRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.readystatesoftware.viewbadger.BadgeView;

public class CompanyListActivity extends MispListActivity<CompanyJson>
{
	
	private  List<BageNumJson> alarmBageList,statusBageList,checkBageList;
	private int bageFlag=0;

	@Override
	public void initRes() 
	{
		
		this.activityRes.setAvtivityView(R.layout.activity_company_list);
		this.activityRes.setName("公司列表");
		
		this.listViewRes.setListView(R.id.company_list_content);
		this.listViewRes.setListItemView(R.layout.misp_list_item_btntype);
		
		Class clazz = (Class) this.getIntent().getSerializableExtra(IntentCodeConst.JUMP_CLASS_NAME);
		if(clazz==FireAlarmActivity.class)
		{
			alarmBageList=BageNumDataCache.getInstance().getAlarmBageList();
			bageFlag=1;
		}
		if(clazz==DeviceStatusActivity.class)
		{
			statusBageList=BageNumDataCache.getInstance().getStatusBageList();
			bageFlag=2;
		}
		if(clazz==CheckLogActivity.class)
		{
			checkBageList=BageNumDataCache.getInstance().getCheckBageList();
			bageFlag=3;
		}
		this.listViewRes.setClickActivityClass(clazz);
		
		
		
	}
	public static void jump(Context context,Class jumpClass)
	{
		Intent intent = new Intent(context,CompanyListActivity.class);
		intent.putExtra(IntentCodeConst.JUMP_CLASS_NAME, jumpClass);

		context.startActivity(intent);
	}
	
	@Override
	public View getListItemView(View view, CompanyJson item) 
	{
		TextView txt_title = (TextView) view.findViewById(R.id.item_btntype_name);
		txt_title.setText(item.getApplyName());
		String bageNum= getNumByID(item.getCompanyID());
		if(!ValidatorUtil.isEmpty(bageNum)&&!bageNum.equals("0"))
		{
			BadgeView bage = new BadgeView(this, txt_title);
			bage.setTextColor(Color.RED);
			bage.setBadgeBackgroundColor(Color.YELLOW);
			bage.setTextSize(15);
			bage.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
			bage.setText(getNumByID(item.getCompanyID()));
			bage.show();
		}

		return view;
	}

	private String getNumByID(int companyID)
	{
		String num=null;
		switch(bageFlag)
		{
		case 1:num=findNum(alarmBageList,companyID);
				break;
		case 2:num=findNum(statusBageList,companyID);
				break;
		case 3:num=findNum(checkBageList,companyID);
				break;
		default:break;
		}
		return num;
	}

	private String findNum(List<BageNumJson> bageList, int companyID)
	{
		String result="0";
		if(!ValidatorUtil.isEmpty(bageList))
		{
			for(int i=0;i<bageList.size();i++)
			{
				if(companyID==bageList.get(i).getCompanyID())
				{
					result=String.valueOf(bageList.get(i).getNum());
					return result;
				}
			}
		}

		return result;
	}

	@Override
	public void loadSendList()
	{
		this.waitDailog.show();
		GetCompanyListReq req = new GetCompanyListReq();
        req.setUserID(AppCache.getInstance().getUser().getUserID());

		WebServiceContext.getInstance().getCompanyManageRest(this).getCompanyList(req);
		
	}

	@Override
	public List<CompanyJson> loadListRecv(Object obj) 
	{
		this.waitDailog.dismiss();
		GetCompanyListRsp rsp = (GetCompanyListRsp) obj;	
		return rsp.getCompanyList();
	}

	

}
