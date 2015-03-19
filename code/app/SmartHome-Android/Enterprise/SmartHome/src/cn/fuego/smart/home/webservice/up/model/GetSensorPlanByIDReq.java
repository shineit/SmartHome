package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;

public class GetSensorPlanByIDReq extends MispBaseReqJson
{
	private int planID;

	public int getPlanID()
	{
		return planID;
	}

	public void setPlanID(int planID)
	{
		this.planID = planID;
	}
	 
 

}
