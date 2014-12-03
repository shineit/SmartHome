package cn.fuego.smart.home.ui.setting.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;

public class UserManageActivity extends BaseActivtiy implements View.OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		//统一activity管理，用于退出APP
		ExitApplication.getInstance().addActivity(this);
		
		Button back_btn=(Button)findViewById(R.id.user_info_back);
		back_btn.setOnClickListener(this);
		back_btn.setTag(1);
		
		Button user_info_btn = (Button) findViewById(R.id.modify_userinfo_sure);
		user_info_btn.setOnClickListener(this);
		user_info_btn.setTag(2);
		
		Button modify_pwd_btn =  (Button) findViewById(R.id.modify_pwd_btn);
		modify_pwd_btn.setOnClickListener(this);
		modify_pwd_btn.setTag(3);
		
		Button logout_btn =  (Button) findViewById(R.id.logout_btn);
		logout_btn.setOnClickListener(this);
		logout_btn.setTag(4);		
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
		case 2: //To do
		 		break;//个人信息保存修改
		case 3: intent = new Intent(this,ModifyPwdActivity.class);
 				startActivity(intent);
 				break;//跳转到密码修改页面
		case 4: exitDialog(UserManageActivity.this);
 				break;//退出当前应用 		
		default:break;
		}
		
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}
}
