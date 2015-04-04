package cn.fuego.smart.home.webservice.up.model.enterprise;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;

public class GetCompanyByIDRsp extends MispBaseRspJson
{
	
	private CompanyJson company;

	public CompanyJson getCompany()
	{
		return company;
	}

	public void setCompany(CompanyJson company)
	{
		this.company = company;
	}

	@Override
	public String toString()
	{
		return "GetCompanyByIDRsp [company=" + company + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg + ", obj=" + obj + "]";
	}
	

	
}
