package cn.fuego.smart.home.webservice.up.model.enterprise;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

public class GetCheckItemByIDReq extends MispBaseReqJson
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
		return "GetCheckItemByIDReq [companyID=" + companyID + ", token="
				+ token + "]";
	}


}
