package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;


/**
 * 
* @ClassName: Sensor 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:01:13 
*
 */
public class Sensor implements PersistenceObject
{
	private int concentratorID;  //集中器ID
	private int sensorID;         //终端ID，在集中中，每个终端设备，每个通道有一个唯一ID
	private int devID;           //终端设备的出场ID，具有唯一性
	private int channelID;       //终端设备的通道ID
	private int sensorKind;      //0 模拟类，1告警类，2控制类型，
	private int sensorType;      //传感器类型
	private String sensorTypeName;  //传感器类型名称
	private int status;          //告警使能状态，0-禁止，1-使能，
	private int alarmStatus;     //告警状态
	private double warnValue;    //预警值
	private double errorValue;   //火警值
	private int groupID;         //区域ID（用于标识联动控制器）
	private int ctrGroupID;      //联动控制器ID
	private String description;  //描述
	private String mark;         //自定义标签
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
	public int getDevID()
	{
		return devID;
	}
	public void setDevID(int devID)
	{
		this.devID = devID;
	}
	public int getChannelID()
	{
		return channelID;
	}
	public void setChannelID(int channelID)
	{
		this.channelID = channelID;
	}
	public int getSensorKind()
	{
		return sensorKind;
	}
	public void setSensorKind(int sensorKind)
	{
		this.sensorKind = sensorKind;
	}
	public int getSensorType()
	{
		return sensorType;
	}
	public void setSensorType(int sensorType)
	{
		this.sensorType = sensorType;
	}

	public String getSensorTypeName()
	{
		return sensorTypeName;
	}
	public void setSensorTypeName(String sensorTypeName)
	{
		this.sensorTypeName = sensorTypeName;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public int getAlarmStatus()
	{
		return alarmStatus;
	}
	public void setAlarmStatus(int alarmStatus)
	{
		this.alarmStatus = alarmStatus;
	}
	public double getWarnValue()
	{
		return warnValue;
	}
	public void setWarnValue(double warnValue)
	{
		this.warnValue = warnValue;
	}
	public double getErrorValue()
	{
		return errorValue;
	}
	public void setErrorValue(double errorValue)
	{
		this.errorValue = errorValue;
	}
	public int getGroupID()
	{
		return groupID;
	}
	public void setGroupID(int groupID)
	{
		this.groupID = groupID;
	}
	public int getCtrGroupID()
	{
		return ctrGroupID;
	}
	public void setCtrGroupID(int ctrGroupID)
	{
		this.ctrGroupID = ctrGroupID;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}
	
}
