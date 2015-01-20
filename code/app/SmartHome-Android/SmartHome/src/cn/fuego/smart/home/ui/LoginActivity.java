package cn.fuego.smart.home.ui;

import java.util.Set;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.ClientTypeEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.service.SystemUser;
import cn.fuego.smart.home.ui.base.BaseActivtiy;
import cn.fuego.smart.home.ui.base.ExitApplication;
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
    private ProgressDialog proDialog;

    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ExitApplication.getInstance().addActivity(this);
		
		textName = (EditText) findViewById(R.id.txt_username);
		textPwd =(EditText) findViewById(R.id.txt_password);
		loginBtn = (Button)findViewById(R.id.login_btn);
		loginBtn.requestFocus();
		loginBtn.requestFocusFromTouch();
		loginBtn.setOnClickListener(loginClick);
       
		
	}
	private OnClickListener loginClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
            
			proDialog =ProgressDialog.show(LoginActivity.this, "请稍等", "登录信息验证中……");
			checkLogin();
			

		}


	};
	private void checkLogin()
	{

		userName =textName.getText().toString().trim();
		//password =textPwd.getText().toString();
		password =MD5(textPwd.getText().toString().trim());
		LoginReq req = new LoginReq();
		req.setPassword(password);
		req.setUserName(userName);
		req.setClientType(ClientTypeEnum.ANDRIOD_CLIENT.getIntValue());
		req.setClientVersion(MemoryCache.getVersion());
		req.setDevToken( getDeviceID());
		
		req.setPush_userID(req.getUserName());
		JPushInterface.setAliasAndTags(getApplicationContext(), req.getUserName(), null, mAliasCallback);
        
	    
		WebServiceContext.getInstance().getUserManageRest(this).login(req);
 
	}
	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
 
            switch (code) {
            case 0:
                log.info("Set tag and alias success");
                log.info("the user is " + MemoryCache.getLoginInfo().getUser());
                break;
                
            case 6002:
                 log.error("Failed to set alias and tags due to timeout. Try again after 60s.");
                 log.error("the user is " + MemoryCache.getLoginInfo().getUser());
                break;
            
            default:
      
                log.error("Failed with errorCode" + code);
                log.error("the user is " + MemoryCache.getLoginInfo().getUser());
            }
           
        }
	    
	};
	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			LoginRsp rsp = (LoginRsp) message.getMessage().obj;

			// 存放个人信息cookie
/*			SharedPreferences userInfo = getSharedPreferences(SharedPreferenceConst.UESR_INFO, Context.MODE_PRIVATE);
			userInfo.edit().putString(SharedPreferenceConst.NAME, userName).commit();
			userInfo.edit().putString(SharedPreferenceConst.PASSWORD, password).commit();*/
			SystemUser user = new  SystemUser();
			user.setRole(rsp.getUser().getRole());
			user.setUserID(rsp.getUser().getUserID());
			user.setUserName(rsp.getUser().getUserName());
			
            MemoryCache.getLoginInfo().setUser(user);
            MemoryCache.setToken(rsp.getToken());
            MemoryCache.getLoginInfo().setCustomer(rsp.getCustomer());
 			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, HomeActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();

		}
		else
		{
			this.showMessage(message);
		}
		
		proDialog.dismiss();

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.news_item)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public String getDeviceID()
	{
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		 return tm.getDeviceId();
	}

}
