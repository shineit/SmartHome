package cn.fuego.smart.home.webservice.from.client.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonRsp;


/**
 * 
* @ClassName: GetHistoryAlarmListRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:38 
*
 */
public class GetHistoryAlarmListRsp extends BaseJsonRsp
{
	private List<AlarmJson> alarmList = new ArrayList<AlarmJson>();

	public List<AlarmJson> getAlarmList()
	{
		return alarmList;
	}

	public void setAlarmList(List<AlarmJson> alarmList)
	{
		this.alarmList = alarmList;
	}

	@Override
	public String toString()
	{
		return "GetHistoryAlarmListRsp [alarmList=" + alarmList + "]";
	}

	
 
	

}
