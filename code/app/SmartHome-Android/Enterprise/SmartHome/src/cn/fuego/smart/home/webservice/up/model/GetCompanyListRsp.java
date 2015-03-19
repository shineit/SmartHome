package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;

/** 
* @ClassName: GetCompanyListRsp 
* @Description: TODO
* @author Aether
* @date 2015-3-13 上午10:17:00 
*  
*/
public class GetCompanyListRsp extends MispBaseRspJson
{
	
	private List<CompanyJson> companyList= new ArrayList<CompanyJson>();

	public List<CompanyJson> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyJson> companyList) {
		this.companyList = companyList;
	}

	@Override
	public String toString()
	{
		return "GetCompanyListRsp [companyList=" + companyList + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg + ", obj=" + obj + "]";
	}

 




}
