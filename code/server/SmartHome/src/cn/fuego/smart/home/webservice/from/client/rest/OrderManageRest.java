package cn.fuego.smart.home.webservice.from.client.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.from.client.model.GetServiceOrderListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetServiceOrderListRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetServiceOrderReq;
import cn.fuego.smart.home.webservice.from.client.model.SetServiceOrderRsp;

/**
 * 
* @ClassName: OrderManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:53:37 
*
 */

@Path("/order")
@Produces("application/json")  
public interface OrderManageRest
{
	@POST
	@Path("/list")
	public GetServiceOrderListRsp getOrderList(GetServiceOrderListReq req);
	
	@POST
	@Path("/set")
	public SetServiceOrderRsp setServiceOrder(SetServiceOrderReq req);


}
