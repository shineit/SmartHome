/**   
* @Title: UserModel.java 
* @Package cn.fuego.remote.medical.expert.web.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-23 下午06:28:16 
* @version V1.0   
*/ 
package cn.fuego.misp.web.model.user;

import java.util.Date;

/** 
 * @ClassName: UserModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-23 下午06:28:16 
 *  
 */

public class UserModel
{
	private int userID;
	private String userName;
	private String password;
	private int accountType;
	private Date regDate;
	private String org_id;
	private String validateCode;
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

	public Date getRegDate()
	{
		return regDate;
	}
	public void setRegDate(Date regDate)
	{
		this.regDate = regDate;
	}
	public int getAccountType()
	{
		return accountType;
	}
	public void setAccountType(int accountType)
	{
		this.accountType = accountType;
	}
	public String getValidateCode()
	{
		return validateCode;
	}
	public void setValidateCode(String validateCode)
	{
		this.validateCode = validateCode;
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
