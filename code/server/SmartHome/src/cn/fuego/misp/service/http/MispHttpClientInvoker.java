/**   
 * @Title: MispClientInvoker.java 
 * @Package cn.fuego.smart.home 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-11-13 上午9:19:59 
 * @version V1.0   
 */
package cn.fuego.misp.service.http;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Set;

import javax.ws.rs.Path;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.specimpl.UriBuilderImpl;
import org.jboss.resteasy.util.IsHttpMethod;

import cn.fuego.misp.service.MISPException;

/**
 * @ClassName: MispClientInvoker
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-13 上午9:19:59
 * 
 */
public class MispHttpClientInvoker
{
	private static final String GET_METHOD = "GET";
	private static final String POST_METHOD = "POST";
	
	private static final String CODE_WITH_UTF_8 = "utf-8";
 	protected UriBuilderImpl uri;
	protected Method method;

	protected HttpClient httpClient;
 
	public MispHttpClientInvoker(URI baseUri,Class<?> calzz,Method method,HttpClient httpClient)
	{
		this.uri = new UriBuilderImpl();
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
	public Object invoke(Object[] args)
	{
 
		Object rspObj = null;
		try
		{
			HttpUriRequest httpMethod = getHttpMethod();
 			HttpResponse response = httpClient.execute(httpMethod); // 发起GET请求
			
			ObjectMapper mapper = new ObjectMapper();
			String content = EntityUtils.toString(response.getEntity(), CODE_WITH_UTF_8);
			
			rspObj = mapper.readValue(content,method.getReturnType());
			 
		} catch (Exception e)
		{
			throw new MISPException("call http failed");
		}

		
		return rspObj;
	}
	
	private HttpUriRequest  getHttpMethod()
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
			HttpPost postMethod = new HttpPost(absPath);
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
