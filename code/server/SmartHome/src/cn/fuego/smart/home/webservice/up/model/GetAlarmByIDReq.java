package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

public class GetAlarmByIDReq extends BaseJsonReq
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
		return "GetAlarmByIDReq [alarmID=" + alarmID + ", token=" + token + "]";
	}
	
}
