/**   
* @Title: SensorManageSerivceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午2:56:31 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.service.SensorManageService;

 /** 
 * @ClassName: SensorManageSerivceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午2:56:31 
 *  
 */
public class SensorManageServiceImpl implements SensorManageService
{

	private Log log = LogFactory.getLog(SensorManageServiceImpl.class);

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#getSensorDataSource()
	 */
	@Override
	public AbstractDataSource<HomeSensor> getSensorDataSource()
	{
		AbstractDataSource<HomeSensor> datasource = new DataBaseSourceImpl<HomeSensor>(HomeSensor.class);
		
		return datasource;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#getAlarmDataSource(int)
	 */
	@Override
	public AbstractDataSource<Alarm> getAlarmDataSource(int userID)
	{
		AbstractDataSource<Alarm> datasource = new DataBaseSourceImpl<Alarm>(HomeSensor.class);

		return datasource;
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#setSensor(cn.fuego.smart.home.constant.SensorSetCmdEnum, cn.fuego.smart.home.domain.Sensor)
	 */
	@Override
	public void setSensor(SensorSetCmdEnum setCmd, HomeSensor sensor)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#alarm(cn.fuego.smart.home.domain.SensorAlarm)
	 */
	@Override
	public void createAlarm(List<Alarm> alarm)
	{
		// TODO Auto-generated method stub
		
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
	 * @see cn.fuego.smart.home.service.SensorManageService#getHomeSensor(int, int, int)
	 */
	@Override
	public HomeSensor getHomeSensor(int concentratorID, int sensorID,
			int channelID)
	{
		// TODO Auto-generated method stub
		return null;
	}


}
