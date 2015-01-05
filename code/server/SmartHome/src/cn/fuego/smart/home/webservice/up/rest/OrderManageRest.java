package cn.fuego.smart.home.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.GetOrderByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetOrderByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetServiceOrderListReq;
import cn.fuego.smart.home.webservice.up.model.GetServiceOrderListRsp;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderReq;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderRsp;

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
@Consumes("application/json")  
public interface OrderManageRest
{
	@POST
	@Path("/list")
	public GetServiceOrderListRsp getOrderList(GetServiceOrderListReq req);
	
	@POST
	@Path("/set")
	public SetServiceOrderRsp setServiceOrder(SetServiceOrderReq req);

	@POST	
	@Path("/id")
	public GetOrderByIDRsp getOrder(GetOrderByIDReq req);
	
}
