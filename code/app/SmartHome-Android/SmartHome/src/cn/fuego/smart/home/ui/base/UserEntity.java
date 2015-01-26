/**   
* @Title: UserEntity.java 
* @Package cn.fuego.smart.home.ui.base 
* @Description: TODO
* @author Aether
* @date 2015-1-25 下午4:04:41 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.base;

import java.io.Serializable;

/** 
 * @ClassName: UserEntity 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-25 下午4:04:41 
 *  
 */
public class UserEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userID;	
	private String userName;        //账户名
	private String password;		//密码
	private int role;
	private String phone;			//用户手机号码
	private String addr;			//用户住址
	private String email;			//用户邮箱
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public int getRole()
	{
		return role;
	}
	public void setRole(int role)
	{
		this.role = role;
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
	@Override
	public String toString()
	{
		return "UserEntity [userID=" + userID + ", userName=" + userName
				+ ", password=" + password + ", role=" + role + ", phone="
				+ phone + ", addr=" + addr + ", email=" + email + "]";
	}

	
}
