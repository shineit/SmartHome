package cn.fuego.smart.home.webservice.up.model.base;

import java.util.Date;

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
	
	private String orderID;         //申请单号，
    private String orderName;       //申请单名称
    private int orderType;		//申请单类型(维修，安装，咨询)
    private String content;			//申请单内容
    private String creator;			//申请人
    private long createTime;		//申请时间
    private String contactName;		//联系人名称
    private String phoneNum;		//联系电话
    private String contactAddr;		//联系地址
    private int orderStatus;		//申请单状态，0-未处理，1-已处理
    private String handleResult;   //处理结果
    private String handler;			//处理人
    private long handleTime;		//处理时间
 
    
    
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
	
	public ServiceOrder getSergviceOrder()
	{
		ServiceOrder order = new ServiceOrder();
		order.setOrderID(orderID);
		order.setOrderName(orderName);
		order.setOrderType(orderType);
		order.setContent(content);
		order.setCreator(creator);
		order.setCreateTime(new Date(createTime));
		order.setContactName(contactName);
		order.setContactAddr(contactAddr);
		order.setPhoneNum(phoneNum);
		order.setOrderStatus(orderStatus);
		order.setHandler(handler);
		order.setHandleResult(handleResult);
		order.setHandleTime(new Date(handleTime));
		
		return order;
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

	/**
	 * @return the orderType
	 */
	public int getOrderType()
	{
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(int orderType)
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
