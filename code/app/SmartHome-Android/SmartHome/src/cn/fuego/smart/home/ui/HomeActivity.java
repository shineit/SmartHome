package cn.fuego.smart.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.about.AboutUsActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.info.AlarmActivity;
import cn.fuego.smart.home.ui.info.NewsActivity;
import cn.fuego.smart.home.ui.setting.concent.ConcentConfigActivity;
import cn.fuego.smart.home.ui.setting.concent.ConcentListActivity;
import cn.fuego.smart.home.ui.setting.service.ServiceActivity;
import cn.fuego.smart.home.ui.setting.user.UserManageActivity;

public class HomeActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	//private Boolean isLoad=false;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ExitApplication.getInstance().addActivity(this);
		
		initView();

		
	}



	private void initView()
	{
		Button safe_btn= (Button) findViewById(R.id.home_menu_safe);
		safe_btn.setOnClickListener(this);
		Button ctr_btn= (Button) findViewById(R.id.home_menu_control);
		ctr_btn.setOnClickListener(this);	
		Button camera_btn= (Button) findViewById(R.id.home_menu_camera);
		camera_btn.setOnClickListener(this);		
		Button alarm_btn= (Button) findViewById(R.id.home_menu_alarm);
		alarm_btn.setOnClickListener(this);		
		Button plane_btn= (Button) findViewById(R.id.home_menu_plane);
		plane_btn.setOnClickListener(this);
		
		Button concent_btn= (Button) findViewById(R.id.home_menu_concent);
		concent_btn.setOnClickListener(this);		
		Button area_btn= (Button) findViewById(R.id.home_menu_area);
		area_btn.setOnClickListener(this);
		
		Button account_btn= (Button) findViewById(R.id.home_menu_account);
		account_btn.setOnClickListener(this);
		Button apply_btn= (Button) findViewById(R.id.home_menu_apply);
		apply_btn.setOnClickListener(this);
		Button news_btn= (Button) findViewById(R.id.home_menu_news);
		news_btn.setOnClickListener(this);
		
		Button about_btn= (Button) findViewById(R.id.home_about_us_btn);
		about_btn.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.home_menu_safe: 
			jumpTab(1);
			break;
		case R.id.home_menu_control: 
			jumpTab(2);
			break;
		case R.id.home_menu_camera: 
			jumpTab(3);
			break;
		case R.id.home_menu_alarm:	
			jumpActivity(AlarmActivity.class);
			break;
		case R.id.home_menu_plane: 
			showDisable();
			break;
		case R.id.home_menu_concent:
			jumpActivity(ConcentListActivity.class);
			break;
		case R.id.home_menu_area: 
			showDisable();
			break;
		case R.id.home_menu_account: 
			jumpActivity(UserManageActivity.class);
			break;
		case R.id.home_menu_apply: 
			jumpActivity(ServiceActivity.class);	
			break;
		case R.id.home_menu_news:
			jumpActivity(NewsActivity.class);
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
		Toast.makeText(HomeActivity.this, "功能还在开发中……", Toast.LENGTH_LONG).show();
		
	}



	private void jumpTab(int index)
	{
		Intent i= new Intent();
		i.setClass(HomeActivity.this, MainTabbarActivity.class);
		i.putExtra("num", index);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        this.startActivity(i);
		this.finish();
		
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
     
	
}
