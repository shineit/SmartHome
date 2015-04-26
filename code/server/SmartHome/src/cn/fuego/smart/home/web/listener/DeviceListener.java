/**   
* @Title: DeviceListener.java 
* @Package cn.fuego.smart.home.web.listener 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 上午10:38:41 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.smart.home.device.listenser.mina.MinaServer;

 /** 
 * @ClassName: DeviceListener 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 上午10:38:41 
 *  
 */
public class DeviceListener implements ServletContextListener
{

    private final FuegoLog log = FuegoLog.getLog(getClass());

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{

		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		try
		{
			log.info("now start device message server");
			int port = Integer.valueOf(SystemConfigInfo.getServerPort());
			MinaServer server = MinaServer.getInstance();
			server.init(port);
			server.start();
			log.info("server load sucess");
		}
		catch(Exception e)
		{
			log.error("device listner failed", e);
		}

		
	} 

}
