package cn.fuego.smart.home.webservice.up.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.http.MispProxyFactory;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;

public class WebServiceContext
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);

	private static WebServiceContext instance;

	public static String hostURL = "http://192.168.1.101:9000/SmartHome/rest";

	
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
	public SensorManageRest getSensorManage()
	{
		
		HttpGet getMethod = new HttpGet(hostURL+"/sensor/hello");  
        
		HttpClient httpClient = new DefaultHttpClient();  
		  
		try {  
		    HttpResponse response = httpClient.execute(getMethod); //发起GET请求  
		  
		    log.info("resCode = " + response.getStatusLine().getStatusCode()); //获取响应码  
		    log.info("result = " + EntityUtils.toString(response.getEntity(), "utf-8"));//获取服务器响应内容  
 
		} catch (Exception e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace();  
		} 
		
		  
		SensorManageRest proxy = MispProxyFactory.create( hostURL,SensorManageRest.class, httpClient);

		return proxy;
	}
	
	

}
