package cn.fuego.smart.home.ui.enterprise.check;

import java.util.HashMap;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckLogByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckLogByIDRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.system.email.Email.ShareParams;
import cn.sharesdk.wechat.friends.Wechat;

public class CheckLogActivity extends MispListActivity<CheckLogJson> implements PlatformActionListener, Callback
{

	private CompanyJson company;
	
	private Window window;
	private PopupWindow popupWindow=null; 
	private View view;
	
	private static final int MSG_TOAST = 1;
	private static final int MSG_ACTION_CCALLBACK = 2;
	private static final int MSG_CANCEL_NOTIFY = 3;
	@Override
	public void initRes()
	{
		this.waitDailog.show();
		this.activityRes.setAvtivityView(R.layout.activity_check_log);
		this.activityRes.setName("智慧管理");
		//this.activityRes.getButtonIDList().add(R.id.check_log_email_btn);
		this.activityRes.setSaveBtnName("历史查询");
		this.listViewRes.setListView(R.id.check_log_list);
		this.listViewRes.setListItemView(R.layout.item_check);
	
		Intent intent = this.getIntent();
		company = (CompanyJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		
		ShareSDK.initSDK(this);//分享接口初始化
	}

	@Override
	public View getListItemView(View view, CheckLogJson item)
	{
		TextView txt_title= (TextView) view.findViewById(R.id.item_check_title);
		txt_title.setText(item.getCheckItem());		
		TextView txt_content= (TextView) view.findViewById(R.id.item_check_content);
		txt_content.setText(DateUtil.getStrTime(item.getCheckTime()));
		TextView txt_result= (TextView) view.findViewById(R.id.item_check_result);
		txt_result.setText(CheckResultEnum.getEnumByInt(item.getCheckResult()).getStrValue());
		ImageView icon_result= (ImageView) view.findViewById(R.id.item_check_img);
		if(!ValidatorUtil.isEmpty(item.getAbnormalPic()))
		{
			icon_result.setVisibility(View.VISIBLE);
		}
		else
		{
			icon_result.setVisibility(View.INVISIBLE);
		}
		
		return view;
	}

	@Override
	public void saveOnClick(View v)
	{
		Intent i = new Intent();
		i.setClass(this, CheckLogHistroyActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle mBundle= new Bundle();
		mBundle.putSerializable(IntentCodeConst.COMPANY_INFO, company);
        i.putExtras(mBundle);
		this.startActivity(i);
	}

	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,CheckLogJson item)
	{
	
		String picUrl= MemoryCache.getImageUrl()+item.getAbnormalPic();
		showDetail(view, item, picUrl);
	}



	private void showDetail(View parent, CheckLogJson item,final String picUrl)
	{
		
		final String title=item.getCheckItem()+CheckResultEnum.getEnumByInt(item.getCheckResult()).getStrValue();
		final String content =item.getAbnormalDesp();
		if (popupWindow == null)
		{		
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
			view = layoutInflater.inflate(R.layout.pop_window_with_img, null);

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
			
			TextView txt_title= (TextView) view.findViewById(R.id.pop_window_title);
			//title= item.getCheckItem()+CheckResultEnum.getEnumByInt(item.getCheckResult()).getStrValue();
			txt_title.setText(title);
			TextView txt_content = (TextView) view.findViewById(R.id.pop_window_content);
			//content = item.getAbnormalDesp();
			txt_content.setText(content);

			TextView txt_checker = (TextView) view.findViewById(R.id.check_log_checker);
			txt_checker.setText(item.getChecker());

			TextView txt_checkDate = (TextView) view.findViewById(R.id.check_log_date);
			txt_checkDate.setText(DateUtil.getStrTime(item.getCheckTime()));
			
			ImageView img_area= (ImageView) view.findViewById(R.id.pop_window_imgarea);
			if(!ValidatorUtil.isEmpty(picUrl))
			{
				LoadImageUtil.getInstance().loadImage(img_area, picUrl);
			}
			
		}

		window=CheckLogActivity.this.getWindow();
        //设置背景变暗
        WindowManager.LayoutParams lp=window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp); 
		popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);//在屏幕居中，无偏移
		
