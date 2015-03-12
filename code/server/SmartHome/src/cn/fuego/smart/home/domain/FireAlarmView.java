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
public class FireAlarmView implements PersistenceObject
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
	
    //通过objID关联FireSensor表	
	private int machineID; //机号
	private int loopID;    //回路号
	private int codeID;    //编号
	private String locationDesp;  //位置描述
	private float locationX;  //X 偏移，相对当前图片尺寸宽度
	private float locationY;  //Y 偏移，相当当前图片尺寸高度
	
	private Integer sensorType;      //传感器类型
	private String sensorTypeName;  //传感器类型名称
	
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
	public int getMachineID()
	{
		return machineID;
	}
	public void setMachineID(int machineID)
	{
		this.machineID = machineID;
	}
	public int getLoopID()
	{
		return loopID;
	}
	public void setLoopID(int loopID)
	{
		this.loopID = loopID;
	}
	public int getCodeID()
	{
		return codeID;
	}
	public void setCodeID(int codeID)
	{
		this.codeID = codeID;
	}

	public String getLocationDesp()
	{
		return locationDesp;
	}
	public void setLocationDesp(String locationDesp)
	{
		this.locationDesp = locationDesp;
	}
	public float getLocationX()
	{
		return locationX;
	}
	public void setLocationX(float locationX)
	{
		this.locationX = locationX;
	}
	public float getLocationY()
	{
		return locationY;
	}
	public void setLocationY(float locationY)
	{
		this.locationY = locationY;
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
