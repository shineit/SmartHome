package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

public class GetCheckItemByIDReq extends BaseJsonReq
{
	private String companyID;

	public String getCompanyID()
	{
		return companyID;
	}

	public void setCompanyID(String companyID) 
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
