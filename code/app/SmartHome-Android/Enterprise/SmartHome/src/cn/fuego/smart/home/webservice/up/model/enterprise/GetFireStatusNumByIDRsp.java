package cn.fuego.smart.home.webservice.up.model.enterprise;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.BageNumJson;

public class GetFireStatusNumByIDRsp extends MispBaseRspJson
{

	List<BageNumJson> numList = new ArrayList<BageNumJson>();
	

	public List<BageNumJson> getNumList()
	{
		return numList;
	}


	public void setNumList(List<BageNumJson> numList)
	{
		this.numList = numList;
	}


	@Override
	public String toString()
	{
		return "GetFireStatusNumByIDRsp [numList=" + numList + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg + ", obj=" + obj + "]";
	}


	
	
}
