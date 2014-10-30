package cn.fuego.smart.home.domain;

import java.util.Date;

import cn.fuego.common.domain.PersistenceObject;


/**
 * 
* @ClassName: SensorAlarm 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:01:17 
*
 */
public class SensorAlarm implements PersistenceObject
{
	private int id;  			//告警ID，自增长
	private int concentratorID;	//集中器ID
	private int sensorID;			//终端ID，在集中中，每个终端设备，每个通道有一个唯一ID
	private int channelID; 			//终端设备的通道ID
	private int alarmStatus;		//告警状态
	private float alarmValue;		//告警值
	private Date alarmTime;			//告警产生的时间

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(int concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public int getSensorID()
	{
		return sensorID;
	}
	public void setSensorID(int sensorID)
	{
		this.sensorID = sensorID;
	}
	public int getChannelID()
	{
		return channelID;
	}
	public void setChannelID(int channelID)
	{
		this.channelID = channelID;
	}
	public int getAlarmStatus()
	{
		return alarmStatus;
	}
	public void setAlarmStatus(int alarmStatus)
	{
		this.alarmStatus = alarmStatus;
	}
	public float getAlarmValue()
	{
		return alarmValue;
	}
	public void setAlarmValue(float alarmValue)
	{
		this.alarmValue = alarmValue;
	}
	public Date getAlarmTime()
	{
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime)
	{
		this.alarmTime = alarmTime;
	}
	
}
