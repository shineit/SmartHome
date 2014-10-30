package cn.fuego.smart.home.webservice.from.client.model;

import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.from.client.model.base.UserMarkJson;


/**
 * 
* @ClassName: GetUserMarkListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:14 
*
 */
public class GetUserMarkListRsp extends BaseJsonRsp
{
	private List<UserMarkJson> markList;

	public List<UserMarkJson> getMarkList()
	{
		return markList;
	}

	public void setMarkList(List<UserMarkJson> markList)
	{
		this.markList = markList;
	}

	@Override
	public String toString()
	{
		return "GetUserMarkListRsp [markList=" + markList + "]";
	}
	
	

}
