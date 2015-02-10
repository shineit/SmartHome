package cn.fuego.smart.home.domain;

import java.util.Date;

import cn.fuego.common.domain.PersistenceObject;
import cn.fuego.smart.home.constant.AlarmClearEnum;


/**
 * 
* @ClassName: SensorAlarm 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:01:17 
*
 */
public class Alarm implements PersistenceObject
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PRI_KEY = "id";
	
	private int id;  			//告警ID，自增长
	
	private long concentratorID;
	private int objType;      //0 集中器,1家庭终端，2消防终端 AlarmObjTypeEnmu
	private long objID;        //对象ID
	
	private int alarmType;		//告警事件类型 AlarmTypeEnum
	private Float dataValue = (float)0;	//告警值,模拟量类型 才有
	private Date alarmTime;	//告警产生的时间
	private String clearUser;  //清除人 手动清除需要填写
	private Integer clearStatus = AlarmClearEnum.NONE_CLEAR.getIntValue();   //0未清除 1 手动清除 2自动清除 AlarmClearEnum
	private Date clearTime;	//告警清除的时间
	private String statusColor;//状态颜色，对应清除状态

	private int objID1=0;       //备选id1,homesensor-channelID;loopID; //回路号
	private int objID2=0;       //备选id2,  codeID;//编号

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



	public Float getDataValue()
	{
		return dataValue;
	}



	public void setDataValue(Float dataValue)
	{
		this.dataValue = dataValue;
	}



	public Date getAlarmTime()
	{
		return alarmTime;
	}



	public void setAlarmTime(Date alarmTime)
	{
		this.alarmTime = alarmTime;
	}



	public String getClearUser()
	{
		return clearUser;
	}



	public void setClearUser(String clearUser)
	{
		this.clearUser = clearUser;
	}



	public Integer getClearStatus()
	{
		return clearStatus;
	}



	public void setClearStatus(Integer clearStatus)
	{
		this.clearStatus = clearStatus;
	}



	public Date getClearTime()
	{
		return clearTime;
	}



	public void setClearTime(Date clearTime)
	{
		this.clearTime = clearTime;
	}



	public int getObjID1()
	{
		return objID1;
	}



	public void setObjID1(int objID1)
	{
		this.objID1 = objID1;
	}



	public int getObjID2()
	{
		return objID2;
	}



	public void setObjID2(int objID2)
	{
		this.objID2 = objID2;
	}



	public void setStatusColor(String statusColor)
	{
		this.statusColor = statusColor;
	}



	public String getStatusColor()
	{
	  
	    if(AlarmClearEnum.NONE_CLEAR.getIntValue()==this.getClearStatus())
	    {
	    	statusColor= "red";
	    }
	    else
	    {
	    	statusColor= "green";

	    }
		return statusColor;
	}

	 
	
}