		Button email_btn=(Button) view.findViewById(R.id.check_log_email_btn);
		email_btn.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//showMessage("start email");
				//sendEmail(title, content, picUrl);
				sendWeChat(title, content, picUrl);
				
				
			}
		});
		
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
		GetCheckLogByIDReq req = new GetCheckLogByIDReq();

		req.setCompanyID(company.getCompanyID());
		//PageJson page = new PageJson();		
		//page.setCurrentPage(curPage);
		//req.setPage(page);
		WebServiceContext.getInstance().getCheckManageRest(this).getCheckLogByID(req);
		
	}

	@Override
	public List<CheckLogJson> loadListRecv(Object obj)
	{
		this.waitDailog.dismiss();
		GetCheckLogByIDRsp rsp = (GetCheckLogByIDRsp) obj;
		if(ValidatorUtil.isEmpty(rsp.getCheckLogList()))
		{
			showToast(this, "当前无不良巡检记录！");
		}
		return rsp.getCheckLogList();
	}
	/**
	 * 邮件分享功能
	 * @param title
	 * @param content
	 * @param abnormalPic
	 */
	private void sendEmail(String title,String content, String abnormalPic)
	{
		//showMessage("ImageUrl-----"+MemoryCache.getImageUrl()+abnormalPic);
		ShareParams sp = new ShareParams();
		sp.setTitle(title);
		sp.setText(content);
/*		if(!ValidatorUtil.isEmpty(picPath))
		{
			sp.setImagePath(picPath);
		}*/
		//sp.setImageUrl("http://www.someserver.com/测试图片网络地址.jpg");
		sp.setImageUrl(abnormalPic);
		Platform email = ShareSDK.getPlatform(Email.NAME);
		email.setPlatformActionListener(this); // 设置分享事件回调
		// 执行图文分享
		email.share(sp);
		
	}
	/**
	 * 微信分享功能
	 * @param title
	 * @param content
	 * @param abnormalPic
	 */
	private void sendWeChat(String title,String content, String abnormalPic)
	{
		ShareParams sp = new ShareParams();
		Platform weChat= ShareSDK.getPlatform(Wechat.NAME);
		sp.setTitle(title);
		sp.setText(content);
		sp.setImageUrl(abnormalPic);
		weChat.setPlatformActionListener(this);
		//sp.setShareType(SSPublishContentMediaTypeNews);
		weChat.share(sp);
	}
	/**
	 * 以下属于分享回调函数
	 */

	@Override
	protected void onDestroy()
	{
		ShareSDK.stopSDK(this);
/*		Intent intent=new Intent();
        intent.putExtra(IntentCodeConst.HOME_REFRESH, 2);
        intent.setAction("android.intent.action.bageNotify");//action与接收器相同
        sendBroadcast(intent);*/
		AppCache.getInstance().setHomeRefreshFlag(3);
		super.onDestroy();
	}
	@Override
	public void onCancel(Platform platform, int action) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 3;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);
	}

	@Override
	public void onComplete(Platform platform, int action, HashMap<String, Object> arg2) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 1;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);
	}

	@Override
	public void onError(Platform platform, int action, Throwable t) {
		t.printStackTrace();
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 2;
		msg.arg2 = action;
		msg.obj = t;
		UIHandler.sendMessage(msg, this);		
	}
	@Override
	public boolean handleMessage(Message msg) {
		switch(msg.what) 
		
		{
		case MSG_TOAST: {
							String text = String.valueOf(msg.obj);
							Toast.makeText(CheckLogActivity.this, text, Toast.LENGTH_SHORT).show();
						}
		break;
		case MSG_ACTION_CCALLBACK: {
			switch (msg.arg1) {
				case 1: { // 成功, successful notification
					showNotification(2000, "分享成功！");
				}
				break;
				case 2: { // 失败, fail notification

					showNotification(2000, "分享失败！");

				}
				break;
				case 3: { // 取消, cancel notification
					showNotification(2000, "分享取消！");
				}
				break;
			}
		}
		break;
		case MSG_CANCEL_NOTIFY: {
			NotificationManager nm = (NotificationManager) msg.obj;
			if (nm != null) {
				nm.cancel(msg.arg1);
			}
		}
		break;
	}
	return false;
	}

	private void showNotification(long cancelTime, String text) {
		try {
			Context app = getApplicationContext();
			NotificationManager nm = (NotificationManager) app
					.getSystemService(Context.NOTIFICATION_SERVICE);
			final int id = Integer.MAX_VALUE / 13 + 1;
			nm.cancel(id);

			long when = System.currentTimeMillis();
			Notification notification = new Notification(R.drawable.ic_launcher, text, when);
			PendingIntent pi = PendingIntent.getActivity(app, 0, new Intent(), 0);
			notification.setLatestEventInfo(app, "sharesdk test", text, pi);
			notification.flags = Notification.FLAG_AUTO_CANCEL;
			nm.notify(id, notification);

			if (cancelTime > 0) {
				Message msg = new Message();
				msg.what = MSG_CANCEL_NOTIFY;
				msg.obj = nm;
				msg.arg1 = id;
				UIHandler.sendMessageDelayed(msg, cancelTime, this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
