package cn.fuego.smart.home.ui.setting.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.fuego.smart.home.ui.LoginActivity;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.setting.service.ServiceApplyActivity;

import com.fuego.smarthome.R;

public class UserInfoActivity extends BaseActivtiy implements View.OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		ExitApplication.getInstance().addActivity(this);
		
		Button back_btn=(Button)findViewById(R.id.user_info_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button user_info_btn = (Button) findViewById(R.id.modify_userinfo_sure);
		user_info_btn.setOnClickListener(this);
		user_info_btn.setTag(2);
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer) v.getTag();
		Intent intent = null;
		switch(tag)
		{
		case 1: this.finish();  
				break;//个人中心返回
		case 2: intent = new Intent(this,LoginActivity.class);
		 		startActivity(intent);
		 		break;//跳转到

		default:break;
		}
		
	}
}
