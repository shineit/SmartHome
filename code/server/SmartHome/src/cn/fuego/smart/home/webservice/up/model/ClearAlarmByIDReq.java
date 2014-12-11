package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

public class ClearAlarmByIDReq extends BaseJsonReq
{
	private int userID;
	private String alarmID;

	
	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	public String getAlarmID()
	{
		return alarmID;
	}

	public void setAlarmID(String alarmID)
	{
		this.alarmID = alarmID;
	}

	@Override
	public String toString()
	{
		return "ClearAlarmByIDReq [userID=" + userID + ", alarmID=" + alarmID
				+ ", token=" + token + "]";
	}

 
	
}
