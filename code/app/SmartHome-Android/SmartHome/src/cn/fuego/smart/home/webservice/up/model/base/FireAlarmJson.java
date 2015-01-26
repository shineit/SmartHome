package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;




/** 
* @ClassName: FireAlarmJson 
* @Description:对应FireAlarmView
* @author Aether
* @date 2014-12-15 下午6:26:29 
*  
*/ 
public class FireAlarmJson implements Serializable
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
	private long alarmTime;	//告警产生的时间
	private Integer clearStatus;   //0未清除 1 手动清除 2自动清除 AlarmClearEnum
	private float dataValue;	//告警值,模拟量类型 才有

	private String clearUser;  //清除人 手动清除需要填写
	private long clearTime;	//告警清除的时间
	//通过concenratorID关联concentrator表
	private String concentDesp;		//集中器描述
    //通过objID关联FireSensor表	
	private int machineID; //机号
	private int loopID;    //回路号
	private int codeID;    //编号
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
		return "FireAlarmJson [id=" + id + ", concentratorID=" + concentratorID
				+ ", objType=" + objType + ", objID=" + objID + ", alarmType="
				+ alarmType + ", alarmTime=" + alarmTime + ", clearStatus="
				+ clearStatus + ", dataValue=" + dataValue + ", clearUser="
				+ clearUser + ", clearTime=" + clearTime + ", concentDesp="
				+ concentDesp + ", machineID=" + machineID + ", loopID="
				+ loopID + ", codeID=" + codeID + "]";
	}

 
}
