package cn.fuego.smart.home.webservice.down.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.smart.home.service.cache.BaiduPushInfo;
import cn.fuego.smart.home.service.impl.UserManageServiceImpl;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class BaiduPushTool
{
	private static Log log = LogFactory.getLog(UserManageServiceImpl.class);

	private static String apiKey = SystemConfigInfo.getConfigItem("apiKey");
	private static String secretKey = SystemConfigInfo.getConfigItem("secretKey");

	
	 
	public static void pushNotification(BaiduPushInfo pushInfo, PushMessageJson message)
	{
		/*
		 * @brief 推送广播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
		 */

		// 1. 设置developer平台的ApiKey/SecretKey

		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

		// 2. 创建BaiduChannelClient对象实例
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);

		// 3. 若要了解交互细节，请注册YunLogHandler类
		channelClient.setChannelLogHandler(new YunLogHandler()
		{
			@Override
			public void onHandle(YunLogEvent event)
			{
				log.info(event.getMessage());
			}
		});

		try
		{

			// 4. 创建请求类对象
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(pushInfo.getDeviceType()); // device_type =>// 1: web 2: pc// 3:android //// 4:ios 5:wp

			request.setChannelId(Long.valueOf(pushInfo.getChannelID()));
			request.setUserId(pushInfo.getUser_id());
			request.setMessage(JsonConvert.ObjectToJson(message));
			request.setMessageType(1);

			// 5. 调用pushMessage接口
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

			// 6. 认证推送成功
			log.info("push amount : " + response.getSuccessAmount());

		} catch (ChannelClientException e)
		{
			// 处理客户端错误异常
			log.error("push client failed",e);
		} catch (ChannelServerException e)
		{
			// 处理服务端错误异常
			log.error("push server failed",e);
 
		}
	}
	public static void pushMessage(BaiduPushInfo pushInfo, String message)
	{

		/*
		 * @brief 推送广播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
		 */

		// 1. 设置developer平台的ApiKey/SecretKey

		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

		// 2. 创建BaiduChannelClient对象实例
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);

		// 3. 若要了解交互细节，请注册YunLogHandler类
		channelClient.setChannelLogHandler(new YunLogHandler()
		{
			@Override
			public void onHandle(YunLogEvent event)
			{
				log.info(event.getMessage());
			}
		});

		try
		{

			// 4. 创建请求类对象
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(pushInfo.getDeviceType()); // device_type =>// 1: web 2: pc// 3:android //// 4:ios 5:wp

			request.setChannelId(Long.valueOf(pushInfo.getChannelID()));
			request.setUserId(pushInfo.getUser_id());
			request.setMessage(message);

			// 5. 调用pushMessage接口
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

			// 6. 认证推送成功
			log.info("push amount : " + response.getSuccessAmount());

		} catch (ChannelClientException e)
		{
			// 处理客户端错误异常
			log.error("push client failed",e);
		} catch (ChannelServerException e)
		{
			// 处理服务端错误异常
			log.error("push server failed",e);
 
		}

	}

 

}
