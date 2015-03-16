package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.up.model.base.ConcentratorJson;

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
	private ConcentratorJson concentrator;

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
		return "SetConcentratorReq [concentrator=" + concentrator + ", token="
				+ token + "]";
	}


}
