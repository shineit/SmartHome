package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.ClientVersionJson;

public class GetClientVersionRsp extends MispBaseRspJson
{
	private ClientVersionJson obj;

	public ClientVersionJson getObj()
	{
		return obj;
	}

	public void setObj(ClientVersionJson obj)
	{
		this.obj = obj;
	}
	

}
