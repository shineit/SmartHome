package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;

public class GetFireAlarmByIDReq extends MispBaseReqJson
{
	private int userID;
	private String companyID;
	private PageJson page;
	private List<AttributeJson> filterList;
	public String getCompanyID()
	{
		return companyID;
	}

	public void setCompanyID(String companyID) 
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
		return "GetFireAlarmByIDReq [userID=" + userID + ", companyID="
				+ companyID + ", page=" + page + ", filterList=" + filterList
				+ ", app_id=" + app_id + ", token=" + token + "]";
	}





}
