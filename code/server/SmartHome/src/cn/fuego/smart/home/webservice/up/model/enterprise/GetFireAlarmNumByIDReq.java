package cn.fuego.smart.home.webservice.up.model.enterprise;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

public class GetFireAlarmNumByIDReq extends MispBaseReqJson
{
	private int userID;

	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	@Override
	public String toString()
	{
		return "GetFireAlarmNumByIDReq [userID=" + userID + ", app_id="
				+ app_id + ", token=" + token + "]";
	}


	
}
