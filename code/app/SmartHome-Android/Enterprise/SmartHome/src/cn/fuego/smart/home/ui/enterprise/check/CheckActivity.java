package cn.fuego.smart.home.ui.enterprise.check;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.service.CheckLogCache;
import cn.fuego.smart.home.webservice.up.model.CreateCheckLogReq;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckItemByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckItemByIDRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class CheckActivity extends MispListActivity<CheckLogJson>
{


	private int companyID; //默认所有项目，后期考虑实际ID
	private CompanyJson company;
	@Override
	public void initRes()
	{
		this.waitDailog.show();
		this.activityRes.setName("日常巡检");
		this.activityRes.setAvtivityView(R.layout.activity_check);
		
		this.listViewRes.setListView(R.id.check_list);	
		this.listViewRes.setListItemView(R.layout.item_check);
		this.listViewRes.setNoResult(false);
		this.listViewRes.setClickActivityClass(CheckOperateActivity.class);
		
		this.activityRes.getButtonIDList().add(R.id.check_submit_btn);
				
		Intent intent = this.getIntent();
		company = (CompanyJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		companyID= company.getCompanyID();
		
		getCheckItem();

		
		
	}
	


	private void getCheckItem()
	{
		GetCheckItemByIDReq req = new GetCheckItemByIDReq();
		//req.setCompanyID(companyID);
		//默认共同模板
		req.setCompanyID(0);
		WebServiceContext.getInstance().getCheckManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				waitDailog.dismiss();
				if (message.isSuccess())
				{
					GetCheckItemByIDRsp rsp = (GetCheckItemByIDRsp) message.getMessage().obj;
					CheckLogCache.getInstance().init(companyID,company.getCompanyName(),rsp.getCheckItemList());
					
					setDataList(CheckLogCache.getInstance().getCheckLogList());
					repaint();
				}
			}
			
		
		}).getItemByID(req);
	}
	

	@Override
	public View getListItemView(View view, CheckLogJson item)
	{
		TextView txt_title= (TextView) view.findViewById(R.id.item_check_title);
		txt_title.setText(item.getCheckItem());		
		TextView txt_content= (TextView) view.findViewById(R.id.item_check_content);
		txt_content.setText(item.getCheckSys());
		TextView txt_result= (TextView) view.findViewById(R.id.item_check_result);
		txt_result.setText(CheckResultEnum.getEnumByInt(item.getCheckResult()).getStrValue());
		ImageView icon_result= (ImageView) view.findViewById(R.id.item_check_img);
		icon_result.setVisibility(View.INVISIBLE);
		return view;
	}
 
	
	
	

	@Override
	public void onClick(View v)
	{
		submitCheckLog();
	}



	private void submitCheckLog()
	{

		if(!CheckLogCache.getInstance().isChecked())
		{
			showToast(this, "请至少检查一项纪录");
			return;
		}
		this.waitDailog.show();
		CreateCheckLogReq req = new CreateCheckLogReq();
		
		req.setCheckLogList(CheckLogCache.getInstance().getCheckLogList());
		WebServiceContext.getInstance().getCheckManageRest(new MispHttpHandler()
		{
			@Override
			public void handle(MispHttpMessage message)
			{
				if (message.isSuccess())
				{
					//CreateCheckLogRsp rsp = (CreateCheckLogRsp) message.getMessage().obj;
					CheckActivity.this.finish();
				}
				showMessage(message);
				waitDailog.dismiss();
			}
		}).createCheckLog(req);
		
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub

		this.repaint();
	}
 


	
	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<CheckLogJson> loadListRecv(Object obj)
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	protected void onDestroy()
	{
/*		Intent intent=new Intent();
        intent.putExtra(IntentCodeConst.HOME_REFRESH, 2);
        intent.setAction("android.intent.action.bageNotify");//action与接收器相同
        sendBroadcast(intent);*/
        AppCache.getInstance().setHomeRefreshFlag(3);
		super.onDestroy();
	}	

}
