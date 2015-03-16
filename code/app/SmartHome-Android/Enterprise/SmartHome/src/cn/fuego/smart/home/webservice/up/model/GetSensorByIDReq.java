package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

public class GetSensorByIDReq extends BaseJsonReq
{
	private String sensorID;

	public String getSensorID()
	{
		return sensorID;
	}

	public void setSensorID(String sensorID)
	{
		this.sensorID = sensorID;
	}

	@Override
	public String toString()
	{
		return "GetSensorByIDReq [sensorID=" + sensorID + ", token=" + token
				+ "]";
	}
	

}
