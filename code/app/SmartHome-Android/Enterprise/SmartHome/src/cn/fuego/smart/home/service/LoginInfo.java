package cn.fuego.smart.home.service;

import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;

public class LoginInfo
{
	private SystemUser user;
	
	private CustomerJson customer;
	
	public SystemUser getUser()
	{
		return user;
	}

	public void setUser(SystemUser user)
	{
		this.user = user;
	}

	public CustomerJson getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerJson customer)
	{
		this.customer = customer;
	}

	

}
