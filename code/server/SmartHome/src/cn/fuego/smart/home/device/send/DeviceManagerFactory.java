/**   
* @Title: DeviceManagerFactory.java 
* @Package cn.fuego.smart.home.device.send 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-4 下午4:27:16 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.send;

import java.util.HashMap;
import java.util.Map;

import cn.fuego.smart.home.domain.Concentrator;


 /** 
 * @ClassName: DeviceManagerFactory 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-4 下午4:27:16 
 *  
 */
public class DeviceManagerFactory
{
	private static DeviceManagerFactory instance;
	private static Map<Long,DeviceManager> map = new HashMap<Long,DeviceManager>();
	private DeviceManagerFactory()
	{
 
	}

	public static synchronized DeviceManagerFactory getInstance()
	{
		if (null == instance)
		{
			instance = new DeviceManagerFactory();
		}
		return instance;
	}
	
	public DeviceManager getDeviceManger(Concentrator concentrator)
	{
		DeviceManager manager = map.get(concentrator.getConcentratorID());
		if(null == manager)
		{
			manager = new DeviceManagerImpl(concentrator);
		}
		return manager;
	}
}
