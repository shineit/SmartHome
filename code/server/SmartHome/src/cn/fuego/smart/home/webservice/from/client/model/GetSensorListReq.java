package cn.fuego.smart.home.webservice.from.client.model;

import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.PageJson;


/**
 * 
* @ClassName: GetSensorListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:53 
*
 */
public class GetSensorListReq extends BaseJsonReq
{
 	private int userID;
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
		return "GetSensorListReq [userID=" + userID + ", page=" + page
				+ ", filterList=" + filterList + "]";
	}



}
