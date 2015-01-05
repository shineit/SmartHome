/**   
* @Title: OrderManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:23:13 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.ServiceOrderManageService;
import cn.fuego.smart.home.service.impl.SensorManageServiceImpl;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.GetOrderByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetOrderByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetServiceOrderListReq;
import cn.fuego.smart.home.webservice.up.model.GetServiceOrderListRsp;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderReq;
import cn.fuego.smart.home.webservice.up.model.SetServiceOrderRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.up.rest.OrderManageRest;

 /** 
 * @ClassName: OrderManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:23:13 
 *  
 */
public class OrderManageRestImpl implements OrderManageRest
{
	private Log log = LogFactory.getLog(SensorManageServiceImpl.class);

	private ServiceOrderManageService orderService = ServiceContext.getInstance().getServiceOrderManageService();

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.OrderManageService#getOrderList(cn.fuego.smart.home.webservice.from.client.model.GetServiceOrderListReq)
	 */
	@Override
	public GetServiceOrderListRsp getOrderList(	GetServiceOrderListReq req)
	{
		GetServiceOrderListRsp rsp = new GetServiceOrderListRsp();
		
		List<ServiceOrder> orderList = orderService.getDataSource().getAllPageData();
		for(ServiceOrder e : orderList)
		{
			ServiceOrderJson orderJson = ModelConvert.ServiceOrderToJson(e);
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
		ServiceOrder order = ModelConvert.jsonToServiceOrder(req.getServiceOrder());
		
		try
		{
			orderService.create(order);
		}
		catch(MISPException e)
		{
			rsp.getResult().setErrorCode(e.getErrorCode());
			log.error("create order failed",e);
		}
		catch(Exception e)
		{
			rsp.getResult().setErrorCode(MISPErrorMessageConst.ERROR_MSG_WRONG);
			log.error("create order failed",e);

		}

		return rsp;
	}

	@Override
	public GetOrderByIDRsp getOrder(GetOrderByIDReq req)
	{
		GetOrderByIDRsp rsp = new GetOrderByIDRsp();
		
		try
		{
			ServiceOrder order = orderService.get(req.getOrderID());
			ServiceOrderJson orderJson = ModelConvert.ServiceOrderToJson(order);
		    rsp.setOrder(orderJson);
		}
		catch(MISPException e)
		{
			log.error("get alarm error",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get alarm error",e);
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}

}
