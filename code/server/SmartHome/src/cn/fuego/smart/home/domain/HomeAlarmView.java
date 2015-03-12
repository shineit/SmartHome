package cn.fuego.smart.home.domain;

import java.util.Date;

import cn.fuego.common.domain.PersistenceObject;


/** 
* @ClassName: HomeAlarmView 
* @Description: TODO
* @author Aether
* @date 2015-3-11 下午11:25:28 
*  
*/ 
public class HomeAlarmView implements PersistenceObject
{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;  			//告警ID，自增长
	private long concentratorID;
	private int objType;      //0 集中器,1家庭终端，2消防终端 AlarmObjTypeEnmu
	private long objID;        //对应snesorID
	private int alarmType;		//告警事件类型 AlarmTypeEnum
	private Date alarmTime;	//告警产生的时间
	private Integer clearStatus;   //0未清除 1 手动清除 2自动清除 AlarmClearEnum

	private Float dataValue ;	//告警值,模拟量类型 才有
	private String clearUser;  //清除人 手动清除需要填写
	private Date clearTime;	//告警清除的时间
	//通过concenratorID关联concentrator表
	private Integer status;			//集中器状态，0 离线 1在线
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


	public long getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(long concentratorID)
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

	public long getObjID()
	{
		return objID;
	}
	public void setObjID(long objID)
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
	public Date getAlarmTime()
	{
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime)
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
	public Float getDataValue()
	{
		return dataValue;
	}
	public void setDataValue(Float dataValue)
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
	public Date getClearTime()
	{
		return clearTime;
	}
	public void setClearTime(Date clearTime)
	{
		this.clearTime = clearTime;
	}
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}


	 
	
}
