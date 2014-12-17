package cn.fuego.smart.home.webservice.up.model.base;




/** 
* @ClassName: HomeAlarmJson 
* @Description:对应HomeAlarmView
* @author Aether
* @date 2014-12-15 下午6:26:29 
*  
*/ 
public class HomeAlarmJson
{
	private int id;  			//告警ID，自增长
	private int concentratorID;
	private int objType;      //0 集中器,1家庭终端，2消防终端 AlarmObjTypeEnmu
	private int objID;        //对应snesorID
	private int alarmType;		//告警事件类型 AlarmTypeEnum
	private long alarmTime;	//告警产生的时间
	private Integer clearStatus;   //0未清除 1 手动清除 2自动清除 AlarmClearEnum
	private float dataValue;	//告警值,模拟量类型 才有

	private String clearUser;  //清除人 手动清除需要填写
	private long clearTime;	//告警清除的时间
	//通过concenratorID关联concentrator表
	private String concentDesp;		//集中器描述
    //通过objID关联HomeSensor表	
	private Integer sensorType;      //传感器类型
	private String sensorTypeName;  //传感器类型名称
	private String sensorDesp;  //传感器描述
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
	public int getObjType()
	{
		return objType;
	}
	public void setObjType(int objType)
	{
		this.objType = objType;
	}
	public int getObjID()
	{
		return objID;
	}
	public void setObjID(int objID)
	{
		this.objID = objID;
	}
	public int getAlarmType()
	{
		return alarmType;
	}
	public void setAlarmType(int alarmType)
	{
		this.alarmType = alarmType;
	}

	public long getAlarmTime()
	{
		return alarmTime;
	}
	public void setAlarmTime(long alarmTime)
	{
		this.alarmTime = alarmTime;
	}
	public Integer getClearStatus()
	{
		return clearStatus;
	}
	public void setClearStatus(Integer clearStatus)
	{
		this.clearStatus = clearStatus;
	}
	public String getConcentDesp()
	{
		return concentDesp;
	}
	public void setConcentDesp(String concentDesp)
	{
		this.concentDesp = concentDesp;
	}
	public Integer getSensorType()
	{
		return sensorType;
	}
	public void setSensorType(Integer sensorType)
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
	public String getSensorDesp()
	{
		return sensorDesp;
	}
	public void setSensorDesp(String sensorDesp)
	{
		this.sensorDesp = sensorDesp;
	}
	public float getDataValue()
	{
		return dataValue;
	}
	public void setDataValue(float dataValue)
	{
		this.dataValue = dataValue;
	}
	public String getClearUser()
	{
		return clearUser;
	}
	public void setClearUser(String clearUser)
	{
		this.clearUser = clearUser;
	}
	public long getClearTime()
	{
		return clearTime;
	}
	public void setClearTime(long clearTime)
	{
		this.clearTime = clearTime;
	}
	@Override
	public String toString()
	{
		return "HomeAlarmJson [id=" + id + ", concentratorID=" + concentratorID
				+ ", objType=" + objType + ", objID=" + objID + ", alarmType="
				+ alarmType + ", alarmTime=" + alarmTime + ", clearStatus="
				+ clearStatus + ", dataValue=" + dataValue + ", clearUser="
				+ clearUser + ", clearTime=" + clearTime + ", concentDesp="
				+ concentDesp + ", sensorType=" + sensorType
				+ ", sensorTypeName=" + sensorTypeName + ", sensorDesp="
				+ sensorDesp + "]";
	}

	
 
}
