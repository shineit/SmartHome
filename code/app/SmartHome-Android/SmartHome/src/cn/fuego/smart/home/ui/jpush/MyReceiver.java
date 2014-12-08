package cn.fuego.smart.home.ui.jpush;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.R;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.service.MemoryCache;
import cn.fuego.smart.home.ui.LoginActivity;
import cn.fuego.smart.home.ui.MainActivity;
import cn.fuego.smart.home.ui.MainTabbarActivity;
import cn.fuego.smart.home.ui.home.AlarmManageActivity;
import cn.fuego.smart.home.ui.model.AlarmViewModel;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
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
    		Intent i = new Intent();
    		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
    		if(!MemoryCache.isLogin())
            {
            	i.setClass(context, LoginActivity.class);
            	
            }
    		else 
    		{
    			showNotifcation(context,extras);
           	    Log.d(TAG, "[MyReceiver] bundle extra_extra"+bundle.getString(JPushInterface.EXTRA_EXTRA));
    		}

        	
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
	private void showNotifcation(Context context,String msg)
	{
		
		Intent i = new Intent();
   	    PushMessageJson pushMsg = (PushMessageJson) JsonConvert.jsonToObject(msg, PushMessageJson.class);
   	    AttributeJson  filter = new AttributeJson();
   	    filter.setAttrName("ID");
   	    filter.setAttrValue(attrValue);
        switch(pushMsg.getObjType())
        {
        	case 0:
        		i.setClass(context, MainTabbarActivity.class);
        		break;
        	case 1:
        		GetHistoryAlarmListReq req = new GetHistoryAlarmListReq();
        		req.setUserID(1);
        		req.setFilterList(filterList);
        		WebServiceContext.getInstance().getSensorManageRest(new MispHttpHandler(){
        			@Override
        			public void handle(MispHttpMessage msg) {
        				// TODO Auto-generated method stub

        				 GetHistoryAlarmListRsp rsp = (GetHistoryAlarmListRsp)msg.getMessage().obj;
        				 for(AlarmJson json : rsp.getAlarmList()){

        					 i.putExtra(alarmModel.getEventID(),rsp.getObj().toString());
/*        						listItem.put(alarmItemAttrs[0], alarmIcon.getResourceId(json.getAlarmType(), 0));
        						listItem.put(alarmItemAttrs[1], AlarmTypeEnum.getEnumByInt(json.getAlarmType()).getStrValue());
        						listItem.put(alarmItemAttrs[2], null);
        						listItem.put(alarmItemAttrs[3], AlarmClearEnum.getEnumByInt(json.getClearStatus()).getStrValue());
        						listItem.put(alarmItemAttrs[4], DateUtil.getStrTime(json.getAlarmTime()));
        						listItem.put(alarmItemAttrs[5], json.getId());//告警信息ID，用于索引，在页面不显示
        						listItem.put(alarmItemAttrs[6], json.getObjID());
        						listItem.put(alarmItemAttrs[7], AlarmObjTypeEnmu.getEnumByInt(json.getObjType()).getStrValue());
        						listItem.put(alarmItemAttrs[8], json.getDataValue());
*/
        				 }

        			}
        		}).getAlarmList(req);
        		i.putExtra(alarmModel.getTitle(),"2");
        		i.putExtra(alarmModel.getEventID(),rsp.getObj().toString());
        		//intent.putExtra(alarmModel.getObjID(), selected_alarm_id);
        		i.putExtra(alarmModel.getObjID(), "2");
        		i.putExtra(alarmModel.getObj(),"2");
        		i.setClass(context, AlarmManageActivity.class);
        		break;
        	default:break;
        
        }

		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
    	context.startActivity(i);

	}

}
