package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;


/**
 * 
* @ClassName: GetServiceOrderListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:06 
*
 */
public class GetServiceOrderListRsp extends BaseJsonRsp
{
	private List<ServiceOrderJson> orderList = new ArrayList<ServiceOrderJson>();

	public List<ServiceOrderJson> getOrderList()
	{
		return orderList;
	}

	public void setOrderList(List<ServiceOrderJson> orderList)
	{
		this.orderList = orderList;
	}
 
	

}
