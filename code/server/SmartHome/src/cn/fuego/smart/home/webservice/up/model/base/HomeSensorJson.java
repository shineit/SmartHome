package cn.fuego.smart.home.webservice.up.model.base;



/**
 * 
* @ClassName: SensorJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:07 
*
 */
public class HomeSensorJson
{
	private int id;              //唯一ID，终端设备的出场ID，具有唯一性
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
	private String descriptions;  //描述
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

	public String getDescriptions()
	{
		return descriptions;
	}

	public void setDescriptions(String description)
	{
		this.descriptions = description;
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
		return "HomeSensorJson [id=" + id + ", concentratorID="
				+ concentratorID + ", sensorID=" + sensorID + ", channelID="
				+ channelID + ", sensorKind=" + sensorKind + ", sensorType="
				+ sensorType + ", sensorTypeName=" + sensorTypeName
				+ ", status=" + status + ", warnValue=" + warnValue
				+ ", errorValue=" + errorValue + ", groupID=" + groupID
				+ ", ctrGroupID=" + ctrGroupID + ", descriptions=" + descriptions
				+ ", mark=" + mark + "]";
	}
 
 


}
