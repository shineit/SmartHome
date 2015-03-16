package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;

/** 
* @ClassName: GetCustomerByIDRsp 
* @Description: TODO
* @author Aether
* @date 2015-1-13 上午12:04:22 
*  
*/ 
public class GetCustomerByIDRsp extends BaseJsonRsp
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
		return "GetCustomerByIDRsp [customer=" + customer + ", result="
				+ result + "]";
	}
	
	
}
