package cn.fuego.smart.home.ui.enterprise.company;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.webservice.up.model.GetCompanyListReq;
import cn.fuego.smart.home.webservice.up.model.GetCompanyListRsp;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class CompanyListActivity extends MispListActivity<CompanyJson>
{
	
	@Override
	public void initRes() 
	{
		this.waitDailog.show();
		this.activityRes.setAvtivityView(R.layout.activity_company_list);
		this.activityRes.setName("公司列表");
		
		this.listViewRes.setListView(R.id.company_list_content);
		this.listViewRes.setListItemView(R.layout.misp_list_item_btntype);
		
		Class clazz = (Class) this.getIntent().getSerializableExtra(IntentCodeConst.JUMP_CLASS_NAME);
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
		return view;
	}

	@Override
	public void loadSendList()
	{
		GetCompanyListReq req = new GetCompanyListReq();
        req.setUserID(AppCache.getInstance().getUser().getUserID());

		WebServiceContext.getInstance().getUserManageRest(this).getCompanyList(req);
		
	}

	@Override
	public List<CompanyJson> loadListRecv(Object obj) 
	{
		this.waitDailog.dismiss();
		GetCompanyListRsp rsp = (GetCompanyListRsp) obj;	
		return rsp.getCompanyList();
	}

	

}
