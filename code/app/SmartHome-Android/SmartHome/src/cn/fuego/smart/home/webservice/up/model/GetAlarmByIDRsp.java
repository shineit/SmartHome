package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;

public class GetAlarmByIDRsp extends BaseJsonRsp
{
	private AlarmJson alarm;

	public AlarmJson getAlarm()
	{
		return alarm;
	}

	public void setAlarm(AlarmJson alarm)
	{
		this.alarm = alarm;
	}

	@Override
	public String toString()
	{
		return "GetAlarmByIDRsp [alarm=" + alarm + ", result=" + result + "]";
	}

 
	
}
