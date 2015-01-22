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
 	
 
	void syncSensorList(long concentorID);
	void syncSensor(long id);
	void setSensor(SensorSetCmdEnum setCmd,HomeSensor sensor);
	
	FireSensor getFireSensor(long concentratorID,int machineID,int loopID,int codeID);
	HomeSensor getHomeSensor(long concentratorID,long sensorID,int channelID);
	
	void disable(List<String> sensorList);
	void enable(List<String> sensorList);
	//根据集中器ID查找所属传感器列表
	List<HomeSensor> getSensorListByID(List<Long> concentIDList);

 
}
