package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

public class GetCompanyByIDReq extends MispBaseReqJson
{
	private int companyID;

	public int getCompanyID()
	{
		return companyID;
	}

	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}

	@Override
	public String toString()
	{
		return "GetCompanyByIDReq [companyID=" + companyID + ", app_id="
				+ app_id + ", token=" + token + "]";
	}


	
}
