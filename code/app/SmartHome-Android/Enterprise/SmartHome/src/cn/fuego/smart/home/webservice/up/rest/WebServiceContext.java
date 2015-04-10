package cn.fuego.smart.home.webservice.up.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.service.http.MispProxyFactory;
import cn.fuego.smart.home.webservice.up.rest.interceptor.AuthInterceptor;

public class WebServiceContext
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);

	private static WebServiceContext instance;
	public static String hostURL = MemoryCache.getRestUrl();

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
	public MispSystemManageRest getSystemManageRest(HttpListener handler)
	{
 
		MispSystemManageRest rest = MispProxyFactory.create( hostURL,MispSystemManageRest.class, getHttpClient(),handler);

		return rest;
	}	
	public SensorManageRest getSensorManageRest(HttpListener handler)
	{
		
		SensorManageRest rest = MispProxyFactory.create( hostURL,SensorManageRest.class, getHttpClient(),handler);
		
		return rest;
	}
	
	public NewsManageRest getNewsManageRest(HttpListener handler)
	{
 
		NewsManageRest rest = MispProxyFactory.create( hostURL,NewsManageRest.class, getHttpClient(),handler);

		return rest;
	}
	public CompanyManageRest getCompanyManageRest(HttpListener handler)
	{
 
		CompanyManageRest rest = MispProxyFactory.create( hostURL,CompanyManageRest.class, getHttpClient(),handler);

		return rest;
	}
	private HttpClient getHttpClient()
	{
		HttpClient httpClient = new DefaultHttpClient();
		//请求超时
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		//读取超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		
		log.info("连接超时");
		return httpClient;

	}
	public UserManageRest getUserManageRest(HttpListener handler)
	{
 
		UserManageRest rest = MispProxyFactory.create( hostURL,UserManageRest.class, getHttpClient(),handler);

		return rest;
	}	

	public  OrderManageRest getOrderManageRest(HttpListener handler)
	{
		OrderManageRest rest = MispProxyFactory.create( hostURL,OrderManageRest.class, getHttpClient(),handler);
		return rest;
	}	
	public  ConcentManageRest getConcentManageRest(HttpListener handler)
	{
		ConcentManageRest rest = MispProxyFactory.create( hostURL,ConcentManageRest.class, getHttpClient(),handler);
		return rest;
	}

	public  CheckManageRest getCheckManageRest(HttpListener handler)
	{
		CheckManageRest rest = MispProxyFactory.create( hostURL,CheckManageRest.class, getHttpClient(),handler);
		return rest;
	}
	
	public  KnowledgeManageRest getKnowledgeManageRest(HttpListener handler)
	{
		KnowledgeManageRest rest = MispProxyFactory.create( hostURL,KnowledgeManageRest.class, getHttpClient(),handler);
		return rest;
	}
	
	public  MallManageRest getMallManageRest(HttpListener handler)
	{
		MallManageRest rest = MispProxyFactory.create( hostURL,MallManageRest.class, getHttpClient(),handler);
		return rest;
	}
}
