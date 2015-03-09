/**   
* @Title: KeepAliveTimeoutHandler.java 
* @Package cn.fuego.smart.home.device.listenser.mina 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-5 下午8:05:03 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import cn.fuego.common.log.FuegoLog;

 /** 
 * @ClassName: KeepAliveTimeoutHandler 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-5 下午8:05:03 
 *  
 */
public class KeepAliveTimeoutHandler implements KeepAliveRequestTimeoutHandler
{
	private final FuegoLog log = FuegoLog.getLog(getClass());

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler#keepAliveRequestTimedOut(org.apache.mina.filter.keepalive.KeepAliveFilter, org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session)
			throws Exception
	{
		log.warn("the client time out," + session.getRemoteAddress());
		session.close(true );   

	}
	
	

}
