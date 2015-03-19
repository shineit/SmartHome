package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

public class GetNewsByIDReq extends MispBaseReqJson
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
