/**   
* @Title: KeepAliveMessageFactoryImpl.java 
* @Package cn.fuego.smart.home.device.listenser.mina 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-5 下午7:49:42 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import cn.fuego.common.log.FuegoLog;

 /** 
 * @ClassName: KeepAliveMessageFactoryImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-5 下午7:49:42 
 *  
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory
{
	private final FuegoLog log = FuegoLog.getLog(getClass());

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getRequest(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public Object getRequest(IoSession arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getResponse(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public Object getResponse(IoSession arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isRequest(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public boolean isRequest(IoSession arg0, Object arg1)
	{
		log.info("the keep alive coming");
		return false;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isResponse(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public boolean isResponse(IoSession arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
