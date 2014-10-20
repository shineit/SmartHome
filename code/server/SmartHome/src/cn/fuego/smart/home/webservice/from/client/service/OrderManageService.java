package cn.fuego.smart.home.webservice.from.client.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ProduceMime;

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
@ProduceMime({ "application/json" })
public interface OrderManageService
{
	@POST
	@Path("/list")
	public GetServiceOrderListRsp getOrderList(GetServiceOrderListReq orderListReq);
	
	@POST
	@Path("/set")
	public SetServiceOrderRsp setServiceOrder(SetServiceOrderReq orderReq);


}
