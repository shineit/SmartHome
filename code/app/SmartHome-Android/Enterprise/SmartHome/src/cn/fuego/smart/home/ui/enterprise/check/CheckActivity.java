package cn.fuego.smart.home.ui.enterprise.check;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.service.CheckLogCache;
import cn.fuego.smart.home.webservice.up.model.CreateCheckLogReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class CheckActivity extends MispListActivity<CheckLogJson>
{


	private int companyID=0; //默认所有项目，后期考虑实际ID
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
				
		//Intent intent = this.getIntent();
		//CompanyJson company = (CompanyJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		//companyID= company.getCompanyID();
		
		getCheckItem();

		
		
	}
	


	private void getCheckItem()
	{
		GetCheckItemByIDReq req = new GetCheckItemByIDReq();
		req.setCompanyID(companyID);
		WebServiceContext.getInstance().getCheckManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				waitDailog.dismiss();
				if (message.isSuccess())
				{
					GetCheckItemByIDRsp rsp = (GetCheckItemByIDRsp) message.getMessage().obj;
					CheckLogCache.getInstance().init(companyID,rsp.getCheckItemList());
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

}
