/**   
* @Title: JPushToolImpl.java 
* @Package cn.fuego.smart.home.webservice.down.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-8 上午10:09:42 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.service.impl;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.smart.home.service.cache.FuegoPushInfo;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;
import cn.fuego.smart.home.webservice.down.service.PushToolInterface;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

 /** 
 * @ClassName: JPushToolImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-8 上午10:09:42 
 *  
 */
public class JPushToolImpl implements PushToolInterface
{
	private FuegoLog log = FuegoLog.getLog(getClass());

    // demo App defined in resources/jpush-api.conf 
	private static final String appKey ="97c0f1c1744eacef02d529c1";//"dd1066407b044738b6479275";
	private static final String masterSecret = "50b48aa6f2c8d4319e13466d";//"2b38ce69b1de2a7fa95706ea";
	
	public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.down.service.PushToolInterface#pushNotification(cn.fuego.smart.home.service.cache.FuegoPushInfo, java.lang.Object)
	 */
	@Override
	public void pushNotification(FuegoPushInfo pushInfo, String title,String content,PushMessageJson msgObj)
	{
		log.info("now push message by jpush,pushInfo is " + pushInfo);
		String alias = pushInfo.getUserID();
 

		 
		PushPayload payLoad = buildForAlias(title, alias, msgObj);
		
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        try {
            PushResult result = jpushClient.sendPush(payLoad);
            log.info("Got result - " + result);
            
        } catch (APIConnectionException e) {
        	log.error("Connection error. Should retry later. ", e);
            
        } catch (APIRequestException e) {
        	log.error("Error response from JPush server. Should review and fix it. ", e);
        	log.info("HTTP Status: " + e.getStatus());
        	log.info("Error Code: " + e.getErrorCode());
        	log.info("Error Message: " + e.getErrorMessage());
        	log.info("Msg ID: " + e.getMsgId());
        }
		
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.down.service.PushToolInterface#pushAll(java.lang.String, java.lang.String, cn.fuego.smart.home.webservice.down.model.PushMessageJson)
	 */
	@Override
	public void pushAll(String title, String content, PushMessageJson msgObj)
	{
		log.info("now push message. the tile is " + title);
 
		 
		PushPayload payLoad = buildForAll(title,msgObj);
		
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        try {
            PushResult result = jpushClient.sendPush(payLoad);
            log.info("Got result - " + result);
            
        } catch (APIConnectionException e) {
        	log.error("Connection error. Should retry later. ", e);
            
        } catch (APIRequestException e) {
        	log.error("Error response from JPush server. Should review and fix it. ", e);
        	log.info("HTTP Status: " + e.getStatus());
        	log.info("Error Code: " + e.getErrorCode());
        	log.info("Error Message: " + e.getErrorMessage());
        	log.info("Msg ID: " + e.getMsgId());
        }
		
		
	}
	private PushPayload buildForAll(String title,PushMessageJson msgObj)
	{
		int objType = msgObj.getObjType();
		String obj = JsonConvert.ObjectToJson(msgObj.getObj());
		PushPayload payLoad = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.all())
		        .setNotification(Notification.newBuilder()
		        		.setAlert(title)
		        		.addPlatformNotification(AndroidNotification.newBuilder()
		        				.addExtra("obj", obj)
		        				.addExtra("objType", objType).build())
		        		.addPlatformNotification(IosNotification.newBuilder()
		        				.incrBadge(1)
		        				.addExtra("obj", obj)
		        				.addExtra("objType", objType).build())
		        		.build())
		        .build();
		return payLoad;
	}
	private PushPayload buildForAlias(String alias,String title,PushMessageJson msgObj)
	{
		int objType = msgObj.getObjType();
		String obj = JsonConvert.ObjectToJson(msgObj.getObj());
		PushPayload payLoad = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
		        .setAudience(Audience.alias(alias))
		        .setNotification(Notification.newBuilder()
		        		.setAlert(title)
		        		.addPlatformNotification(AndroidNotification.newBuilder()
		        				.addExtra("obj", obj)
		        				.addExtra("objType", objType).build())
		        		.addPlatformNotification(IosNotification.newBuilder()
		        				.incrBadge(1)
		        				.addExtra("obj", obj)
		        				.addExtra("objType", objType).build())
		        		.build())
		        .build();
		return payLoad;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.down.service.PushToolInterface#pushMessage(cn.fuego.smart.home.service.cache.FuegoPushInfo, java.lang.Object)
	 */
	@Override
	public void pushMessage(FuegoPushInfo pushInfo, PushMessageJson msgObj)
	{
		// TODO Auto-generated method stub
		
	}

}
