package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;

public class GetSensorByIDRsp extends MispBaseRspJson
{
	private HomeSensorJson sensor;

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
		return "GetSensorByIDRsp [sensor=" + sensor + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg + ", obj=" + obj + "]";
	}

 
}
