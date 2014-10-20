/**   
* @Title: User.java 
* @Package cn.fuego.remote.medical.expert.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 上午11:49:00 
* @version V1.0   
*/ 
package cn.fuego.misp.domain;

import java.util.Date;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: User 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 上午11:49:00 
 *  
 */

public class SystemUser implements PersistenceObject
{
	private int userID;
	private String userName;
	private String password;
	private String secondPwd;
	private int userRole;
	private Date regDate;
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
	public String getSecondPwd()
	{
		return secondPwd;
	}
	public void setSecondPwd(String secondPwd)
	{
		this.secondPwd = secondPwd;
	}
	public int getUserRole()
	{
		return userRole;
	}
	public void setUserRole(int userRole)
	{
		this.userRole = userRole;
	}
	public Date getRegDate()
	{
		return regDate;
	}
	public void setRegDate(Date regDate)
	{
		this.regDate = regDate;
	}
 
	 
	

}
