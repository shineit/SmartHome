/**   
* @Title: EncodeInterceptor.java 
* @Package cn.fuego.smart.home.webservice.from.client.rest.interceptor 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午11:50:19 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.rest.interceptor;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;

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
	private Log log = LogFactory.getLog(EncodeInterceptor.class);

	/* (non-Javadoc)
	 * @see org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor#write(org.jboss.resteasy.spi.interception.MessageBodyWriterContext)
	 */
	@Override
	public void write(MessageBodyWriterContext arg0) throws IOException,
			WebApplicationException
	{
		
		log.info("response is "+arg0.getEntity().toString());
		arg0.proceed();
		
	}

}
