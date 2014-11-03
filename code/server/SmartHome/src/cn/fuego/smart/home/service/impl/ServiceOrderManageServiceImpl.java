/**   
* @Title: ServiceOrderManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午3:05:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.ServiceOrderStatusEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.service.ServiceOrderManageService;
import cn.fuego.smart.home.web.model.OrderFilterModel;

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
	private Log log = LogFactory.getLog(ServiceOrderManageServiceImpl.class);
	@Override
	
	public AbstractDataSource<ServiceOrder> getOrderDataSource(	OrderFilterModel orderFilter)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(null != orderFilter)
		{
			if(!ValidatorUtil.isEmpty(orderFilter.getOrderID()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"orderID",orderFilter.getOrderID()));
			}
			if(!ValidatorUtil.isEmpty(orderFilter.getOrderName()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"orderName",orderFilter.getOrderName()));
			}
			if(!ValidatorUtil.isEmpty(orderFilter.getOrderStatus()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"orderStatus",String.valueOf(ServiceOrderStatusEnum.getEnumByStr(orderFilter.getOrderStatus()).getIntValue())));
			}
			if(!ValidatorUtil.isEmpty(orderFilter.getStartDate()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"createTime",orderFilter.getStartDate()));
			}
			if(!ValidatorUtil.isEmpty(orderFilter.getEndDate()))
			{
				Date endDate = DateUtil.stringToDate(orderFilter.getEndDate());
				conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER_EQ,"createTime",DateUtil.dayCalculate(endDate, 1).toString()));
			}				
		}
		
		AbstractDataSource<ServiceOrder> datasource = new DataBaseSourceImpl<ServiceOrder>(ServiceOrder.class,conditionList);
		
		return datasource;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#create(cn.fuego.smart.home.domain.ServiceOrder)
	 */
	@Override
	public void create(ServiceOrder order)
	{
		order.setCreateTime(DateUtil.getCurrentDateTime());
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




}
