/**   
* @Title: UserCompanyModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2015-3-24 下午3:33:30 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

/** 
 * @ClassName: UserCompanyModel 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-24 下午3:33:30 
 *  
 */
public class UserCompanyModel
{

	private String userID;
	private String companyID;
	private String userName;
	public String getUserID()
	{
		return userID;
	}
	public void setUserID(String userID)
	{
		this.userID = userID;
	}
	public String getCompanyID()
	{
		return companyID;
	}
	public void setCompanyID(String companyID)
	{
		this.companyID = companyID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
}
