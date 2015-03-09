/**   
* @Title: DeviceOnlineCache.java 
* @Package cn.fuego.smart.home.device.listenser 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-23 下午3:52:20 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.smart.home.constant.ConcentratorStatusEnum;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.service.ServiceContext;

 /** 
 * @ClassName: DeviceOnlineCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-23 下午3:52:20 
 *  
 */
public class DeviceOnlineCache
{
	private Log log = LogFactory.getLog(DeviceOnlineCache.class);

	private Map<Concentrator,Long> deviceCache = new ConcurrentHashMap<Concentrator,Long>();
	
	private static final DeviceOnlineCache instance = new DeviceOnlineCache();
	
	private DeviceOnlineCache()
	{
		
	}
	
	public static DeviceOnlineCache getInstance()
	{
		return instance;
	}
	
	public void checkOnline()
	{
		log.info("checking device is online or not");
		for (Concentrator entry : deviceCache.keySet()) 
		{
			log.info("the concentrator is " + entry);
			long nowTime = System.currentTimeMillis();
			if(nowTime -30000 > deviceCache.get(entry))
			{
				entry.setStatus(ConcentratorStatusEnum.OFFLINE.getIntValue());
				log.warn("the device is off line. the concentrator is  " + entry);
				ServiceContext.getInstance().getConcentratorManageService().offline(entry.getIpAddr(),entry.getPort());
			}
 		}
	}
	
	public void refresh(Concentrator concentrator)
	{

		log.info("refresh the latest time for concentrator " + concentrator.getConcentratorID());
		Concentrator old = getConcentrator(concentrator.getConcentratorID());
		if(null == old)
		{
			log.info("can not get concentor from data cache,now get from database");
			old = ServiceContext.getInstance().getConcentratorManageService().get(concentrator.getConcentratorID());
		}
		if(null != old)
		{
			this.deviceCache.put(old, System.currentTimeMillis());	
			if(ConcentratorStatusEnum.ONLINE.getIntValue() != old.getStatus())
			{
				log.info("the status in database is wrong, should update it");
				old.setStatus(ConcentratorStatusEnum.ONLINE.getIntValue());
				ServiceContext.getInstance().getConcentratorManageService().modify(old);
			}
		}
		else
		{
			log.warn("can not find the concentor,it is not online, the concentor id is "+ concentrator.getConcentratorID());
			 
		}

		
	}

	
	public void online(Concentrator concentrator)
	{
		log.info("the concentor is online " + concentrator);
		Concentrator old = getConcentrator(concentrator.getConcentratorID());
		
		if(null != old)
		{
			if((!old.getIpAddr().equals(concentrator.getIpAddr())) || old.getPort() != concentrator.getPort())
			{
				log.warn("the ip address or port changed");
			}
			this.deviceCache.remove(old);
		}
		this.deviceCache.put(concentrator, System.currentTimeMillis());

		ServiceContext.getInstance().getConcentratorManageService().online(concentrator);
 
	}
	
	public Concentrator getConcentrator(long id)
	{
		log.info(deviceCache.size());
		for (Concentrator entry : deviceCache.keySet()) 
		{
			if(entry.getConcentratorID() == id)
			{
				return entry;
			}
 
 		}
		return null;
	}
	
	public boolean isOnline(Concentrator concentrator)
	{
		if(!this.deviceCache.containsKey(concentrator))
		{
			return false;
		}
		long nowTime = System.currentTimeMillis();
		long lastTime = this.deviceCache.get(concentrator);
		if(nowTime -30000 > lastTime)
		{
			log.warn("the device is off line. the concentrator is  " + concentrator);

			return false;
		}
		return true;
	}

}
