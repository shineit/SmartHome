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
import cn.fuego.common.util.format.DateUtil;

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
	private int role;
	private Date regDate=DateUtil.getCurrentDate();
	private int status; //0-已创建，1-已申请，2-已注册，3-已注销
	
	private String org_id;
	
	
	public static String getUserIDAttr()
	{
		return "userID";
	}
	
	public static String getUserNameAttr()
	{
		return "userName";
	}
 
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

	public Date getRegDate()
	{
		return regDate;
	}
	public void setRegDate(Date regDate)
	{
		this.regDate = regDate;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getOrg_id()
	{
		return org_id;
	}

	public void setOrg_id(String org_id)
	{
		this.org_id = org_id;
	}
	

}
