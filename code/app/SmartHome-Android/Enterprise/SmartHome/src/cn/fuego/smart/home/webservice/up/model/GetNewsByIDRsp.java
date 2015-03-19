package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;

public class GetNewsByIDRsp extends MispBaseRspJson
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
		return "GetNewsByIDRsp [news=" + news + ", errorCode=" + errorCode
				+ ", errorMsg=" + errorMsg + ", obj=" + obj + "]";
	}

 
	
}
