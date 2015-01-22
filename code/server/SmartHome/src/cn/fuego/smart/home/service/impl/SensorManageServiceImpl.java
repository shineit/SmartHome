/**   
* @Title: SensorManageSerivceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午2:56:31 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
import cn.fuego.smart.home.constant.SensorStatusEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.device.send.DeviceManager;
import cn.fuego.smart.home.device.send.DeviceManagerFactory;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.service.SensorManageService;
import cn.fuego.smart.home.service.ServiceContext;

 /** 
 * @ClassName: SensorManageSerivceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午2:56:31 
 *  
 */
public class SensorManageServiceImpl extends MispCommonServiceImpl<HomeSensor> implements SensorManageService
{

	private Log log = LogFactory.getLog(SensorManageServiceImpl.class);

 
	public void syncSensorList(long concentorID)
	{
		log.info("update sensor list from concentrator, delete old sensor first. the concentor id " + concentorID);

		Concentrator concentrator = ServiceContext.getInstance().getConcentratorManageService().get(String.valueOf(concentorID));
		DeviceManager device = DeviceManagerFactory.getInstance().getDeviceManger(concentrator);
		List<HomeSensor> sensorList = device.getSensorList();
		
 
		for(HomeSensor sensor : sensorList)
		{
			try
			{
				device.getSensor(sensor);
			
			}
			catch(Exception e)
			{
				log.error("get home sensor config failed,the sensor id is " + sensor.getSensorID() + " ,the channel id is "+sensor.getChannelID());
			}
			
		}
		
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID",concentorID);
		this.getDao().delete(condition);
		
		super.create(sensorList);
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#setSensor(cn.fuego.smart.home.constant.SensorSetCmdEnum, cn.fuego.smart.home.domain.Sensor)
	 */
	@Override
	public void setSensor(SensorSetCmdEnum setCmd, HomeSensor sensor)
	{
		HomeSensor old = this.get(String.valueOf(sensor.getId()));
		
		old.setGroupID(sensor.getGroupID());
		old.setCtrGroupID(sensor.getCtrGroupID());		
		old.setWarnValue(sensor.getWarnValue());
		old.setErrorValue(sensor.getErrorValue());
		old.setMark(sensor.getMark());
		old.setDescription(sensor.getDescription());
		//新增联动控制器ID
		old.setCtrSensorID(sensor.getCtrSensorID());
		old.setCtrChannelID(sensor.getCtrChannelID());
		switch(setCmd)
		{
		case MODIFY:
			super.modify(old);
			break;
		case STOP:
			break;
		case START:
			break;
		default:
		    break;
		
		}
		
	}

 

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#getFireSensor(int, int, int, int)
	 */
	@Override
	public FireSensor getFireSensor(long concentratorID, int machineID,
			int loopID, int codeID)
	{
		// TODO Auto-generated method stub
		return null;
	}

 
 
	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#GetPrimaryName()
	 */
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return HomeSensor.PRI_KEY;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#getHomeSensor(int, int, int)
	 */
	@Override
	public HomeSensor getHomeSensor(long concentratorID, long sensorID, int channelID)
	{
		
	    List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID)); 
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"sensorID",sensorID)); 
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"channelID",channelID)); 
		
		
		return super.getDao().getUniRecord(conditionList);
	}
 
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#disable(java.util.List)
	 */
	@Override
	public void disable(List<String> idList)
	{
	
		List<HomeSensor> sensorList = this.get(idList);
		
		Map<Long,List<HomeSensor>> sensorMap = new HashMap<Long,List<HomeSensor>>();
		
		for(HomeSensor sensor : sensorList)
		{
			sensor.setStatus(SensorStatusEnum.DISABLE.getIntValue());
			List<HomeSensor> temp = sensorMap.get(sensor.getConcentratorID());
			if(null == temp)
			{
				temp = new ArrayList<HomeSensor>();
				temp.add(sensor);
				sensorMap.put(sensor.getConcentratorID(), temp);
			}
			else
			{
				temp.add(sensor);
			}
		}
		
		this.Modify(idList, "status", SensorStatusEnum.DISABLE.getIntValue());
		for (Long key : sensorMap.keySet())
		{
			 Concentrator concentrator = ServiceContext.getInstance().getConcentratorManageService().get(key);
			 DeviceManager device = DeviceManagerFactory.getInstance().getDeviceManger(concentrator);
			 device.disableSensor(sensorMap.get(key));	
		}
 
		
		

		
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#enable(java.util.List)
	 */
	@Override
	public void enable(List<String> idList)
	{
		List<HomeSensor> sensorList = this.get(idList);
		
		Map<Long,List<HomeSensor>> sensorMap = new HashMap<Long,List<HomeSensor>>();
		
		for(HomeSensor sensor : sensorList)
		{
			sensor.setStatus(SensorStatusEnum.ENABLE.getIntValue());
			List<HomeSensor> temp = sensorMap.get(sensor.getConcentratorID());
			if(null == temp)
			{
				temp = new ArrayList<HomeSensor>();
				temp.add(sensor);
				sensorMap.put(sensor.getConcentratorID(), temp);
			}
			else
			{
				temp.add(sensor);
			}
		}
		this.Modify(idList, "status", SensorStatusEnum.ENABLE.getIntValue());
		for (Long key : sensorMap.keySet())
		{
			 Concentrator concentrator = ServiceContext.getInstance().getConcentratorManageService().get(key);
			 DeviceManager device = DeviceManagerFactory.getInstance().getDeviceManger(concentrator);
			 device.enableSensor(sensorMap.get(key));	
		}
	}
	@Override
	public List<HomeSensor> getSensorListByID(List<Long> concentIDList)
	{
		List<HomeSensor> list= new ArrayList<HomeSensor>();
		list= DaoContext.getInstance().getSensorDao().getAll(new QueryCondition(ConditionTypeEnum.IN, "concentratorID", concentIDList));
		return list;
	}


}
