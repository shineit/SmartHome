package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

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
