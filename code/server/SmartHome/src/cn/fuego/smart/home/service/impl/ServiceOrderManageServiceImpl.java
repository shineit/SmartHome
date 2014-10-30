/**   
* @Title: ServiceOrderManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午3:05:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.util.format.DateUtil;
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
public class ServiceOrderManageServiceImpl implements ServiceOrderManageService
{

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#getNewsDataSource()
	 */
	@Override
	public AbstractDataSource<ServiceOrder> getNewsDataSource()
	{
		AbstractDataSource<ServiceOrder> datasource = new DataBaseSourceImpl<ServiceOrder>(ServiceOrder.class);
		
		return datasource;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#create(cn.fuego.smart.home.domain.ServiceOrder)
	 */
	@Override
	public void create(ServiceOrder order)
	{
		order.setCreateTime(DateUtil.getCurrentDateTime());
		order.setOrderStatus(ServiceOrderStatusEnum.APPLYED.getStatusInt());
		DaoContext.getInstance().getServiceOrderDao().create(order);
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#handle(cn.fuego.smart.home.domain.ServiceOrder)
	 */
	@Override
	public void handle(int orderID,String handler,String handleResult)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, ServiceOrder.getOrderIDAttr(),String.valueOf(orderID));
		ServiceOrder order = (ServiceOrder) DaoContext.getInstance().getServiceOrderDao().getUniRecord(condition);
		order.setHandler(handler);
		order.setHandleResult(handleResult);
		order.setOrderStatus(ServiceOrderStatusEnum.HANDLED.getStatusInt());
		order.setHandleTime(DateUtil.getCurrentDateTime());
		DaoContext.getInstance().getServiceOrderDao().update(order);
		
	}

}
