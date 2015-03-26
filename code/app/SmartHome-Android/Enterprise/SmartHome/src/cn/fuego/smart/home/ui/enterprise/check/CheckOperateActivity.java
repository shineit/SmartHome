package cn.fuego.smart.home.ui.enterprise.check;



import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.common.upload.MispUploadImgActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.misp.ui.util.StrUtil;
import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.service.CheckLogCache;
import cn.fuego.smart.home.webservice.up.model.DeleteImgByNameReq;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.system.email.Email.ShareParams;

public class CheckOperateActivity extends MispBaseActivtiy implements OnCheckedChangeListener, PlatformActionListener, Callback
{

	private View abnormalView;
	private CheckLogJson checkLog;
	private TextView abnormal_desp;
	private ImageView abnormal_img;
	private static final int MSG_TOAST = 1;
	private static final int MSG_ACTION_CCALLBACK = 2;
	private static final int MSG_CANCEL_NOTIFY = 3;
	
	private String picPath;//图片本地地址
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.activity_check_operate);
		this.activityRes.setName("日常巡检");
		this.activityRes.getButtonIDList().add(R.id.check_operate_upload_btn);
		this.activityRes.getButtonIDList().add(R.id.check_operate_email);
		checkLog = (CheckLogJson) this.getIntent().getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		abnormalView=findViewById(R.id.check_operate_desp);

		initData();
		ShareSDK.initSDK(this);//分享接口初始化
 
	}

	//初始化数据
	private void initData()
	{
		RadioButton unset_btn= (RadioButton) findViewById(R.id.radio_btn_unset);
		RadioButton normal_btn= (RadioButton) findViewById(R.id.radio_btn_normal);
		RadioButton abnormal_btn= (RadioButton) findViewById(R.id.radio_btn_abnormal);
		
		switch(CheckResultEnum.getEnumByInt(checkLog.getCheckResult()))
		{
		case NONE_SET:
			unset_btn.setChecked(true);
			abnormalView.setVisibility(View.INVISIBLE);
			break;
		case NORMAL:
			normal_btn.setChecked(true);
			abnormalView.setVisibility(View.INVISIBLE);
			break;
		case ABNORMAL:
			abnormal_btn.setChecked(true);
			abnormalView.setVisibility(View.VISIBLE);
			break;
			
		}
		abnormal_desp = (TextView) findViewById(R.id.check_operate_abnormal_desp);
		abnormal_desp.setText(StrUtil.noNullStr(checkLog.getAbnormalDesp()));
		
		RadioGroup group = (RadioGroup) findViewById(R.id.check_operate_radiogroup);
		group.setOnCheckedChangeListener(this);
		
		abnormal_img= (ImageView) findViewById(R.id.check_operate_img);
		if(!ValidatorUtil.isEmpty(checkLog.getAbnormalPic()))
		{
			loadImag(checkLog.getAbnormalPic());
		}
		
		
	}

	@Override
	public void saveOnClick(View v)
	{
		Intent intent = new Intent();
        //以下设置flag 有作用
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		CheckOperateActivity.this.setResult(IntentCodeConst.RESULT_CODE,intent);
		checkLog.setAbnormalDesp(abnormal_desp.getText().toString().trim());
	 
		CheckLogCache.getInstance().update(checkLog);
		this.finish();
		
	}


	@Override
	public void backOnClick()
	{
		clearCache();
		super.backOnClick();
	}

	@Override
	public void onClick(View v)
	{
		if(v.getId()==R.id.check_operate_email)
		{
			StringBuffer sbTitle = new StringBuffer();
			sbTitle.append(checkLog.getCheckItem());
			sbTitle.append(CheckResultEnum.getEnumByInt(checkLog.getCheckResult()).getStrValue());
/*			StringBuffer sbContent = new StringBuffer();
			sbContent.append("所属系统：");
			sbContent.append(checkLog.getCheckSys());
			sbContent.append("\r\n");			
			sbContent.append(abnormal_desp.getText().toString().trim());*/
			//以上不能进行换行
			String contents=new String("所属系统："+checkLog.getCheckSys()+"\n"+abnormal_desp.getText().toString().trim());
			sendEmail(sbTitle.toString(),contents,checkLog.getAbnormalPic());
		}
		if(v.getId()==R.id.check_operate_upload_btn)
		{
			uploadImg();
		}
		
	}

	/**
	 * 邮件分享功能
	 * @param title
	 * @param content
	 * @param abnormalPic
	 */
	private void sendEmail(String title,String content, String abnormalPic)
	{
		ShareParams sp = new ShareParams();
		sp.setTitle(title);
		sp.setText(content);
		if(!ValidatorUtil.isEmpty(picPath))
		{
			sp.setImagePath(picPath);
		}

		Platform email = ShareSDK.getPlatform(Email.NAME);
		email.setPlatformActionListener(this); // 设置分享事件回调
		// 执行图文分享
		email.share(sp);
		
	}

	private void uploadImg()
	{
		
		MispUploadImgActivity.jump(this, 1);
	}

	/**
	 * 切换时清除缓存
	 */
	private void clearCache()
	{
		checkLog.setAbnormalDesp("");
		abnormal_desp.setText("");
		if(!ValidatorUtil.isEmpty(checkLog.getAbnormalPic()))
		{
			needClearImg();
		}
		

	}
	/**
	 * 切换radio时删除服务器图片
	 */
	private void deleteImag(String imgUrl)
	{
		DeleteImgByNameReq req = new DeleteImgByNameReq();
		req.setUserID(AppCache.getInstance().getUser().getUserID());
		req.setImgName(imgUrl);
	
		WebServiceContext.getInstance().getCheckManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					checkLog.setAbnormalPic("");
					abnormal_img.setImageResource(R.drawable.load_image_failed);
				}
				else
				{
					showMessage(message);
				}
			}
		}).deleteImgByName(req);
		
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		clearCache();
		switch(checkedId)
		{
			case R.id.radio_btn_normal:
				abnormalView.setVisibility(View.INVISIBLE);
				checkLog.setCheckResult(CheckResultEnum.NORMAL.getIntValue());
				break;
			case R.id.radio_btn_abnormal:
				abnormalView.setVisibility(View.VISIBLE);
				checkLog.setCheckResult(CheckResultEnum.ABNORMAL.getIntValue());

				break;
			case R.id.radio_btn_unset:
				abnormalView.setVisibility(View.INVISIBLE);
				checkLog.setCheckResult(CheckResultEnum.NONE_SET.getIntValue());

				break;
			default:break;
		}
		
		
	}

	
	private void needClearImg()
	{
        // 创建退出对话框  
        AlertDialog isExit = new AlertDialog.Builder(this).create();  
        // 设置对话框标题  
        isExit.setTitle("系统提示");  
        // 设置对话框消息  
        isExit.setMessage("该操作将清除图片资源？");  
        // 添加选择按钮并注册监听  
        isExit.setButton("确定", listener);  
        isExit.setButton2("取消", listener);  
        // 显示对话框  
        isExit.show(); 
		
	}
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
            	deleteImag(checkLog.getAbnormalPic());
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    };

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(null!=data)
		{
			MispBaseRspJson rsp = (MispBaseRspJson) data.getSerializableExtra(RETURN_DATA);
			if(null!=rsp)
			{
				checkLog.setAbnormalPic((String)rsp.getObj());
				
			}
			picPath = data.getStringExtra("picPath");
			LoadImageUtil.getInstance().loadImage(abnormal_img, "file://"+picPath);
		}
	
	}
	
	private void loadImag(String picName)
	{
		LoadImageUtil.getInstance().loadImage(abnormal_img, MemoryCache.getImageUrl()+picName);
		
	}

	/**
	 * 以下属于分享回调函数
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ShareSDK.stopSDK(this);
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
							Toast.makeText(CheckOperateActivity.this, text, Toast.LENGTH_SHORT).show();
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
