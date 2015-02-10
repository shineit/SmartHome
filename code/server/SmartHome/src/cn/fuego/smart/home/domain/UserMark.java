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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int markID;//自增长
	private int userID;
	private String mark;
	
	public static String getUserIDAttr()
	{
		return "userID";
	}
	
	public static String getMarkAttr()
	{
		return "mark";
	}
	
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}

	public int getMarkID()
	{
		return markID;
	}

	public void setMarkID(int markID)
	{
		this.markID = markID;
	}

	@Override
	public String toString()
	{
		return "UserMark [markID=" + markID + ", userID=" + userID + ", mark="
				+ mark + "]";
	}
    
		

}
