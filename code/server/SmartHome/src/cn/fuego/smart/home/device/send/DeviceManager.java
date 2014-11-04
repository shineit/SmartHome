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

import cn.fuego.smart.home.domain.Concentrator;
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

	/**
	 * 集中器复位
	 * @param concentrator
	 */
	public void reset(Concentrator concentrator);
	
	/**
	 * 集中器清除
	 * @param concentrator
	 */
	public void clear(Concentrator concentrator);
	
	/**
	 * 集中器配置，设置连接服务器的IP地址和端口号
	 * @param concentrator
	 */
	public void setConfig(Concentrator concentrator);
	
	/**
	 * 获取集中器配置
	 * @return
	 */
	public Concentrator getConfig();

	public List<HomeSensor> getSensorList();
	
	public void setSensor(HomeSensor sensor);
	
	public void enableSensor(List<HomeSensor> sensorList);
	
	public void disableSensor(List<HomeSensor> sensorList);
	
	public void startSensor(List<HomeSensor> sensorList);
	
	public void stopSensor(List<HomeSensor> sensorList);
	
	/**
	 * 获取模拟量终端采集数据
	 * @param sensorList
	 */
	public void getSensorData(List<HomeSensor> sensorList);
	
}























