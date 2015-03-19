package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;

public class GetFireAlarmByIDRsp extends MispBaseRspJson
{
	private List<FireAlarmJson> fireAlarmList= new ArrayList<FireAlarmJson>();

	public List<FireAlarmJson> getFireAlarmList()
	{
		return fireAlarmList;
	}

	public void setFireAlarmList(List<FireAlarmJson> fireAlarmList)
	{
		this.fireAlarmList = fireAlarmList;
	}

	@Override
	public String toString()
	{
		return "GetFireAlarmByIDRsp [fireAlarmList=" + fireAlarmList
				+ ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", obj=" + obj + "]";
	}

 
	

	
}
