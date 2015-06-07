package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.up.model.base.PageJson;


/**
 * 
* @ClassName: GetNewsListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:42 
*
 */
public class GetNewsListReq extends MispBaseReqJson
{
	private String org_id;
	private PageJson page;
 
	private List<AttributeJson> filterList;

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

	public String getOrg_id()
	{
		return org_id;
	}

	public void setOrg_id(String org_id)
	{
		this.org_id = org_id;
	}

	@Override
	public String toString()
	{
		return "GetNewsListReq [org_id=" + org_id + ", page=" + page
				+ ", filterList=" + filterList + "]";
	}


	
	
}
