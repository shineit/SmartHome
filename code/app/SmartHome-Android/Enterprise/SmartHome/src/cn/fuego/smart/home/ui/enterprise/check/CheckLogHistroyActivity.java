package cn.fuego.smart.home.ui.enterprise.check;

import android.content.Intent;
import android.os.Bundle;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckLogByIDReq;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;
import cn.sharesdk.framework.ShareSDK;

public class CheckLogHistroyActivity extends CheckLogActivity
{

	private CompanyJson company;
	private int defPageSize=50;
	private int curPage=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initRes()
	{
		
		this.waitDailog.show();
		this.activityRes.setAvtivityView(R.layout.activity_check_log_histroy);
		this.activityRes.setName("巡检记录");
		this.listViewRes.setListView(R.id.check_history_list);
		this.listViewRes.setListItemView(R.layout.item_check);	
		Intent intent = this.getIntent();
		company = (CompanyJson) intent.getSerializableExtra(IntentCodeConst.COMPANY_INFO);
		
		ShareSDK.initSDK(this);//分享接口初始化
	}

	@Override
	public void loadSendList()
	{
		GetCheckLogByIDReq req = new GetCheckLogByIDReq();

		req.setCompanyID(company.getCompanyID());
		PageJson page = new PageJson();			
		page.setPageSize(defPageSize);
		page.setCurrentPage(curPage);
		req.setPage(page);
		WebServiceContext.getInstance().getCheckManageRest(this).getCheckLogHistory(req);

	}
	
	
}
