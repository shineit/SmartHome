package cn.fuego.smart.home.webservice.from.client.model.base;

/**
 * 
* @ClassName: AlarmJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:32 
*
 */
public class AlarmJson
{
	private String id;
	private String concentratorID;
	private int sensorID;
	private int alarmStatus;
	private float alarmValue;
	private long alarmTime;
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(String concentratorID)
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
	public long getAlarmTime()
	{
		return alarmTime;
	}
	public void setAlarmTime(long alarmTime)
	{
		this.alarmTime = alarmTime;
	}
	@Override
	public String toString()
	{
		return "AlarmJson [id=" + id + ", concentratorID=" + concentratorID
				+ ", sensorID=" + sensorID + ", alarmStatus=" + alarmStatus
				+ ", alarmValue=" + alarmValue + ", alarmTime=" + alarmTime
				+ "]";
	}
 
	
	
 
}
