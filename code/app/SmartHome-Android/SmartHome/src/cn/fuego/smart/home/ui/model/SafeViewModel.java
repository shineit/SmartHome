/**   
* @Title: SafeViewModel.java 
* @Package cn.fuego.smart.home.ui.model 
* @Description: TODO
* @author Aether
* @date 2014-12-17 下午6:31:50 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.model;

/** 
 * @ClassName: SafeViewModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-17 下午6:31:50 
 *  
 */
public class SafeViewModel
{
	private String id="id";              //唯一ID，终端设备的出场ID，具有唯一性
	private String concentratorID="concentratorID";  //集中器ID
	private String sensorID="sensorID";         //终端ID，在集中中，每个终端设备，每个通道有一个唯一ID
	private String channelID="channelID";  //通道ID
	private String sensorTypeName="sensorTypeName";  //传感器类型名称
	private String warnValue="warnValue";    //预警值
	private String errorValue="errorValue";   //火警值
	private String descriptions="descriptions";  //描述
	private String mark="mark";         //自定义标签
	private String ctrGroupID="ctrGroupID"; //控制器ID

	private String ctrSensorID="ctrSensorID";
	private String ctrChannelID ="ctrChannelID";
	public String getId()
	{
		return id;
	}
	public String getConcentratorID()
	{
		return concentratorID;
	}
	public String getSensorID()
	{
		return sensorID;
	}
	public String getSensorTypeName()
	{
		return sensorTypeName;
	}
	public String getWarnValue()
	{
		return warnValue;
	}
	public String getErrorValue()
	{
		return errorValue;
	}
	public String getDescriptions()
	{
		return descriptions;
	}
	public String getMark()
	{
		return mark;
	}
	public String getCtrGroupID()
	{
		return ctrGroupID;
	}
	public String getCtrSensorID()
	{
		return ctrSensorID;
	}
	public String getCtrChannelID()
	{
		return ctrChannelID;
	}
	public String getChannelID()
	{
		return channelID;
	}

	
}
