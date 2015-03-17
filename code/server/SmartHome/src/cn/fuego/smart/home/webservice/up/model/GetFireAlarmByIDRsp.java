package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;

public class GetFireAlarmByIDRsp extends BaseJsonRsp
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
				+ ", result=" + result + "]";
	}
	

	
}
