package cn.fuego.smart.home.webservice.from.client.model;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.SensorJson;


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
	private SensorJson sensor;
	public String getCommand()
	{
		return command;
	}
	public void setCommand(String command)
	{
		this.command = command;
	}
	public SensorJson getSensor()
	{
		return sensor;
	}
	public void setSensor(SensorJson sensor)
	{
		this.sensor = sensor;
	}
	@Override
	public String toString()
	{
		return "SetSensorReq [command=" + command + ", sensor=" + sensor + "]";
	}
	
	
}
