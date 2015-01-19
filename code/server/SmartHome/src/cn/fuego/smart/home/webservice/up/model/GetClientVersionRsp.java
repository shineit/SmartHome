package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.ClientVersionJson;

public class GetClientVersionRsp extends BaseJsonRsp
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
