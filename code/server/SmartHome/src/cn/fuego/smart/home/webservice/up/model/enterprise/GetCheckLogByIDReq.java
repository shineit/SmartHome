package cn.fuego.smart.home.webservice.up.model.enterprise;

import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;

public class GetCheckLogByIDReq extends MispBaseReqJson
{
	private int userID;
	private int companyID=0;
	private PageJson page;
	private List<AttributeJson> filterList;


	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	public int getCompanyID()
	{
		return companyID;
	}

	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}

	public PageJson getPage()
	{
		return page;
	}

	public void setPage(PageJson page)
	{
		this.page = page;
	}

	public List<AttributeJson> getFilterList()
	{
		return filterList;
	}

	public void setFilterList(List<AttributeJson> filterList)
	{
		this.filterList = filterList;
	}

	@Override
	public String toString()
	{
		return "GetCheckLogByIDReq [userID=" + userID + ", companyID="
				+ companyID + ", page=" + page + ", filterList=" + filterList
				+ ", app_id=" + app_id + ", token=" + token + "]";
	}



}
