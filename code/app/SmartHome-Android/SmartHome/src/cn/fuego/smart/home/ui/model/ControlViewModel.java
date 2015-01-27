/**   
* @Title: ControlViewModel.java 
* @Package cn.fuego.smart.home.ui.model 
* @Description: TODO
* @author Aether
* @date 2014-12-10 下午11:08:36 
* @version V1.0   
*/ 
package cn.fuego.smart.home.ui.model;

/** 
 * @ClassName: ControlViewModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-10 下午11:08:36 
 *  
 */
public class ControlViewModel
{
	private String id="id";              //唯一ID，终端设备的出场ID，具有唯一性
	private String concentratorID="concentratorID";  //集中器ID
	private String sensorTypeName="sensorTypeName";  //传感器类型名称
	private String sensorID="sensorID";         //终端ID，在集中中，每个终端设备，每个通道有一个唯一ID
	private String channelID="channelID";         //通道ID
	private String descriptions="descriptions";  //描述
	private String mark="mark";         //自定义标签
	private String groupID="groupID"; //区域ID，用于标识联动控制器
	public String getId()
	{
		return id;
	}
	public String getConcentratorID()
	{
		return concentratorID;
	}
	public String getSensorTypeName()
	{
		return sensorTypeName;
	}
	public String getDescriptions()
	{
		return descriptions;
	}
	public String getMark()
	{
		return mark;
	}
	public String getGroupID()
	{
		return groupID;
	}
	public String getSensorID()
	{
		return sensorID;
	}
	public String getChannelID()
	{
		return channelID;
	}
	
	
}
