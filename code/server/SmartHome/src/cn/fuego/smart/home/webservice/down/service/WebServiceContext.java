package cn.fuego.smart.home.webservice.down.service;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.smart.home.webservice.down.service.impl.PushServiceImpl;

public class WebServiceContext
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private static WebServiceContext instance;

	public static String hostURL = "http://channel.api.duapp.com/rest/2.0/channel/channel";
	
	private WebServiceContext()
	{
		log.info("the host and base url is "+hostURL);

	}

	public static synchronized WebServiceContext getInstance()
	{
		if (null == instance)
		{
			instance = new WebServiceContext();
		}
		return instance;
	}
	
	public PushService getPushService()
	{
		return new PushServiceImpl();
	}
 
	
	private HttpClient getHttpClient()
	{
		HttpClient httpClient = new DefaultHttpClient();  

		return httpClient;

	}
 
}
