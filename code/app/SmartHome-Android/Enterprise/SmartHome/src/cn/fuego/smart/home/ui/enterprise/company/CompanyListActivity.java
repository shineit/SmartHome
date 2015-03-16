package cn.fuego.smart.home.ui.enterprise.company;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.webservice.up.model.GetCompanyListReq;
import cn.fuego.smart.home.webservice.up.model.GetCompanyListRsp;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class CompanyListActivity extends MispListActivity<CompanyJson>
{
	private static final String JUMP_CLASS_NAME = "jumpClass";

	
	@Override
	public void initRes() 
	{
		this.activityRes.setAvtivityView(R.layout.activity_company_list);
		
		this.listViewRes.setListView(R.id.company_list_content);
		
		this.listViewRes.setListItemView(R.layout.misp_list_item_btntype);
		
		Class clazz = (Class) this.getIntent().getSerializableExtra(JUMP_CLASS_NAME);
		this.listViewRes.setClickActivityClass(clazz);
		
		this.activityRes.setName("基本信息");
		
	}
	public static void jump(Context context,Class jumpClass)
	{
		Intent intent = new Intent(context,CompanyListActivity.class);
		intent.putExtra(JUMP_CLASS_NAME, jumpClass);

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
        req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		req.setToken(MemoryCache.getToken());
		WebServiceContext.getInstance().getUserManageRest(this).getCompanyList(req);
		
	}

	@Override
	public List<CompanyJson> loadListRecv(Object obj) 
	{
		GetCompanyListRsp rsp = (GetCompanyListRsp) obj;
		
		return rsp.getCompanyList();
	}

	

}
