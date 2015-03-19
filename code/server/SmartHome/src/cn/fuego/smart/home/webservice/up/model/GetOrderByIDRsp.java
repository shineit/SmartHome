package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;

public class GetOrderByIDRsp extends MispBaseRspJson
{
	private ServiceOrderJson order;

	public ServiceOrderJson getOrder()
	{
		return order;
	}

	public void setOrder(ServiceOrderJson order)
	{
		this.order = order;
	}

	@Override
	public String toString()
	{
		return "GetOrderByIDRsp [order=" + order + ", errorCode=" + errorCode
				+ ", errorMsg=" + errorMsg + ", obj=" + obj + "]";
	}

 


}
