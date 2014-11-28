/**   
* @Title: ServiceOrderManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午3:05:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.service.IDCreateService;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.ServiceOrderStatusEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.service.ServiceOrderManageService;

 /** 
 * @ClassName: ServiceOrderManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午3:05:35 
 *  
 */
public class ServiceOrderManageServiceImpl extends MispCommonServiceImpl<ServiceOrder> implements ServiceOrderManageService
{

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#getNewsDataSource()
	 */
	private Log log = LogFactory.getLog(ServiceOrderManageServiceImpl.class);
	 
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#create(cn.fuego.smart.home.domain.ServiceOrder)
	 */
	@Override
	public void create(ServiceOrder order)
	{
		order.setOrderID(MISPServiceContext.getInstance().getIDCreateService(IDCreateService.ORDER_ID_NAME).create());
		order.setCreateTime(DateUtil.getCurrentDateTime());
		order.setHandleTime(null);
		order.setOrderStatus(ServiceOrderStatusEnum.APPLYED.getIntValue());
		DaoContext.getInstance().getServiceOrderDao().create(order);
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#handle(cn.fuego.smart.home.domain.ServiceOrder)
	 */
	@Override
	public void handle(String orderID,String handler,String handleResult)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, ServiceOrder.getOrderIDAttr(),orderID);
		ServiceOrder order = (ServiceOrder) DaoContext.getInstance().getServiceOrderDao().getUniRecord(condition);
		order.setHandler(handler);
		order.setHandleResult(handleResult);
		order.setOrderStatus(ServiceOrderStatusEnum.HANDLED.getIntValue());
		order.setHandleTime(DateUtil.getCurrentDateTime());
		DaoContext.getInstance().getServiceOrderDao().update(order);
		
	}
	@Override
	public ServiceOrder getOrderById(String orderID)
	{

		ServiceOrder order= (ServiceOrder) DaoContext.getInstance().getServiceOrderDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL, "orderID", orderID));

		return order;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#GetPrimaryName()
	 */
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return ServiceOrder.PRI_KEY;
	}




}
