package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

public class GetOrderByIDReq extends MispBaseReqJson
{
	private String orderID;

	public String getOrderID()
	{
		return orderID;
	}

	public void setOrderID(String orderID)
	{
		this.orderID = orderID;
	}

	@Override
	public String toString()
	{
		return "GetOrderByIDReq [orderID=" + orderID + ", token=" + token + "]";
	}


	
}
