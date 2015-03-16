package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;


/**
 * 
* @ClassName: GetNewsListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:47 
*
 */
public class GetNewsListRsp extends BaseJsonRsp
{
	private List<NewsJson> newsList = new ArrayList<NewsJson>();

	public List<NewsJson> getNewsList()
	{
		return newsList;
	}

	public void setNewsList(List<NewsJson> newsList)
	{
		this.newsList = newsList;
	}

	@Override
	public String toString()
	{
		return "GetNewsListRsp [newsList=" + newsList + "]";
	}
	
 
}
