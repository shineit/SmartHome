package cn.fuego.smart.home.ui.enterprise.check;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.service.CheckLogCache;
import cn.fuego.smart.home.webservice.up.model.CreateCheckLogReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class CheckActivity extends MispListActivity<CheckLogJson>
{

	private Window window;
	private PopupWindow popupWindow=null; 
	private View view;
	
	private CompanyJson company;
	@Override
	public void initRes()
	{
		this.activityRes.setName("日常巡检");

		this.activityRes.setAvtivityView(R.layout.activity_check);
		
		this.listViewRes.setListView(R.id.check_list);	
		this.listViewRes.setListItemView(R.layout.item_check);
		this.listViewRes.setNoResult(false);
		this.listViewRes.setClickActivityClass(CheckOperateActivity.class);
		
		this.activityRes.getButtonIDList().add(R.id.check_submit_btn);
				
		Intent intent = this.getIntent();
		company = (CompanyJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		getCheckItem();

		
		
	}
	


	private void getCheckItem()
	{
		GetCheckItemByIDReq req = new GetCheckItemByIDReq();
		req.setCompanyID(company.getCompanyID());
		WebServiceContext.getInstance().getCheckManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				if (message.isSuccess())
				{
					GetCheckItemByIDRsp rsp = (GetCheckItemByIDRsp) message.getMessage().obj;
					CheckLogCache.getInstance().init(company.getCompanyID(),rsp.getCheckItemList());
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
		//super.onActivityResult(requestCode, resultCode, data);
		//this.refreshList(newDataList);
		
		this.repaint();
	}
 

	private void loadLocaPic(View parent) 
	{

		if (popupWindow == null)
		{		
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
			view = layoutInflater.inflate(R.layout.pop_check_operate, null);

			// 创建一个PopuWidow对象
			 popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			// 使其聚集
			popupWindow.setFocusable(true);
			// 设置允许在外点击消失
			popupWindow.setOutsideTouchable(true);

			// 实例化一个ColorDrawable颜色为半透明
			ColorDrawable dw = new ColorDrawable(0x90000000);
			popupWindow.setBackgroundDrawable(dw);
			popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);         
			popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			 
		}


        //设置背景变暗
        WindowManager.LayoutParams lp=window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp); 
	
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

		popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);//在屏幕居中，无偏移
		
		popupWindow.setOnDismissListener(new OnDismissListener()
		{
			
			@Override
			public void onDismiss()
			{
				WindowManager.LayoutParams lp=window.getAttributes();
			    lp.alpha = 1f;
			    window.setAttributes(lp);
			    popupWindow=null;
			}
		});
		
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
