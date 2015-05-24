package cn.fuego.smart.home.ui.enterprise.alarm;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.StrUtil;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.constant.AttributeConst;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class AlarmHistoryActivity extends MispListActivity<FireAlarmJson>
{

	private String companyID;
	private String alarmKind;
	private int defPageSize=50;
	private int curPage=1;
	
	private ImageView safeImg;
	private View listView;
	
	private CompanyJson company;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		listView = findViewById(R.id.alarm_history_list);
		safeImg = (ImageView) findViewById(R.id.alarm_history_safeinfo);
	}
	
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.activity_alarm_history);
		this.activityRes.setName("历史记录");
		
		this.listViewRes.setListView(R.id.alarm_history_list);
		this.listViewRes.setListItemView(R.layout.fire_alarm_item);
		
		this.listViewRes.setClickActivityClass(FireAlarmViewActivity.class);
		companyID=this.getIntent().getStringExtra(IntentCodeConst.COMPANY_ID);
		alarmKind=this.getIntent().getStringExtra(IntentCodeConst.ALARM_KIND);
		
		company =(CompanyJson) this.getIntent().getSerializableExtra(IntentCodeConst.COMPANY_INFO);
	}
	
	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,	FireAlarmJson item)
	{
		if(item.getAlarmTypeName().equals(AlarmTypeEnum.OFF_LINE.getStrValue())||
				item.getAlarmTypeName().equals(AlarmTypeEnum.ONLINE.getStrValue()))
		{
			return;
		}
		else
		{
			Intent i = new Intent();
			i.setClass(this, FireAlarmViewActivity.class);
			Bundle mBundle= new Bundle();
		    mBundle.putSerializable(ListViewResInfo.SELECT_ITEM,item);
			mBundle.putSerializable(IntentCodeConst.COMPANY_INFO, company);
	        i.putExtras(mBundle);
	        this.startActivity(i);
		}

	}

	@Override
	public View getListItemView(View view, FireAlarmJson item)
	{
		ImageView item_icon= (ImageView) view.findViewById(R.id.item_alarm_icon);
		if(item.getAlarmKind()==AlarmKindEnum.ALARM.getIntValue())
		{
			item_icon.setImageResource(R.drawable.fire);
		}
		else
		{
			item_icon.setImageResource(R.drawable.prealarm);
		}
		StringBuffer sbTitle= new StringBuffer();
		sbTitle.append(StrUtil.noNullStr(item.getSensorTypeName()));
		sbTitle.append("发生");
		sbTitle.append(item.getAlarmTypeName());
		TextView item_title = (TextView) view.findViewById(R.id.item_alarm_title);
		item_title.setText(sbTitle.toString());
		TextView item_content = (TextView) view.findViewById(R.id.item_alarm_content);
		if(item.getAlarmTypeName().equals(AlarmTypeEnum.OFF_LINE.getStrValue())||
				item.getAlarmTypeName().equals(AlarmTypeEnum.ONLINE.getStrValue()))
		{
			item_content.setText(DateUtil.getStrTime(item.getAlarmTime()));
		}
		else
		{
			item_content.setText(StrUtil.noNullStr(item.getLocationDesp()));
		}
		
		
		return view;
	}

	@Override
	public void loadSendList()
	{
		if(ValidatorUtil.isEmpty(companyID)||ValidatorUtil.isEmpty(alarmKind))
		{
			this.showToast(this, "暂时查询不到结果！");
		}
		else
		{
			this.waitDailog.show();			
			GetFireAlarmByIDReq req= new GetFireAlarmByIDReq();
	        req.setCompanyID(companyID);
	    	List<AttributeJson> attrList = new ArrayList<AttributeJson>();
	    	AttributeJson attr = new AttributeJson();
			attr.setAttrName(AttributeConst.ALARM_KIND);
			attr.setAttrValue(alarmKind);
			attrList.add(attr);
			PageJson page = new PageJson();			
			page.setPageSize(defPageSize);
			page.setCurrentPage(curPage);
			req.setPage(page);
			req.setFilterList(attrList);
			WebServiceContext.getInstance().getSensorManageRest(this).getFireAlarmHistory(req);
		}	
	}

	@Override
	public List<FireAlarmJson> loadListRecv(Object obj)
	{
		this.waitDailog.dismiss();
		GetFireAlarmByIDRsp rsp = (GetFireAlarmByIDRsp) obj;
		if(ValidatorUtil.isEmpty(rsp.getFireAlarmList()))
		{

			safeImg.setVisibility(View.VISIBLE);
			listView.setVisibility(View.GONE);
		}
		return rsp.getFireAlarmList();
	}


	


}
