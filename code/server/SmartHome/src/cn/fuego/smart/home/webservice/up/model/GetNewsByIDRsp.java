package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;

public class GetNewsByIDRsp extends BaseJsonRsp
{
	private NewsJson news;

	public NewsJson getNews()
	{
		return news;
	}

	public void setNews(NewsJson news)
	{
		this.news = news;
	}

	@Override
	public String toString()
	{
		return "GetNewsByIDRsp [news=" + news + ", result=" + result + "]";
	}
	
}
