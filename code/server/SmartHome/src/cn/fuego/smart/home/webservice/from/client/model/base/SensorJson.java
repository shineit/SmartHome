package cn.fuego.smart.home.webservice.from.client.model.base;


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
	private int sesorID;
	private int devID;
	private int channelID;
	private int sensorKind;
	private int sensorType;
	private int sensorTypeName;
	private int status;
	private int alarmStaus;
	private double warnValue;
	private double errorValue;
	private int groupID;
	private int ctrGroupID;
	private String description;
	private String mark;
	public int getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(int concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public int getSesorID()
	{
		return sesorID;
	}
	public void setSesorID(int sesorID)
	{
		this.sesorID = sesorID;
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
	public int getSensorTypeName()
	{
		return sensorTypeName;
	}
	public void setSensorTypeName(int sensorTypeName)
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
