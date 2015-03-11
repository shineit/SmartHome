/**   
* @Title: DeviceOnlineTask.java 
* @Package cn.fuego.smart.home.device.listenser 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 下午5:36:33 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.smart.home.device.listenser.udp.FuegoUdpServer;

 /** 
 * @ClassName: DeviceOnlineTask 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 下午5:36:33 
 *  
 */
public class DeviceOnlineTask  extends TimerTask 
{
	private Log log = LogFactory.getLog(FuegoUdpServer.class);

	public DeviceOnlineTask()
	{
	}
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run()
	{
		log.info("checking device is online or not");
		DeviceOnlineCache.getInstance().checkOnline();
		
	}
	
	
	

}
