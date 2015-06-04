package cn.fuego.smart.home.ui;

import java.util.HashSet;
import java.util.Set;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.smart.enterprise.R;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.ui.jpush.MyReceiver;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends InstrumentedActivity 
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);
	
	public static boolean isForeground = false;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_welcome);
		//本地缓存;  
 		
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
 
				 if(!MemoryCache.isLogined()||AppCache.getInstance().getUser().getOrg_id()==null
						 ||ValidatorUtil.isEmpty(AppCache.getInstance().getUser().getOrg_id()))
				 {
					 LoginActivity.jump(MainActivity.this, HomeActivity.class);
					 
				 }
				 else
				 {
					//注册推送信息
					Set<String> tags=  new HashSet<String>();
					tags.add(AppCache.getInstance().getUser().getOrg_id());
					JPushInterface.setAliasAndTags(MainActivity.this,AppCache.getInstance().getUser().getUserName(), tags, mAliasCallback);
						
					HomeActivity.jump(MainActivity.this);
					 
				 }
				 

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
