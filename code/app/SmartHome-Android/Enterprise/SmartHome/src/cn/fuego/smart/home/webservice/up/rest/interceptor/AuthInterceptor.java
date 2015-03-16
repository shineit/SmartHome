/**   
* @Title: AuthInterceptor.java 
* @Package cn.fuego.smart.home.webservice.from.client.rest 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午11:39:30 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.interceptor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import cn.fuego.common.log.FuegoLog;

 /** 
 * @ClassName: AuthInterceptor 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午11:39:30 
 *  
 */
@Provider
@ServerInterceptor
@Precedence("SECURITY")
public class AuthInterceptor implements PreProcessInterceptor
{
	private FuegoLog log = FuegoLog.getLog(AuthInterceptor.class);

	/* (non-Javadoc)
	 * @see org.jboss.resteasy.spi.interception.PreProcessInterceptor#preProcess(org.jboss.resteasy.spi.HttpRequest, org.jboss.resteasy.core.ResourceMethod)
	 */
	@Override
	public ServerResponse preProcess(HttpRequest arg0, ResourceMethod arg1)
			throws Failure, WebApplicationException
	{
		log.info("auth");
		log.info("the request url is "+arg0.getUri().getPath());
		log.info("the request call method is  "+ arg1.getMethod());
		return  null;
	}

 
}
