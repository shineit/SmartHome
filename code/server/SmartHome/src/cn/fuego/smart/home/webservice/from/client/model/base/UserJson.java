/**   
* @Title: UserJson.java 
* @Package cn.fuego.smart.home.webservice.from.client.model.base 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-1 下午12:13:54 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.model.base;

import java.util.List;

import cn.fuego.misp.domain.SystemUser;

 /** 
 * @ClassName: UserJson 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-1 下午12:13:54 
 *  
 */
public class UserJson
{
	private int userID;
	private String userName;
	private int role;
	private List<AttributeJson> listAttr;
	
	public void loadWithUser(SystemUser user)
	{
		this.userID = user.getUserID();
		this.role = user.getRole();
		this.userName = user.getUserName();
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
	public int getRole()
	{
		return role;
	}
	public void setRole(int role)
	{
		this.role = role;
	}
	public List<AttributeJson> getListAttr()
	{
		return listAttr;
	}
	public void setListAttr(List<AttributeJson> listAttr)
	{
		this.listAttr = listAttr;
	}
	
	
	
}
