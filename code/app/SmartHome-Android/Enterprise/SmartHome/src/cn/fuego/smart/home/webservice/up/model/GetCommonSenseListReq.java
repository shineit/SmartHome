package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;


/** 
* @ClassName: GetCommonSenseListReq 
* @Description: TODO
* @author Aether
* @date 2015-3-18 下午3:25:39 
*  
*/
public class GetCommonSenseListReq extends BaseJsonReq
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
