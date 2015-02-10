/**   
* @Title: BatchSetSensorReq.java 
* @Package cn.fuego.smart.home.webservice.from.client.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-4 下午9:32:13 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model;

import java.util.List;

import cn.fuego.smart.home.webservice.up.model.base.BaseJsonReq;


/** 
* @ClassName: ClearAlarmListReq 
* @Description: TODO
* @author Aether
* @date 2015-2-8 下午5:59:30 
*  
*/
public class ClearAlarmListReq extends BaseJsonReq
{
	private List<String> alarmIDList;
    private int userID;
	public List<String> getAlarmIDList()
	{
		return alarmIDList;
	}
	public void setAlarmIDList(List<String> alarmIDList)
	{
		this.alarmIDList = alarmIDList;
	}
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	@Override
	public String toString()
	{
		return "ClearAlarmListReq [alarmIDList=" + alarmIDList + ", userID="
				+ userID + ", token=" + token + "]";
	}




}
