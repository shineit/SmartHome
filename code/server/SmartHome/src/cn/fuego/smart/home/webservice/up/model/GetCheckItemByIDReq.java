package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

public class GetCheckItemByIDReq extends BaseJsonReq
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
