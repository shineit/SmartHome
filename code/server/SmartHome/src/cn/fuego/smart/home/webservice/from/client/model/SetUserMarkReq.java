package cn.fuego.smart.home.webservice.from.client.model;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.UserMarkJson;


/**
 * 
* @ClassName: SetUserMarkReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:03 
*
 */
public class SetUserMarkReq extends BaseJsonReq
{
	private String command;
	private UserMarkJson userMark;
	public String getCommand()
	{
		return command;
	}
	public void setCommand(String command)
	{
		this.command = command;
	}
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
		return "SetUserMarkReq [command=" + command + ", userMark=" + userMark
				+ "]";
	}
	
	
}
