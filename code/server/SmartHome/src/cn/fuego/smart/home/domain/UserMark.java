/**   
* @Title: UserMark.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午9:31:02 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

 /** 
 * @ClassName: UserMark 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午9:31:02 
 *  
 */
public class UserMark implements PersistenceObject
{
	private int userID;
	private String make;
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getMake()
	{
		return make;
	}
	public void setMake(String make)
	{
		this.make = make;
	}
	
	

}
