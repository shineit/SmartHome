/**   
* @Title: ServiceManageAction.java 
* @Package cn.fuego.smart.home.web.action.info 
* @Description: TODO
* @author Aether
* @date 2014-11-1 上午10:15:44 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.info;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.constant.HandleStatusEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.ServiceOrderManageService;
import cn.fuego.smart.home.web.model.OrderFilterModel;

/** 
 * @ClassName: ServiceManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-1 上午10:15:44 
 *  
 */ 
public class OrderManageAction  extends DWZTableAction<ServiceOrder>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(OrderManageAction.class);
	private ServiceOrderManageService orderService = ServiceContext.getInstance().getServiceOrderManageService();
	private TableDataModel<ServiceOrder> serviceOrderTable = new  TableDataModel<ServiceOrder>();
	private OrderFilterModel filter = new OrderFilterModel();
	private ServiceOrder order;
	public static final String PARENT_PAGE="info/OrderManage"; 
	@Override
	public List<QueryCondition> getFilterCondition()
	{
	      	
	    return this.filter.getConidtionList();
	    	
	}
 

	@Override
	public String modify()
	{
		
 		orderService.handle(this.getObj().getOrderID(), this.getLoginUser().getUserName(), this.getObj().getHandleResult());
 		this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
		return MISP_DONE_PAGE;
	}



	public ServiceOrderManageService getOrderService()
	{
		return orderService;
	}
	public void setOrderService(ServiceOrderManageService orderService)
	{
		this.orderService = orderService;
	}

	public TableDataModel<ServiceOrder> getServiceOrderTable()
	{
		return serviceOrderTable;
	}
	public void setServiceOrderTable(TableDataModel<ServiceOrder> serviceOrderTable)
	{
		this.serviceOrderTable = serviceOrderTable;
	}
	public OrderFilterModel getFilter()
	{
		return filter;
	}
	public void setFilter(OrderFilterModel filter)
	{
		this.filter = filter;
	}
	/**
	 * @return the order
	 */
	public ServiceOrder getOrder()
	{
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(ServiceOrder order)
	{
		this.order = order;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<ServiceOrder> getService()
	{
		// TODO Auto-generated method stub
		return this.orderService;
	}

}
