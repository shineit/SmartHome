package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;


/**
 * 
* @ClassName: GetConcentratorListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:24 
*
 */
public class GetConcentratorListReq extends MispBaseReqJson
{
	 
	private PageJson page;
	private int userID;
	private List<AttributeJson> filterList;
	public PageJson getPage()
	{
		return page;
	}
	public void setPage(PageJson page)
	{
		this.page = page;
	}
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
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
		return "GetConcentratorListReq [page=" + page + ", userID=" + userID
				+ ", filterList=" + filterList + "]";
	}
	
	

}
