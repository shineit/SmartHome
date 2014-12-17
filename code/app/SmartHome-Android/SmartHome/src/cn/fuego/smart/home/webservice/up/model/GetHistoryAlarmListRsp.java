package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeAlarmJson;


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
	private List<HomeAlarmJson> alarmList = new ArrayList<HomeAlarmJson>();

	public List<HomeAlarmJson> getAlarmList()
	{
		return alarmList;
	}

	public void setAlarmList(List<HomeAlarmJson> alarmList)
	{
		this.alarmList = alarmList;
	}

	@Override
	public String toString()
	{
		return "GetHistoryAlarmListRsp [alarmList=" + alarmList + "]";
	}

	
 
	

}
