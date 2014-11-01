package cn.fuego.smart.home.domain;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.domain.PersistenceObject;


/**
 * 
* @ClassName: Sensor 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:01:13 
*
 */
public class HomeSensor implements PersistenceObject
{
	private int id;              //唯一ID 终端设备的出场ID，具有唯一性
	private int concentratorID;  //集中器ID
	private int sensorID;         //终端ID，在集中中，每个终端设备，每个通道有一个唯一ID
 	private int channelID;       //终端设备的通道ID
	private int sensorKind;      //传感器种类 0 告警类，1模拟类，2控制类型， SensorKindEunm
	private int sensorType;      //传感器类型
	private String sensorTypeName;  //传感器类型名称
	private int status;          //告警使能状态，0-禁止，1-使能，SensorStatusEnum
	private float warnValue;    //预警值
	private float errorValue;   //火警值
	private int groupID;         //区域ID（用于标识联动控制器）
	private String ctrGroupID;      //联动控制器ID ;隔开
	private String description;  //描述
	private String mark;         //自定义标签
	
	
	
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
	
	public List<Integer> getCtrGroupIDList()
	{
		List<Integer> groupIDList = new ArrayList<Integer>();
		String[] strAry = ctrGroupID.split(";");
		for(int i=0;i<strAry.length;i++)
		{
			groupIDList.add(Integer.valueOf(strAry[i]));
		}
		
		return groupIDList;
		
	}
	public void  setCtrGroupIDWithIDList(List<Integer> idList)
	{
  		String str = "";
		for(int i=0;i<idList.size();i++)
		{
			str += idList.get(i) + ";";
		}
		this.ctrGroupID = str;
		
 		
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
 
	public float getWarnValue()
	{
		return warnValue;
	}
	public void setWarnValue(float warnValue)
	{
		this.warnValue = warnValue;
	}
	public float getErrorValue()
	{
		return errorValue;
	}
	public void setErrorValue(float errorValue)
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
	
}
