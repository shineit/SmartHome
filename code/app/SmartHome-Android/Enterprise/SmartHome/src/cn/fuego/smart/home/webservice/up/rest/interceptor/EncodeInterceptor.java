/**   
* @Title: EncodeInterceptor.java 
* @Package cn.fuego.smart.home.webservice.from.client.rest.interceptor 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午11:50:19 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.interceptor;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;

import cn.fuego.common.log.FuegoLog;

 /** 
 * @ClassName: EncodeInterceptor 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午11:50:19 
 *  
 */
@Provider
@ServerInterceptor
@Precedence("ENCODER")
public class EncodeInterceptor  implements MessageBodyWriterInterceptor
{
	private FuegoLog log = FuegoLog.getLog(EncodeInterceptor.class);

	/* (non-Javadoc)
	 * @see org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor#write(org.jboss.resteasy.spi.interception.MessageBodyWriterContext)
	 */
	@Override
	public void write(MessageBodyWriterContext arg0) throws IOException,
			WebApplicationException
	{
		
		log.info("response is "+  arg0.getEntity());
		arg0.proceed();
		
 
	}

}
