/**   
* @Title: SensorManageService.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午10:58:38 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.HomeSensor;

 /** 
 * @ClassName: SensorManageService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午10:58:38 
 *  
 */
public interface SensorManageService extends MispCommonService<HomeSensor>
{
 	
 
	void syncSensorList(int concentorID);
	void setSensor(SensorSetCmdEnum setCmd,HomeSensor sensor);
	
	FireSensor getFireSensor(int concentratorID,int machineID,int loopID,int codeID);
	HomeSensor getHomeSensor(int concentratorID,int sensorID,int channelID);

 
}
