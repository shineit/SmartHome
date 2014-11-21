/**   
 * @Title: MispClientInvoker.java 
 * @Package cn.fuego.smart.home 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-11-13 上午9:19:59 
 * @version V1.0   
 */
package cn.fuego.misp.service.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Set;

import javax.ws.rs.Path;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.specimpl.UriBuilderImpl;
import org.jboss.resteasy.util.IsHttpMethod;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.ui.home.HomeFragment;

/**
 * @ClassName: MispClientInvoker
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-13 上午9:19:59
 * 
 */

public class MispHttpClientInvoker extends Thread
{
	
	private FuegoLog log = FuegoLog.getLog(getClass());

	private static final String GET_METHOD = "GET";
	private static final String POST_METHOD = "POST";
	
	private static final String CODE_WITH_UTF_8 = "utf-8";
 	protected UriBuilderImpl uri;
	protected Method method;
	protected Object[] argObjects;
	protected HttpClient httpClient;
	protected Handler handler;
 
	public MispHttpClientInvoker(URI baseUri,Class<?> calzz,Method method,HttpClient httpClient, Handler handler)
	{
		this.uri = new UriBuilderImpl();
		this.handler = handler;
		uri.uri(baseUri);
		if (calzz.isAnnotationPresent(Path.class)) 
		{
			uri.path(calzz);
		}
		if (method.isAnnotationPresent(Path.class))
		{
			uri.path(method);
		}
		 
		this.method = method;
		this.httpClient = httpClient;
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Object rspObj = null;
		try
		{
			
		   HttpUriRequest httpMethod = getHttpMethod(argObjects[0]);
			
			
			
 			HttpResponse response = httpClient.execute(httpMethod); // 发起GET请求
			
			
			String content = EntityUtils.toString(response.getEntity(), CODE_WITH_UTF_8);
			
			ObjectMapper mapper = new ObjectMapper();
			
			rspObj = mapper.readValue(content,method.getReturnType());
			Message msg = new Message();
			msg.obj = rspObj;
			handler.sendMessage(msg);
			 
		} catch (Exception e)
		{
			throw new MISPException("call http failed",e);
		}
		
	}



	public MispHttpClientInvoker invoke(Object[] args)
	{
		this.argObjects = args;
		return this;
//		
//		Object rspObj = null;
//		try
//		{
//			
//		   HttpUriRequest httpMethod = getHttpMethod(args[0]);
//			
//			
//			
// 			HttpResponse response = httpClient.execute(httpMethod); // 发起GET请求
//			
//			
//			String content = EntityUtils.toString(response.getEntity(), CODE_WITH_UTF_8);
//			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			rspObj = mapper.readValue(content,method.getReturnType());
//			 
//		} catch (Exception e)
//		{
//			throw new MISPException("call http failed",e);
//		}
//
//		
//		return rspObj;
	}
	
	private HttpUriRequest  getHttpMethod(Object args)
	{
		Set<String> httpMethods = IsHttpMethod.getHttpMethods(method);
	    if (httpMethods == null || httpMethods.size() != 1)
	    {
	         throw new RuntimeException("You must use at least one, but no more than one http method annotation on: " + method.toString());
	    }
	    
	    String absPath = this.getAbsUrlPath();

		if(GET_METHOD.equals(httpMethods.iterator().next()))
		{
 
			HttpGet getMethod = new HttpGet(absPath);
			return getMethod;
		}
		else if(POST_METHOD.equals(httpMethods.iterator().next()))
		{
			ObjectMapper mapper = new ObjectMapper();
			
			StringEntity se = null;
			try
			{
				se = new StringEntity( mapper.writeValueAsString(args));
	            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

			} catch (Exception e)
			{
				log.error("covert object to json failed.object is " + args,e);
			} 
        
			HttpPost postMethod = new HttpPost(absPath);
			postMethod.setHeader(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			postMethod.setEntity(se);
			return postMethod;
		}
		
		return null;
		
	}
	private String getAbsUrlPath()
	{
		String absPath = uri.getScheme() + "://" + uri.getHost() +":"+ uri.getPort() + uri.getPath();
		return absPath;
	}

}