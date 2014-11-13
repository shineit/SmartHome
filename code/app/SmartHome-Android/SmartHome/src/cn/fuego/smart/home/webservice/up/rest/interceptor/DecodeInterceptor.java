/**   
* @Title: EncodeInterceptor.java 
* @Package cn.fuego.smart.home.webservice.from.client.rest.interceptor 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午11:50:19 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;

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
public class DecodeInterceptor  implements MessageBodyReaderInterceptor
{
	private FuegoLog log = FuegoLog.getLog(DecodeInterceptor.class);

	/* (non-Javadoc)
	 * @see org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor#read(org.jboss.resteasy.spi.interception.MessageBodyReaderContext)
	 */
	@Override
	public Object read(MessageBodyReaderContext arg0) throws IOException,
			WebApplicationException
	{

		 int i =0;

		 StringBuffer body = new StringBuffer();
		 while((i = arg0.getInputStream().read())!= -1)
		 {
			 body.append((char)i);
		 }
		 if(null != arg0.getInputStream())
		 {
			 arg0.getInputStream().close();
		 }
		 log.info("the request message is "+body);
		 
 
		 ByteArrayInputStream bais=new ByteArrayInputStream(body.toString().getBytes());
		 arg0.setInputStream(bais);
		 
		return arg0.proceed();
	}

 

}
