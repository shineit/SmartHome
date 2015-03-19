package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.SensorPlanJson;

public class GetSensorPlanByIDRsp extends MispBaseRspJson
{
	private SensorPlanJson plan;

	public SensorPlanJson getPlan()
	{
		return plan;
	}

	public void setPlan(SensorPlanJson plan)
	{
		this.plan = plan;
	}

 
 
	

	
}
