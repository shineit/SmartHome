package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;

/** 
* @ClassName: CustomerJson 
* @Description: TODO
* @author Aether
* @date 2015-1-12 下午11:06:43 
*  
*/ 
public class CustomerJson implements Serializable
{
	private int userID; 			//用户id
	private String customerName;	//用户姓名
	private String phone;			//用户手机号码
	private String addr;			//用户住址
	private String email;			//用户邮箱
	private int status;
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getCustomerName()
	{
		return customerName;
	}
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}	
    

 
}
