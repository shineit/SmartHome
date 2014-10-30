package cn.fuego.smart.home.webservice.from.client.model.base;

import cn.fuego.smart.home.domain.ServiceOrder;


/**
 * 
* @ClassName: ServiceOrderJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:11 
*
 */
public class ServiceOrderJson
{
	private String orderID;
    private String orderName;
    private String orderType;
    private String content;
    private String creator;
    private long createTime;
    private String contactName;
    private String phoneNum;
    private String contactAddr;
    private int orderStatus;
    private String handler;
    private String handleResult;
    private long handleTime;
    
    
	/**
	 * @param e
	 */
	public void loadWithOrder(ServiceOrder order)
	{
		this.orderID = order.getOrderID();
		this.orderName = order.getOrderName();
		this.orderType = order.getOrderType();
		this.content = order.getContent();
		this.creator = order.getCreator();
		this.createTime = order.getCreateTime().getTime();
		this.contactName = order.getContactName();
		this.phoneNum = order.getPhoneNum();
		this.contactAddr = order.getContactAddr();
		this.orderStatus = order.getOrderStatus();
		this.handler = order.getHandler();
		this.handleResult = order.getHandleResult();
		this.handleTime = order.getHandleTime().getTime();
		
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
	public String getOrderType()
	{
		return orderType;
	}
	public void setOrderType(String orderType)
	{
		this.orderType = orderType;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getCreator()
	{
		return creator;
	}
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	public long getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(long createTime)
	{
		this.createTime = createTime;
	}
	public String getContactName()
	{
		return contactName;
	}
	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}
	public String getPhoneNum()
	{
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	public String getContactAddr()
	{
		return contactAddr;
	}
	public void setContactAddr(String contactAddr)
	{
		this.contactAddr = contactAddr;
	}
 
	public int getOrderStatus()
	{
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	public String getHandler()
	{
		return handler;
	}
	public void setHandler(String handler)
	{
		this.handler = handler;
	}
	
	public String getHandleResult()
	{
		return handleResult;
	}

	public void setHandleResult(String handleResult)
	{
		this.handleResult = handleResult;
	}

	public long getHandleTime()
	{
		return handleTime;
	}
	public void setHandleTime(long handleTime)
	{
		this.handleTime = handleTime;
	}
	@Override
	public String toString()
	{
		return "ServiceOrderJson [orderID=" + orderID + ", orderName="
				+ orderName + ", orderType=" + orderType + ", content="
				+ content + ", creator=" + creator + ", createTime="
				+ createTime + ", contactName=" + contactName + ", phoneNum="
				+ phoneNum + ", contactAddr=" + contactAddr + ", orderStatus="
				+ orderStatus + ", handler=" + handler + ", handleTime="
				+ handleTime + "]";
	}

    
 
}
