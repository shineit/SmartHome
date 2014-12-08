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
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
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

 
	public void syncSensorList(int concentorID)
	{
		log.info("update sensor list from concentrator, delete old sensor first. the concentor id " + concentorID);

		Concentrator concentrator = ServiceContext.getInstance().getConcentratorManageService().get(String.valueOf(concentorID));
		DeviceManager device = DeviceManagerFactory.getInstance().getDeviceManger(concentrator);
		List<HomeSensor> sensorList = device.getSensorList();
		
		List<HomeSensor> sensorInfoList = new ArrayList<HomeSensor>();
		for(HomeSensor sensor : sensorList)
		{
			HomeSensor e = device.getSesnor(sensor.getSensorID(), sensor.getChannelID());
			sensorInfoList.add(e);
		}
		
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID",concentorID);
		this.getDao().delete(condition);
		
		super.create(sensorInfoList);
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
	public FireSensor getFireSensor(int concentratorID, int machineID,
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
	public HomeSensor getHomeSensor(int concentratorID, int sensorID, int channelID)
	{
		
	    List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID)); 
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"sensorID",sensorID)); 
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"channelID",channelID)); 
		
		
		return super.getDao().getUniRecord(conditionList);
	}


}
