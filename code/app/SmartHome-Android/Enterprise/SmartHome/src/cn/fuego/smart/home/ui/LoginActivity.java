package cn.fuego.smart.home.ui;

import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.ClientTypeEnum;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
import cn.fuego.smart.home.ui.base.SharedPreUtil;
import cn.fuego.smart.home.ui.base.UserEntity;
import cn.fuego.smart.home.webservice.up.model.LoginReq;
import cn.fuego.smart.home.webservice.up.model.LoginRsp;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends BaseActivtiy
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private Button loginBtn;
    private EditText textName,textPwd;
    private String 	userName,password; 
    
    private static String JUMP_SOURCE = "jumpSource";
    
 	@Override
	public void initRes() 
	{
		
	}
	public static void jump(Context context,Class jumpSource)
	{
		AppCache.getInstance().clear();

 		Intent intent = new Intent();
 		intent.putExtra(JUMP_SOURCE, jumpSource);
 		intent.setClass(context, LoginActivity.class);
 		context.startActivity(intent);
  	}
	
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
/*		if(MemoryCache.getFlag()==1)
		{
			textPwd.setText("");
			textPwd.requestFocus();
			textPwd.requestFocusFromTouch();
		}*/
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
 			checkLogin(userName,password);

		}

	};


	public void  checkLogin(String userName,String pwd)
	{
 
 
		
		LoginReq req = new LoginReq();
		req.setPassword(MD5(pwd));
		req.setUserName(userName);
		req.setClientType(ClientTypeEnum.ANDRIOD_CLIENT.getIntValue());
 		req.setDevToken(getDeviceID());
		
		req.setPush_userID(req.getUserName());
		
		JPushInterface.setAliasAndTags(this, userName, null, mAliasCallback);
	

	    
		WebServiceContext.getInstance().getUserManageRest(this).login(req);
	}
	
	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
 
            switch (code) {
            case 0:
                log.info("Set tag and alias success");
                log.info("the user is " + AppCache.getInstance().getUser());
                break;
                
            case 6002:
                 log.error("Failed to set alias and tags due to timeout. Try again after 60s.");
                 log.error("the user is " + AppCache.getInstance().getUser());
                break;
            
            default:
      
                log.error("Failed with errorCode" + code);
                log.error("the user is " + AppCache.getInstance().getUser());
            }
           
        }
	    
	};
	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
	 
			LoginRsp rsp = (LoginRsp) message.getMessage().obj;
	 
			AppCache.getInstance().update(rsp.getToken(), rsp.getUser(), rsp.getCustomer());
			Class jumpClass = (Class) this.getIntent().getSerializableExtra(JUMP_SOURCE);
            jumpIntent( jumpClass);
 
		}
		else
		{
			showMessage(message);
 
			
		}
 
	}
	private void jumpIntent(Class clazz)
	{
		Intent toIntent = new Intent();
		toIntent.setClass(this, clazz);
		this.startActivity(toIntent);
		//this.finish();
	}
	public String getDeviceID()
	{
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		 return tm.getDeviceId();
	}
}
