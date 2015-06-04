/**   
* @Title: AlarmIsFeedBackEunm.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:01:22 
* @version V1.0   
*/ 
package cn.fuego.smart.home.constant;


/** 
* @ClassName: AlarmKindEnum 
* @Description: TODO
* @author Aether
* @date 2015-3-12 上午10:50:31 
*  
*/ 
public enum AlarmKindEnum
{
	ALARM(2,"智慧告警"),
	STATUS(1,"设备状态");
 
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private AlarmKindEnum(int intValue, String strValue)
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
	public static AlarmKindEnum getEnumByInt(int intValue)
	{
		for (AlarmKindEnum c : AlarmKindEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
}
