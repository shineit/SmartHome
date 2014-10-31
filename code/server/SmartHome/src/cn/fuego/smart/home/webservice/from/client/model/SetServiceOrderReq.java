package cn.fuego.smart.home.webservice.from.client.model;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.ServiceOrderJson;

/**
 * 
* @ClassName: SetSerivceOrderReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:52:08 
*
 */
public class SetServiceOrderReq extends BaseJsonReq
{
	private String command;
    private ServiceOrderJson serviceOrder;
	public String getCommand()
	{
		return command;
	}
	public void setCommand(String command)
	{
		this.command = command;
	}
	public ServiceOrderJson getServiceOrder()
	{
		return serviceOrder;
	}
	public void setServiceOrder(ServiceOrderJson serviceOrder)
	{
		this.serviceOrder = serviceOrder;
	}
    
    
}
