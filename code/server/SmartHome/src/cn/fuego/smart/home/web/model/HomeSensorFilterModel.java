/**   
* @Title: HomeSensorFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2015-1-18 下午9:18:09 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import cn.fuego.smart.home.constant.DeviceKindEunm;

/** 
 * @ClassName: HomeSensorFilterModel 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-18 下午9:18:09 
 *  
 */
public class HomeSensorFilterModel
{
	private String concentratorID;
	private String sensorID;
	private String sensorKind;
	private DeviceKindEunm[] sensorKindList = DeviceKindEunm.values();

	
	public String getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(String concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public String getSensorID()
	{
		return sensorID;
	}
	public void setSensorID(String sensorID)
	{
		this.sensorID = sensorID;
	}
	public String getSensorKind()
	{
		return sensorKind;
	}
	public void setSensorKind(String sensorKind)
	{
		this.sensorKind = sensorKind;
	}
	public DeviceKindEunm[] getSensorKindList()
	{
		return sensorKindList;
	}
	public void setSensorKindList(DeviceKindEunm[] sensorKindList)
	{
		this.sensorKindList = sensorKindList;
	} 

}
