package cn.fuego.smart.home.ui.jpush;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.constant.PushMessagTypeEnum;
import cn.fuego.smart.home.constant.SensorKindEunm;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.MainActivity;
import cn.fuego.smart.home.ui.MainTabbarActivity;
import cn.fuego.smart.home.ui.home.AlarmManageActivity;
import cn.fuego.smart.home.ui.model.AlarmViewModel;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;
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
	
	//自定义信息内容和页面跳转
	private AlarmViewModel alarmModel= new AlarmViewModel();
	private Intent newsIntent= new Intent();
	private Intent alarmIntent= new Intent();	

	@Override
	public void onReceive(Context context, Intent intent) {
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
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_EXTRA);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        	
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
   	    //AlarmJson alarm= ;
   	   // AlarmJson alarmObject = (AlarmJson) pushMsg.getObj();
   	    
        switch(msgType)
        {
        	case NEWS_MSG:
        		
        		newsIntent.setClass(context, MainTabbarActivity.class);
        		newsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            	context.startActivity(newsIntent);
        		break;
        	case ALRAM_MSG: 
        		GetAlarmByIDReq req = new GetAlarmByIDReq();
        		req.setAlarmID(String.valueOf(pushMsg.getObj()));
        		req.setToken(MemoryCache.getToken());
        		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
        			@Override
        			public void handle(MispHttpMessage msg)
        			{
        				GetAlarmByIDRsp rsp = (GetAlarmByIDRsp) msg.getMessage().obj;
        				if(rsp.getAlarm().getObjType()==AlarmObjTypeEnmu.CONCENTRATOR_ALARM.getIntValue())
                		{
                			return;
                		}
                		if(rsp.getAlarm().getObjType()==AlarmObjTypeEnmu.HOME_SENSOR.getIntValue())
                		{
                			showHomeSensor(context,rsp.getAlarm());
                		}
                		if(rsp.getAlarm().getObjType()==AlarmObjTypeEnmu.FIRE_SENSOR.getIntValue())
                		{
                			return;
                		}
        			}
        		}).getAlarm(req);
        		

        		break;
        		
        	default:break;
        
        }
     


	}

	private void showHomeSensor(final Context context,final AlarmJson alarm)
	{
		GetSensorByIDReq req = new GetSensorByIDReq();
    	req.setSensorID(String.valueOf(alarm.getObjID()));
  		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
			@Override
			public void handle(MispHttpMessage msg) {

				 GetSensorByIDRsp rsp = (GetSensorByIDRsp)msg.getMessage().obj;

				 alarmIntent.putExtra(alarmModel.getEventID(), String.valueOf(alarm.getId()));
				 alarmIntent.putExtra(alarmModel.getObjID(), String.valueOf(rsp.getSensor().getSensorID()));
				 alarmIntent.putExtra(alarmModel.getObj(), SensorKindEunm.getEnumByInt(rsp.getSensor().getSensorKind()).getStrValue());
     		     alarmIntent.putExtra(alarmModel.getDescription(),rsp.getSensor().getDescriptions());
				 alarmIntent.putExtra(alarmModel.getWarnValue(), String.valueOf(rsp.getSensor().getWarnValue()));
				 alarmIntent.putExtra(alarmModel.getErrorValue(), String.valueOf(rsp.getSensor().getErrorValue()));
				 
				 alarmIntent.putExtra(alarmModel.getTitle(),AlarmTypeEnum.getEnumByInt(alarm.getAlarmType()).getStrValue());
		         alarmIntent.setClass(context,AlarmManageActivity.class);
                 alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                 context.startActivity(alarmIntent);

			}
		}).getSensor(req);
  		
		
	}


}
