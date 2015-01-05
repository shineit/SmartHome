package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;

public class GetOrderByIDReq extends BaseJsonReq
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
