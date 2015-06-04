/**   
* @Title: GetDetail.java 
* @Package cn.fuego.smart.home.ui.base 
* @Description: TODO
* @author Aether
* @date 2014-12-10 上午10:39:59 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import android.content.Context;
import android.content.Intent;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.smart.home.cache.AppCache;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.constant.AlarmPushTypeEnum;
import cn.fuego.smart.home.constant.IntentCodeConst;
import cn.fuego.smart.home.constant.PushMessagTypeEnum;
import cn.fuego.smart.home.ui.common.news.NewsViewActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.DeviceStatusActivity;
import cn.fuego.smart.home.ui.enterprise.alarm.FireAlarmActivity;
import cn.fuego.smart.home.ui.setting.model.ConfigInfo;
import cn.fuego.smart.home.webservice.down.model.AlarmPushInfoJson;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;

/** 
 * @ClassName: GetDetail 
 * @Description: 显示通知栏的具体信息
 * @author Aether
 * @date 2014-12-10 上午10:39:59 
 *  
 */
public class NotificationUtil
{
	private static NotificationUtil instance;
	
	private NotificationUtil() 
	{

	}
	/**
	 * 单例模式获取
	 * @return
	 */
	public static NotificationUtil getInstance() 
	{
		if (null == instance)
		{
			instance = new NotificationUtil();
		}
		return instance;

	}
    /**
     * 推送信息类型为LONG_PUSH需要响铃
     * @param context
     * @param extras
     */
	public void playAlarm(Context context, String extras)
	{
		PushMessageJson pushMsg = (PushMessageJson) JsonConvert.jsonToObject(extras, PushMessageJson.class);
		String objJson = JsonConvert.ObjectToJson(pushMsg.getObj());
		objJson=objJson.replace("\\", "");
 		int beginIndex = objJson.indexOf("\"") == 0 ? 1 : 0;  
        int endIndex = objJson.lastIndexOf("\"") + 1 == objJson.length() ? objJson.lastIndexOf("\"") : objJson.length();  
        objJson = objJson.substring(beginIndex, endIndex); 
		AlarmPushInfoJson alarmPushInfo = (AlarmPushInfoJson) JsonConvert.jsonToObject(objJson, AlarmPushInfoJson.class);
		//AlarmPushInfoJson alarmPushInfo =(AlarmPushInfoJson) pushMsg.getObj();
		if(alarmPushInfo!=null)
		{
			
			AlarmPushTypeEnum pushType= AlarmPushTypeEnum.getEnumByInt(alarmPushInfo.getPushType());
	        switch(pushType)
	        {
	        	case LONG_PUSH:
	        		ConfigInfo config = AppCache.getInstance().getConfig();
	        		if(config.isSound())
	        		{
		    			Intent serviceIntent = new Intent(context, AlarmSoundService.class);
		    			context.startService(serviceIntent);
	        		}
      		
	        		//AppShortCutUtil appShortCut= new AppShortCutUtil(context);
	        		//appShortCut.addBage();
	        		
	        		break;
		
	        	default:break;
	        
	        }
		}

		
	}
	/**
	 * 点击显示通知
	 * @param context
	 * @param msg
	 */
	public void showNotifcation(final Context context,String msg)
	{
		
		
   	    PushMessageJson pushMsg = (PushMessageJson) JsonConvert.jsonToObject(msg, PushMessageJson.class);
   	    PushMessagTypeEnum msgType=PushMessagTypeEnum.getEnumByInt(pushMsg.getObjType());

        switch(msgType)
        {
        	case NEWS_MSG:
        		showNews(context, pushMsg);
        		break;
        	case ALRAM_MSG: 
        		showAlarm(context, pushMsg);
        		break;
        		
        	default:break;
        
        }

	}
	/**
	 * 点击通知栏查看新闻信息
	 * @param context
	 * @param pushMsg
	 */
	private void showNews(Context context, PushMessageJson pushMsg)
	{
		Intent i = new Intent();
		i.setClass(context, NewsViewActivity.class);
		i.putExtra(IntentCodeConst.NEWS_ID,  String.valueOf(pushMsg.getObj()));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        context.startActivity(i); 
		
	}
	/**
	 * 点击通知栏获取告警信息
	 * @param context
	 * @param pushMsg
	 */
	public void showAlarm(final Context context,PushMessageJson pushMsg)
	{

		Intent i = new Intent();
 		String objJson = JsonConvert.ObjectToJson(pushMsg.getObj());
		objJson=objJson.replace("\\", "");
 		int beginIndex = objJson.indexOf("\"") == 0 ? 1 : 0;  
        int endIndex = objJson.lastIndexOf("\"") + 1 == objJson.length() ? objJson.lastIndexOf("\"") : objJson.length();  
        objJson = objJson.substring(beginIndex, endIndex); 
		AlarmPushInfoJson alarmPushInfo = (AlarmPushInfoJson) JsonConvert.jsonToObject(objJson, AlarmPushInfoJson.class);
		//AlarmPushInfoJson alarmPushInfo = (AlarmPushInfoJson) JsonConvert.jsonToObject(JsonConvert.ObjectToJson(pushMsg.getObj()), AlarmPushInfoJson.class);
		if(alarmPushInfo.getAlarmKind()==AlarmKindEnum.ALARM.getIntValue())
		{
			i.setClass(context, FireAlarmActivity.class);
		}
		else
		{
			i.setClass(context, DeviceStatusActivity.class);
		}
		i.putExtra(IntentCodeConst.COMPANY_ID,  alarmPushInfo.getCompanyID());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        context.startActivity(i); 
	}
	
}
