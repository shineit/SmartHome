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
* @ClassName: AlarmIsPushEnum 
* @Description: TODO
* @author Aether
* @date 2015-3-12 上午10:50:41 
*  
*/ 
public enum AlarmPushTypeEnum
{
	NO_PUSH(0,"不推送"),
	PUSH(1,"推送");
 
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private AlarmPushTypeEnum(int intValue, String strValue)
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
	public static AlarmPushTypeEnum getEnumByInt(int intValue)
	{
		for (AlarmPushTypeEnum c : AlarmPushTypeEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
}
