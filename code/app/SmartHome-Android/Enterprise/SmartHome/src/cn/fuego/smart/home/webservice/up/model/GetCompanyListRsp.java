package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;

/** 
* @ClassName: GetCompanyListRsp 
* @Description: TODO
* @author Aether
* @date 2015-3-13 上午10:17:00 
*  
*/
public class GetCompanyListRsp extends BaseJsonRsp
{
	private List<CompanyJson> companyList;

	public List<CompanyJson> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyJson> companyList) {
		this.companyList = companyList;
	}

	@Override
	public String toString() {
		return "GetCompanyListRsp [companyList=" + companyList + ", result="
				+ result + "]";
	}





}
