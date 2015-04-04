package cn.fuego.smart.home.webservice.up.model.enterprise;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

public class GetCheckLogNumByIDReq extends MispBaseReqJson
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
		return "GetCheckLogNumByIDReq [userID=" + userID + ", app_id=" + app_id
				+ ", token=" + token + "]";
	}



	
}
