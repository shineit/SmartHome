package cn.fuego.smart.home.ui;

import java.util.List;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.common.about.AboutUsActivity;
import cn.fuego.smart.home.ui.common.knowledge.CommonSenseActivity;
import cn.fuego.smart.home.ui.common.mall.ProductMallActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.DeviceStatusActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.FireAlarmActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckLogActivity;
import cn.fuego.smart.home.ui.enterprise.company.CompanyListActivity;
import cn.fuego.smart.home.ui.enterprise.company.CompanyViewActivity;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

import com.readystatesoftware.viewbadger.BadgeView;

public class HomeActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private BadgeView alarmBageView,statusBageView;
	private Button alarm_btn,status_btn;
	private int alarmEnter=0,statusEnter=0;
	@Override
	public void initRes() 
	{
		this.activityRes.setAvtivityView(R.layout.activity_home);
		
		this.activityRes.getButtonIDList().add(R.id.home_menu_alarm);
		this.activityRes.getButtonIDList().add(R.id.home_menu_status);
		this.activityRes.getButtonIDList().add(R.id.home_menu_check);
		this.activityRes.getButtonIDList().add(R.id.home_menu_account);
		this.activityRes.getButtonIDList().add(R.id.home_menu_manage);
		this.activityRes.getButtonIDList().add(R.id.home_menu_knowledge);
		this.activityRes.getButtonIDList().add(R.id.home_menu_mall);
		this.activityRes.getButtonIDList().add(R.id.home_about_us_btn);
	} 
	public static void jump(Context context)
	{
 
 		Intent intent = new Intent();
  		intent.setClass(context, HomeActivity.class);
 		context.startActivity(intent);
  	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
    	alarm_btn = (Button) findViewById(R.id.home_menu_alarm);
    	status_btn = (Button) findViewById(R.id.home_menu_status);
    	showAlarmBadge();
        //注册广播，接收service中启动的线程发送过来的信息，同时更新UI  
        IntentFilter filter = new IntentFilter("android.intent.action.bageNotify");  
        this.registerReceiver(new HomeReceiver(), filter);  


	}
	private void showAlarmBadge()
	{

		GetFireAlarmByIDReq req= new GetFireAlarmByIDReq();
		req.setUserID(AppCache.getInstance().getUser().getUserID());
		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage message)
			{
				
				if(message.isSuccess())
				{
			    	GetFireAlarmByIDRsp rsp = (GetFireAlarmByIDRsp) message.getMessage().obj;
					if(!ValidatorUtil.isEmpty(rsp.getFireAlarmList()))
			    	{

				    	int alarmCount=0,statusCount=0;
				    	List<FireAlarmJson> tempList =  rsp.getFireAlarmList();
				    	for(FireAlarmJson json:tempList)
				    	{
				    		if(json.getAlarmKind()==AlarmKindEnum.ALARM.getIntValue())
				    		{
				    			alarmCount++;
				    		}
				    		if(json.getAlarmKind()==AlarmKindEnum.STATUS.getIntValue())
				    		{
				    			statusCount++;
				    		}
				    	}

				    	if(alarmCount!=0)
				    	{
				    		alarmEnter=1;
				    		alarmBageView = new BadgeView(HomeActivity.this, alarm_btn);
							if(alarmCount>99)
							{
								alarmBageView.setText("99+");
							}
							else
							{
								alarmBageView.setText(String.valueOf(alarmCount));
							}
							
					    	alarmBageView.setTextColor(Color.RED);
					    	alarmBageView.setBadgeBackgroundColor(Color.YELLOW);
					    	alarmBageView.setTextSize(15);
					    	alarmBageView.toggle();
				    	}
				    	if(statusCount!=0)
				    	{
				    		statusEnter=1;
				    		statusBageView = new BadgeView(HomeActivity.this, status_btn);
							if(statusCount>99)
							{
								statusBageView.setText("99+");
							}
							else
							{
								statusBageView.setText(String.valueOf(statusCount));
							}
				    		
				    		statusBageView.setTextColor(Color.RED);
				    		statusBageView.setBadgeBackgroundColor(Color.YELLOW);
				    		statusBageView.setTextSize(15);
				    		statusBageView.toggle();
				    	}
			    	}
					

				}
				else
				{
					showMessage(message);
				}
				
				
			}
		}).getFireAlarm(req);

		
	}
	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{

		case R.id.home_menu_alarm:
			if(alarmEnter==1)
			{
				alarmBageView.toggle();
				alarmEnter=0;
			}
			
			CompanyListActivity.jump(this, FireAlarmActivity.class);
			break;
		case R.id.home_menu_status:
			if(statusEnter==1)
			{
				statusBageView.toggle();
				statusEnter=0;
			}
			CompanyListActivity.jump(this, DeviceStatusActivity.class);
			break;
		case R.id.home_menu_check:	
			CompanyListActivity.jump(this, CheckActivity.class);
			break;			
		case R.id.home_menu_account: 
			CompanyListActivity.jump(this, CompanyViewActivity.class);
			break;
		case R.id.home_menu_manage:
			CompanyListActivity.jump(this, CheckLogActivity.class);
			break;
			
		case R.id.home_menu_knowledge:
			jumpActivity(CommonSenseActivity.class);
			break;
		case R.id.home_menu_mall: 
			jumpActivity(ProductMallActivity.class);
			break;
		case R.id.home_about_us_btn:
			jumpActivity(AboutUsActivity.class);
			break;
		default:break;
		}
		
	}

	private void jumpActivity(Class jumpActivityClass)
	{
		Intent i= new Intent();
		i.setClass(HomeActivity.this, jumpActivityClass);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        this.startActivity(i);
		//this.finish();
		
	}

	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            // 创建退出对话框  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // 设置对话框标题  
            isExit.setTitle("系统提示");  
            // 设置对话框消息  
            isExit.setMessage("确定要退出吗?");  
            // 添加选择按钮并注册监听  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            // 显示对话框  
            isExit.show();  
  
        }  
          
        return false;  
          
    }  
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
                finish();  
                //int nPid = android.os.Process.myPid();  
                //android.os.Process.killProcess(nPid);  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    };

    class HomeReceiver extends BroadcastReceiver
    {

		@Override
		public void onReceive(Context context, Intent intent)
		{
			
			Boolean refresh=intent.getBooleanExtra("refresh", false);
			if(refresh)
			{
				//更新数字提醒
				showAlarmBadge();
			}
		}
	};
     
	
}
