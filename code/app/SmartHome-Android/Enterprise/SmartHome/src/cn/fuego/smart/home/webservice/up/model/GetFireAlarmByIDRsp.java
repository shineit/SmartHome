package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;

public class GetFireAlarmByIDRsp extends BaseJsonRsp
{
	
	private FireAlarmJson fireAlarm;

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
		return "GetFireAlarmByIDRsp [fireAlarm=" + fireAlarm + ", result="
				+ result + "]";
	}



	
}
