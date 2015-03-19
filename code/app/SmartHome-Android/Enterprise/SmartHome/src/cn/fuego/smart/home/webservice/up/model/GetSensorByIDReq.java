package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

public class GetSensorByIDReq extends MispBaseReqJson
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
