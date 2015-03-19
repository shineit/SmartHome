package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;


/**
 * 
* @ClassName: SetSensorReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:45 
*
 */
public class SetSensorReq extends MispBaseReqJson
{
	private int command;
	private HomeSensorJson sensor;
	private int userID;
 
	public int getCommand()
	{
		return command;
	}
	public void setCommand(int command)
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
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	@Override
	public String toString()
	{
		return "SetSensorReq [command=" + command + ", sensor=" + sensor
				+ ", userID=" + userID + ", token=" + token + "]";
	}

	
	
}
