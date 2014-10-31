package cn.fuego.smart.home.webservice.from.client.model.base;

import cn.fuego.smart.home.domain.Sensor;


/**
 * 
* @ClassName: SensorJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:07 
*
 */
public class SensorJson
{
	private int concentratorID;
	private int sensorID;
	private int devID;
	private int channelID;
	private int sensorKind;
	private int sensorType;
	private String sensorTypeName;
	private int status;
	private int alarmStaus;
	private double warnValue;
	private double errorValue;
	private int groupID;
	private String ctrGroupID;
	private String description;
	private String mark;
	
	public void loadWithSensor(Sensor sensor)
	{
		this.concentratorID = sensor.getConcentratorID();
		this.sensorID = sensor.getSensorID();
		this.devID = sensor.getDevID();
		this.channelID = sensor.getChannelID();
		this.sensorKind = sensor.getSensorKind();
		this.sensorType = sensor.getSensorType();
		this.sensorTypeName = sensor.getSensorTypeName();
		this.status = sensor.getStatus();
		this.alarmStaus = sensor.getAlarmStatus();
		this.warnValue = sensor.getWarnValue();
		this.errorValue = sensor.getErrorValue();
		this.groupID = sensor.getGroupID();
		this.ctrGroupID = sensor.getCtrGroupID();
		this.description = sensor.getDescription();
		this.mark = sensor.getMark();
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
	public int getAlarmStaus()
	{
		return alarmStaus;
	}
	public void setAlarmStaus(int alarmStaus)
	{
		this.alarmStaus = alarmStaus;
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
 
	public String getCtrGroupID()
	{
		return ctrGroupID;
	}
	public void setCtrGroupID(String ctrGroupID)
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
	@Override
	public String toString()
	{
		return "SensorJson [concentratorID=" + concentratorID + ", sensorID="
				+ sensorID + ", devID=" + devID + ", channelID=" + channelID
				+ ", sensorKind=" + sensorKind + ", sensorType=" + sensorType
				+ ", sensorTypeName=" + sensorTypeName + ", status=" + status
				+ ", alarmStaus=" + alarmStaus + ", warnValue=" + warnValue
				+ ", errorValue=" + errorValue + ", groupID=" + groupID
				+ ", ctrGroupID=" + ctrGroupID + ", description=" + description
				+ ", mark=" + mark + "]";
	}


}
