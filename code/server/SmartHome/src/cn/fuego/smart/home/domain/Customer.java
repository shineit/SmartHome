/**   
* @Title: Customer.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-1-9 下午2:34:07 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

/** 
 * @ClassName: Customer 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-9 下午2:34:07 
 *  
 */
public class Customer
{
	private int userID; 			//用户id
	private String customerName;	//用户姓名
	private String phone;			//用户手机号码
	private String addr;			//用户住址
	private String email;			//用户邮箱
	private int status;				//用户状态，CREATED(0,"已创建"),APPLIED(1,"已申请"),REGISTERED(2,"已注册"),	CANCELED(3,"已注销"); 
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
