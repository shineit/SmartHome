package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseRspJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeAlarmJson;

public class GetAlarmByIDRsp extends MispBaseRspJson
{
	
	private HomeAlarmJson homeAlarm;
	private FireAlarmJson fireAlarm;



	public HomeAlarmJson getHomeAlarm()
	{
		return homeAlarm;
	}

	public void setHomeAlarm(HomeAlarmJson homeAlarm)
	{
		this.homeAlarm = homeAlarm;
	}

	public FireAlarmJson getFireAlarm()
	{
		return fireAlarm;
	}

	public void setFireAlarm(FireAlarmJson fireAlarm)
	{
		this.fireAlarm = fireAlarm;
	}

	@Override
	public String toString()
	{
		return "GetAlarmByIDRsp [homeAlarm=" + homeAlarm + ", fireAlarm="
				+ fireAlarm + ", errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + ", obj=" + obj + "]";
	}

	 

	
}
