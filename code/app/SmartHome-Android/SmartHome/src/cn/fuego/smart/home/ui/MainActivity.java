package cn.fuego.smart.home.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.jpush.MyReceiver;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;

import com.videogo.openapi.EzvizAPI;

public class MainActivity extends InstrumentedActivity 
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);
	
	public static boolean isForeground = false;
    public static String APP_KEY = "9a39449992d048439b4cef7d62a3c997";
    public static String SECRET_KEY = "2e49fa81764d370c2693a5f1ed0d8048";

    public static String API_URL = "https://open.ys7.com";
    public static String WEB_URL = "https://auth.ys7.com";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		setContentView(R.layout.main_welcome);
		//initView();   
		registerMessageReceiver();  // used for receive msg
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
		        	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
		        	if(MemoryCache.getFlag()==1)
		        	{
		        		MemoryCache.setFlag(0);
		        	}
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
	

	// 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
	private void init(){
		 JPushInterface.init(getApplicationContext());
	}


	@Override
	protected void onResume() {
		isForeground = true;
		super.onResume();
	}


	@Override
	protected void onPause() {
		isForeground = false;
		super.onPause();
	}


	@Override
	protected void onDestroy() {
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}
	

	//for receive customer msg from jpush server
	private MyReceiver mMessageReceiver;
	public static final String MESSAGE_RECEIVED_ACTION = "cn.fuego.MESSAGE_RECEIVED_ACTION";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";
	
	public void registerMessageReceiver() {
		mMessageReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}


		
}
