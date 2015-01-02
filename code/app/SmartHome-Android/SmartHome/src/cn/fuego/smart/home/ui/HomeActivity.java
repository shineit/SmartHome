package cn.fuego.smart.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.info.AlarmActivity;
import cn.fuego.smart.home.ui.info.NewsActivity;
import cn.fuego.smart.home.ui.setting.service.ServiceActivity;
import cn.fuego.smart.home.ui.setting.user.UserManageActivity;

public class HomeActivity extends BaseActivtiy implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ExitApplication.getInstance().addActivity(this);
		
		Button safe_btn= (Button) findViewById(R.id.home_menu_safe);
		safe_btn.setTag(1);
		safe_btn.setOnClickListener(this);
		Button ctr_btn= (Button) findViewById(R.id.home_menu_control);
		ctr_btn.setTag(2);
		ctr_btn.setOnClickListener(this);	
		Button camera_btn= (Button) findViewById(R.id.home_menu_camera);
		camera_btn.setTag(3);
		camera_btn.setOnClickListener(this);		
		Button alarm_btn= (Button) findViewById(R.id.home_menu_alarm);
		alarm_btn.setTag(4);
		alarm_btn.setOnClickListener(this);		
		Button plane_btn= (Button) findViewById(R.id.home_menu_plane);
		plane_btn.setTag(5);
		plane_btn.setOnClickListener(this);
		
		Button concent_btn= (Button) findViewById(R.id.home_menu_concent);
		concent_btn.setTag(6);
		concent_btn.setOnClickListener(this);		
		Button area_btn= (Button) findViewById(R.id.home_menu_area);
		area_btn.setTag(7);
		area_btn.setOnClickListener(this);
		
		Button account_btn= (Button) findViewById(R.id.home_menu_account);
		account_btn.setTag(8);
		account_btn.setOnClickListener(this);
		Button apply_btn= (Button) findViewById(R.id.home_menu_apply);
		apply_btn.setTag(9);
		apply_btn.setOnClickListener(this);
		Button news_btn= (Button) findViewById(R.id.home_menu_news);
		news_btn.setTag(10);
		news_btn.setOnClickListener(this);
		
	}


	@Override
	public void handle(MispHttpMessage message)
	{
		
		
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		switch(tag)
		{
		case 1: jumpTab(1);
				break;
		case 2: jumpTab(2);
				break;
		case 3: jumpTab(3);
				break;
		case 4:	jumpActivity(AlarmActivity.class);
				break;
		case 5: showDisable();
				break;
		case 6: showDisable();
				break;
		case 7: showDisable();
				break;
		case 8: jumpActivity(UserManageActivity.class);
				break;
		case 9: jumpActivity(ServiceActivity.class);
				break;
		case 10:jumpActivity(NewsActivity.class);
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
}
