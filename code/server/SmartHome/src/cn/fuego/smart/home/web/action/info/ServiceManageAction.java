/**   
* @Title: ServiceManageAction.java 
* @Package cn.fuego.smart.home.web.action.info 
* @Description: TODO
* @author Aether
* @date 2014-11-1 上午10:15:44 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.info;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.page.TableDataModel;
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
public class ServiceManageAction  extends DWZTableAction
{
	private Log log = LogFactory.getLog(ServiceManageAction.class);
	private ServiceOrderManageService orderService = ServiceContext.getInstance().getServiceOrderManageService();
	private TableDataModel<ServiceOrder> serviceOrderTable = new  TableDataModel<ServiceOrder>();
	private OrderFilterModel filter = new OrderFilterModel();
	private ServiceOrder order;
	public String execute()
	{
		serviceOrderTable.setPage(this.getPage());
		serviceOrderTable.setDataSource(orderService.getOrderDataSource(filter));
		return SUCCESS;
	}
	@Override
	public String create()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteList()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify()
	{
		orderService.handle(order.getOrderID(), this.getLoginUser().getUserName(), order.getHandleResult());
		
		return MISP_DONE_PAGE;
	}

	@Override
	public String show()
	{
		order = orderService.getOrderById(this.getSelectedID());
		return EDIT_INFO;
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

}
