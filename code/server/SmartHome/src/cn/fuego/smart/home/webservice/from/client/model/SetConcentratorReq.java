package cn.fuego.smart.home.webservice.from.client.model;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.ConcentratorJson;

/**
 * 
* @ClassName: SetConcentratorReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:25 
*
 */
public class SetConcentratorReq extends BaseJsonReq
{
	private String command;
	private ConcentratorJson concentrator;
	public String getCommand()
	{
		return command;
	}
	public void setCommand(String command)
	{
		this.command = command;
	}
	public ConcentratorJson getConcentrator()
	{
		return concentrator;
	}
	public void setConcentrator(ConcentratorJson concentrator)
	{
		this.concentrator = concentrator;
	}
	@Override
	public String toString()
	{
		return "SetConcentratorReq [command=" + command + ", concentrator="
				+ concentrator + "]";
	}




}
