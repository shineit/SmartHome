/**   
* @Title: HomeSensorFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2015-1-18 下午9:18:09 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import cn.fuego.smart.home.constant.SensorKindEunm;

/** 
 * @ClassName: HomeSensorFilterModel 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-18 下午9:18:09 
 *  
 */
public class HomeSensorFilterModel
{
	private String sensorID;
	private String sensorKind;
	private SensorKindEunm[] sensorKindList = SensorKindEunm.values();

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
	public SensorKindEunm[] getSensorKindList()
	{
		return sensorKindList;
	}
	public void setSensorKindList(SensorKindEunm[] sensorKindList)
	{
		this.sensorKindList = sensorKindList;
	} 

}
