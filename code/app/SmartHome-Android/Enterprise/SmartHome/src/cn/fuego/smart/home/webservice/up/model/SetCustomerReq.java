package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;


public class SetCustomerReq extends MispBaseReqJson
{
 
	private CustomerJson customer;

	public CustomerJson getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerJson customer)
	{
		this.customer = customer;
	}

	@Override
	public String toString()
	{
		return "SetCustomerReq [customer=" + customer + ", token=" + token
				+ "]";
	}
 

	
}
