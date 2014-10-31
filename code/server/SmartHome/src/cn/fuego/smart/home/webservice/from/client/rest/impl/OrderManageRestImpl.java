/**   
* @Title: OrderManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:23:13 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.rest.impl;

import java.util.List;

import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.ServiceOrderManageService;
import cn.fuego.smart.home.webservice.from.client.model.GetServiceOrderListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetServiceOrderListRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetServiceOrderReq;
import cn.fuego.smart.home.webservice.from.client.model.SetServiceOrderRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.from.client.rest.OrderManageRest;

 /** 
 * @ClassName: OrderManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:23:13 
 *  
 */
public class OrderManageRestImpl implements OrderManageRest
{
	private ServiceOrderManageService orderService = ServiceContext.getInstance().getServiceOrderManageService();

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.OrderManageService#getOrderList(cn.fuego.smart.home.webservice.from.client.model.GetServiceOrderListReq)
	 */
	@Override
	public GetServiceOrderListRsp getOrderList(	GetServiceOrderListReq req)
	{
		GetServiceOrderListRsp rsp = new GetServiceOrderListRsp();
		
		List<ServiceOrder> orderList = orderService.getNewsDataSource().getAllPageData();
		for(ServiceOrder e : orderList)
		{
			ServiceOrderJson orderJson = new ServiceOrderJson();
			orderJson.loadWithOrder(e);
			rsp.getOrderList().add(orderJson);
		}
	 
 		return rsp;
 
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.OrderManageService#setServiceOrder(cn.fuego.smart.home.webservice.from.client.model.SetServiceOrderReq)
	 */
	@Override
	public SetServiceOrderRsp setServiceOrder(SetServiceOrderReq req)
	{
		SetServiceOrderRsp rsp = new SetServiceOrderRsp();
		ServiceOrder order = req.getServiceOrder().getSergviceOrder();
		
		try
		{
			orderService.create(order);
		}
		catch(MISPException e)
		{
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			rsp.getResult().setErrorCode(MISPErrorMessageConst.ERROR_MSG_WRONG);

		}

		return rsp;
	}

}
