package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;

public class GetSensorByIDRsp extends BaseJsonRsp
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
		return "GetSensorByIDRsp [sensor=" + sensor + ", result=" + result
				+ "]";
	}

}
