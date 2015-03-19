package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;


/**
 * 
* @ClassName: SetUserMarkReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:03 
*
 */
public class SetUserMarkReq extends MispBaseReqJson
{
 
	private UserMarkJson userMark;
 
	public UserMarkJson getUserMark()
	{
		return userMark;
	}
	public void setUserMark(UserMarkJson userMark)
	{
		this.userMark = userMark;
	}
	@Override
	public String toString()
	{
		return "SetUserMarkReq [userMark=" + userMark + "]";
	}
 
	
	
}
