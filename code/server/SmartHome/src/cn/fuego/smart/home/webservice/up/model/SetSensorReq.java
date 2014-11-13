package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;


/**
 * 
* @ClassName: SetSensorReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:45 
*
 */
public class SetSensorReq extends BaseJsonReq
{
	private String command;
	private HomeSensorJson sensor;
	public String getCommand()
	{
		return command;
	}
	public void setCommand(String command)
	{
		this.command = command;
	}
	public HomeSensorJson getSensor()
	{
		return sensor;
	}
	public void setSensor(HomeSensorJson sensor)
	{
		this.sensor = sensor;
	}
	@Override
	public String toString()
	{
		return "SetSensorReq [command=" + command + ", sensor=" + sensor + "]";
	}
	
	
}
