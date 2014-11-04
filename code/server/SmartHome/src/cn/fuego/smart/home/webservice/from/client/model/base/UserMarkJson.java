package cn.fuego.smart.home.webservice.from.client.model.base;

import cn.fuego.smart.home.domain.UserMark;


/**
 * 
* @ClassName: UserMarkJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:20 
*
 */
public class UserMarkJson
{
    private int userID;
    private String mark;
    
    public void load(UserMark userMark)
    {
    	this.userID = userMark.getUserID();
    	this.mark = userMark.getMark();
    }
    
    public UserMark getUserMark()
    {
    	UserMark userMark = new UserMark();
    	userMark.setUserID(userID);
    	userMark.setMark(mark);
    	return userMark;
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
	@Override
	public String toString()
	{
		return "UserMarkJson [userID=" + userID + ", mark=" + mark + "]";
	}
    

}
