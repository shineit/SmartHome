package cn.fuego.smart.home.web.model;

import cn.fuego.smart.home.constant.ServiceOrderStatusEnum;
import cn.fuego.smart.home.constant.ServiceOrderTypeEnum;

/** 
* @ClassName: OrderFilterModel 
* @Description: TODO
* @author Aether
* @date 2014-11-1 下午4:20:38 
*  
*/ 
public class OrderFilterModel
{
    private String orderID;
    private String orderName;
    private String orderStatus;
    private String startDate;
    private String endDate;
    private ServiceOrderStatusEnum[] statusList =ServiceOrderStatusEnum.values();
    private ServiceOrderTypeEnum[] typeList = ServiceOrderTypeEnum.values();
	public String getOrderID()
	{
		return orderID;
	}
	public void setOrderID(String orderID)
	{
		this.orderID = orderID;
	}
	public String getOrderName()
	{
		return orderName;
	}
	public void setOrderName(String orderName)
	{
		this.orderName = orderName;
	}
	public String getOrderStatus()
	{
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus)
	{
		this.orderStatus = orderStatus;
	}
	public String getStartDate()
	{
		return startDate;
	}
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	public String getEndDate()
	{
		return endDate;
	}
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	/**
	 * @return the statusList
	 */
	public ServiceOrderStatusEnum[] getStatusList()
	{
		return statusList;
	}
	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(ServiceOrderStatusEnum[] statusList)
	{
		this.statusList = statusList;
	}
	/**
	 * @return the typeList
	 */
	public ServiceOrderTypeEnum[] getTypeList()
	{
		return typeList;
	}
	/**
	 * @param typeList the typeList to set
	 */
	public void setTypeList(ServiceOrderTypeEnum[] typeList)
	{
		this.typeList = typeList;
	}
	
	
}
