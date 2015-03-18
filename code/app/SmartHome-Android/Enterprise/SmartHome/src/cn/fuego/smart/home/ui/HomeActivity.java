package cn.fuego.smart.home.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.about.AboutUsActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.common.knowledge.CommonSenseActivity;
import cn.fuego.smart.home.ui.common.mall.DeviceMallActivity;
import cn.fuego.smart.home.ui.common.mall.ProductMallActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.FireAlarmActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckActivity;
import cn.fuego.smart.home.ui.enterprise.check.CheckLogActivity;
import cn.fuego.smart.home.ui.enterprise.company.CompanyListActivity;
import cn.fuego.smart.home.ui.enterprise.company.CompanyViewActivity;

public class HomeActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private Boolean isLoadSensor=false;
	private ProgressDialog proDialog;
      
	public static void jump(Context context)
	{
 
 		Intent intent = new Intent();
  		intent.setClass(context, HomeActivity.class);
 		context.startActivity(intent);
  	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ExitApplication.getInstance().addActivity(this);
 
		initView();
		//initSoundPool();
		
	}



	private void initView()
	{
		Button alarm_btn= (Button) findViewById(R.id.home_menu_alarm);
		alarm_btn.setOnClickListener(this);
		Button status_btn= (Button) findViewById(R.id.home_menu_status);
		status_btn.setOnClickListener(this);	
		Button check_btn= (Button) findViewById(R.id.home_menu_check);
		check_btn.setOnClickListener(this);

		Button account_btn= (Button) findViewById(R.id.home_menu_account);
		account_btn.setOnClickListener(this);
		Button manage_btn= (Button) findViewById(R.id.home_menu_manage);
		manage_btn.setOnClickListener(this);
		
		Button knowledge_btn= (Button) findViewById(R.id.home_menu_knowledge);
		knowledge_btn.setOnClickListener(this);
		Button mall_btn= (Button) findViewById(R.id.home_menu_mall);
		mall_btn.setOnClickListener(this);		
		
/*		//MENU 加bage 提醒
		if(MemoryCache.getBageNum()>1)
		{
			BadgeView badge2 = new BadgeView(this, alarm_btn);
	    	badge2.setText(" ! ");
	    	badge2.setTextColor(Color.WHITE);
	    	badge2.setBadgeBackgroundColor(Color.RED);
	    	badge2.setTextSize(15);
	    	badge2.toggle();
		}*/

		
		Button about_btn= (Button) findViewById(R.id.home_about_us_btn);
		about_btn.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{

		case R.id.home_menu_alarm:	
			//jumpActivity(AlarmActivity.class);
			CompanyListActivity.jump(this, FireAlarmActivity.class);
			//jumpActivity(FireAlarmActivity.class);
			break;
		case R.id.home_menu_status:
			CompanyListActivity.jump(this, FireAlarmActivity.class);
			break;
		case R.id.home_menu_check:	
			//jumpActivity(AlarmActivity.class);
			//jumpActivity(CheckActivity.class);
			CompanyListActivity.jump(this, CheckActivity.class);
			break;			
		case R.id.home_menu_account: 
			CompanyListActivity.jump(this, CompanyViewActivity.class);
			//jumpActivity(CompanyListActivity.class);
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


	private void showDisable()
	{
		showToast(HomeActivity.this, "功能还在开发中");
		
	}

 

	
	
	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			proDialog.dismiss();
			//jumpTab(tabIndex);
			
		}
		else
		{
			proDialog.dismiss();
			showToast(HomeActivity.this, message);
		}
	}



	//Android按返回键退出程序但不销毁，程序后台运行
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{

		if (keyCode == KeyEvent.KEYCODE_BACK) 
		{
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}



	@Override
	public void initRes() {
		// TODO Auto-generated method stub
		
	} 
     
	
}
