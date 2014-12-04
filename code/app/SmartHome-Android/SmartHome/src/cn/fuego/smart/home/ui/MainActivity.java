package cn.fuego.smart.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.bdsend.Utils;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

public class MainActivity extends Activity
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);
    //private int loginFlag=0;
	public void onCreate(Bundle savedInstanceState) 
	{

		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_welcome);
        // Push: 以apikey的方式登录，一般放在主Activity的onCreate中。
        // 这里把apikey存放于manifest文件中，只是一种存放方式，
        // 您可以用自定义常量等其它方式实现，来替换参数中的Utils.getMetaValue(PushDemoActivity.this,
        // "api_key")
        PushManager.startWork(getApplicationContext(),PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(MainActivity.this, "api_key"));
        // Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
        // PushManager.enableLbs(getApplicationContext());

	new CountDownTimer(2000, 1000)
		{

			@Override
			public void onTick(long millisUntilFinished)
			{
			}

			@Override
			public void onFinish()
			{
				Intent intent = new Intent();
				//SharedPreferences userInfo = getSharedPreferences(SharedPreferenceConst.UESR_INFO, 0);
				
				if(!MemoryCache.isLogin())
		        {
		        	intent.setClass(MainActivity.this, LoginActivity.class);
		        	//loginFlag =1;
		        	
		        }
		        else
		        {
		        	intent.setClass(MainActivity.this, MainTabbarActivity.class);
		        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        }
				
				startActivity(intent);

				@SuppressWarnings("deprecation")
				int VERSION = Integer.parseInt(android.os.Build.VERSION.SDK);
				if (VERSION >= 5)
				{
					MainActivity.this.overridePendingTransition(
							R.anim.alpha_in, R.anim.alpha_out);
				}
				finish();
			}
		}.start();
	}
		
}
