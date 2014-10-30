package cn.fuego.smart.home.webservice.from.client.model;

import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.PageJson;


/**
 * 
* @ClassName: GetNewsListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:42 
*
 */
public class GetNewsListReq extends BaseJsonReq
{
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

	@Override
	public String toString()
	{
		return "GetNewsListReq [page=" + page + ", filterList=" + filterList
				+ "]";
	}
	
	
}
