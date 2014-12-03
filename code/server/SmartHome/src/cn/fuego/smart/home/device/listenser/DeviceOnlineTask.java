/**   
* @Title: DeviceOnlineTask.java 
* @Package cn.fuego.smart.home.device.listenser 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 下午5:36:33 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser;

import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	private Map<String,Long> deviceCache;
	
	public DeviceOnlineTask(Map<String,Long> deviceCache )
	{
		this.deviceCache = deviceCache;
	}
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run()
	{
		log.info("checking device is online or not");
		for (Map.Entry<String, Long> entry : deviceCache.entrySet()) 
		{
			long nowTime = System.currentTimeMillis();
			if(nowTime -30000 > entry.getValue())
			{
				log.warn("the device is off line " + entry.getKey());
			}
 		}
		
	}

}
