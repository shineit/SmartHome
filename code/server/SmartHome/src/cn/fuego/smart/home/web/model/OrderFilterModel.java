package cn.fuego.smart.home.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.HandleStatusEnum;
import cn.fuego.smart.home.constant.ServiceOrderTypeEnum;
import cn.fuego.smart.home.domain.ServiceOrder;

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
    private HandleStatusEnum[] statusList =HandleStatusEnum.values();
    private ServiceOrderTypeEnum[] typeList = ServiceOrderTypeEnum.values();
    
    public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 
			if(!ValidatorUtil.isEmpty(this.getOrderID()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,ServiceOrder.getOrderIDAttr(),this.getOrderID()));
			}
			if(!ValidatorUtil.isEmpty(this.getOrderName()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"orderName",this.getOrderName()));
			}
			if(!ValidatorUtil.isEmpty(this.getOrderStatus()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"orderStatus",String.valueOf(HandleStatusEnum.getEnumByStr(this.getOrderStatus()).getIntValue())));
			}
			if(!ValidatorUtil.isEmpty(this.getStartDate()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"createTime",this.getStartDate()));
			}
			if(!ValidatorUtil.isEmpty(this.getEndDate()))
			{
				Date endDate = DateUtil.stringToDate(this.getEndDate());
				conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER,"createTime",DateUtil.DateToString(DateUtil.dayCalculate(endDate, 1))));
			}				
		 
		return conditionList;
    }
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
	public HandleStatusEnum[] getStatusList()
	{
		return statusList;
	}
	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(HandleStatusEnum[] statusList)
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
