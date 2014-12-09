package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

public class ClearAlarmByIDReq extends BaseJsonReq
{
	private String alarmID;

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
		return "ClearAlarmByIDReq [alarmID=" + alarmID + ", token=" + token + "]";
	}
	
}
