package cn.fuego.smart.home.webservice.up.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispProxyFactory;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;

public class WebServiceContext
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);

	private static WebServiceContext instance;

	public static String hostURL = "http://192.168.1.103:8080/SmartHome/rest";
	
	private WebServiceContext()
	{

	}

	public static synchronized WebServiceContext getInstance()
	{
		if (null == instance)
		{
			instance = new WebServiceContext();
		}
		return instance;
	}
	public SensorManageRest getSensorManageRest(Handler handler)
	{
		
		SensorManageRest rest = MispProxyFactory.create( hostURL,SensorManageRest.class, getHttpClient(),handler);
		
		return rest;
	}
	
	public NewsManageRest getNewsManageRest(Handler handler)
	{
 
		NewsManageRest rest = MispProxyFactory.create( hostURL,NewsManageRest.class, getHttpClient(),handler);

		return rest;
	}
	private HttpClient getHttpClient()
	{
		HttpClient httpClient = new DefaultHttpClient();  

		return httpClient;

	}
	
	

}
