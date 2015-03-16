package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

public class GetNewsByIDReq extends BaseJsonReq
{
	private String newsID;

	public String getNewsID()
	{
		return newsID;
	}

	public void setNewsID(String newsID)
	{
		this.newsID = newsID;
	}

	@Override
	public String toString()
	{
		return "GetNewsByIDReq [newsID=" + newsID + ", token=" + token + "]";
	}
	
	
}
