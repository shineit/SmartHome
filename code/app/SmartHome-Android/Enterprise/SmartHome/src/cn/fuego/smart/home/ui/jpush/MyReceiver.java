package cn.fuego.smart.home.ui.jpush;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.constant.PushMessagTypeEnum;
import cn.fuego.smart.home.service.GetDetail;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.MainActivity;
import cn.fuego.smart.home.ui.authrun.AlarmSoundService;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;
import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";
	
    //自定义通知显示公共方法
    private GetDetail getDetail = new GetDetail();
	

	@Override
	public void onReceive(Context context, Intent intent) 
	{

		Bundle bundle = intent.getExtras();
		Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
		
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...
                        
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        	processCustomMessage(context, bundle);
        
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
           // int notifactionId = bundle.getInt(JPushInterface.EXTRA_EXTRA);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知 __bundle extra_extra"+bundle.getString(JPushInterface.EXTRA_EXTRA));
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            playAlarm(context,extras);
            
           // Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        	
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] jtest用户点击打开了通知");
            
        	//打开自定义的Activity
    		//Intent i = new Intent();
    		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);

    			showNotifcation(context,extras);
           	    Log.d(TAG, "[MyReceiver] bundle extra_extra"+bundle.getString(JPushInterface.EXTRA_EXTRA));


        	
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        	
        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        	Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
        	Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
        
	}


	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} 
			else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
		if (MainActivity.isForeground) {
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
			if (!JPushUtil.isEmpty(extras)) {
				try {
					JSONObject extraJson = new JSONObject(extras);
					if (null != extraJson && extraJson.length() > 0) {
						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
					}
				} catch (JSONException e) {

				}

			}
			context.sendBroadcast(msgIntent);
		}
	}
	
	/*
	 * 根据objType 显示相应的内容
	 */
	private void showNotifcation(final Context context,String msg)
	{
		
		
   	    PushMessageJson pushMsg = (PushMessageJson) JsonConvert.jsonToObject(msg, PushMessageJson.class);
   	    PushMessagTypeEnum msgType=PushMessagTypeEnum.getEnumByInt(pushMsg.getObjType());

        switch(msgType)
        {
        	case NEWS_MSG:

        		getDetail.showNews(context, pushMsg);
        		break;
        	case ALRAM_MSG: 
        		getDetail.showHomeAlarm(context, pushMsg);
        	case FATAL_ALARM: 
        		getDetail.showFatalAlarm(context, pushMsg);
        		break;
        		
        	default:break;
        
        }

	}
    /**
     * 报警类型为火警需要响铃
     * @param context
     * @param extras
     */
	private void playAlarm(Context context, String extras)
	{
		PushMessageJson pushMsg = (PushMessageJson) JsonConvert.jsonToObject(extras, PushMessageJson.class);
		PushMessagTypeEnum msgType=PushMessagTypeEnum.getEnumByInt(pushMsg.getObjType());
        switch(msgType)
        {
        	case FATAL_ALARM:
    			Intent serviceIntent = new Intent(context, AlarmSoundService.class);
    			context.startService(serviceIntent);
        		//SoundPoolHandler.playSound(1,-1);       		
        		//AppShortCutUtil appShortCut= new AppShortCutUtil(context);
        		//appShortCut.addBage();
        		
        		break;
        	case NEWS_MSG:
        	case ALRAM_MSG:

        		//getDetail.showNews(context, pushMsg);
        		//spHandler.playSound(1, 0);
        		//SoundPoolHandler.playSound(1, 0);
        		break;	
        	default:break;
        
        }
		
	}



}
