package cn.fuego.smart.home.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.base.LoginHandler;
import cn.fuego.smart.home.ui.base.SharedPreUtil;
import cn.fuego.smart.home.ui.base.UserEntity;

public class LoginActivity extends BaseActivtiy
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private Button loginBtn;
    private EditText textName,textPwd;
    private String 	userName,password; 

    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ExitApplication.getInstance().addActivity(this);
		//本地存储个人信息
		SharedPreUtil.initSharedPreference(getApplicationContext());
		
		textName = (EditText) findViewById(R.id.txt_username);
		textPwd =(EditText) findViewById(R.id.txt_password);
		UserEntity userInfo = SharedPreUtil.getInstance().getUser();
		// log.info("userInfo"+userInfo);
		if(!ValidatorUtil.isEmpty(userInfo.getUserName()))
		 {
			 textName.setText(userInfo.getUserName());
			 
		 }
		if(MemoryCache.getFlag()==1)
		{
			textPwd.setText("");
			textPwd.requestFocus();
			textPwd.requestFocusFromTouch();
		}
		else if(!ValidatorUtil.isEmpty(userInfo.getPassword()))
		{
			textPwd.setText(userInfo.getPassword());
		}

		loginBtn = (Button)findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(loginClick);

       
		
	}
	private OnClickListener loginClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			userName=textName.getText().toString().trim();
			password=textPwd.getText().toString().trim();
			if(ValidatorUtil.isEmpty(userName))
			{
				showToast(LoginActivity.this, "用户名不能为空");
				return;
			}
			if(ValidatorUtil.isEmpty(password))
			{
				showToast(LoginActivity.this, "密码不能为空");
				return;
			}
			LoginHandler longinHandler= new LoginHandler(LoginActivity.this);
			longinHandler.checkLogin(userName,password,false);

		}

	};


}
