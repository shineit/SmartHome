/**   
* @Title: DeviceManager.java 
* @Package cn.fuego.smart.home.device.send 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-25 上午12:26:05 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.send;

import java.util.List;

import cn.fuego.smart.home.domain.HomeSensor;

 /** 
 * @ClassName: DeviceManager 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-25 上午12:26:05 
 *  
 */
public interface DeviceManager
{

	public List<HomeSensor> getSensorList();
	
	public void setSensor(HomeSensor sensor);
	
	public void enableSensor(List<HomeSensor> sensorList);
	
	public void disableSensor(List<HomeSensor> sensorList);
	
	public void startSensor(List<HomeSensor> sensorList);
	
	public void stopSensor(List<HomeSensor> sensorList);
	
	
}























