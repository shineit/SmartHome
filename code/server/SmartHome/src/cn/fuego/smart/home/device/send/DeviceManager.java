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

import cn.fuego.smart.home.domain.Sensor;

 /** 
 * @ClassName: DeviceManager 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-25 上午12:26:05 
 *  
 */
public interface DeviceManager
{

	public List<Sensor> getSensorList(String deviceIP);
	
	public void setSensor(Sensor sensor);
	
	public void enableSensor(List<Sensor> sensorList);
	
	public void disableSensor(List<Sensor> sensorList);
	
	public void startSensor(List<Sensor> sensorList);
	
	public void stopSensor(List<Sensor> sensorList);
	
	
}























