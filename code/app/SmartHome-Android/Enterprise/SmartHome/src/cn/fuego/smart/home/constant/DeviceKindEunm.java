package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: SensorKindEunm 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:47 
*
 */
public enum DeviceKindEunm
{
	
	DISCRETE_SENSOR(0,"告警类"),
	CONTIUOUS_SENSOR(1,"模拟类"),
	CTRL_SENSOR(2,"控制类"),
	HOME_CONCENTRATOR(3,"家庭集中器"),
	FIRE_CONCENTRATOR(4,"企业集中器");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private DeviceKindEunm(int intValue, String strValue)
	{
		this.intValue = intValue;
		this.strValue = strValue;
	}
	public int getIntValue()
	{
		return intValue;
	}
	public String getStrValue()
	{
		return strValue;
	}
	public static DeviceKindEunm getEnumByInt(int intValue)
	{
		for (DeviceKindEunm c : DeviceKindEunm.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
	public static DeviceKindEunm getEnumByStr(String strValue)
	{
		for (DeviceKindEunm c : DeviceKindEunm.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	} 
	
}
