package cn.fuego.smart.home.webservice.from.client.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.NewsJson;


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
	
 
}
