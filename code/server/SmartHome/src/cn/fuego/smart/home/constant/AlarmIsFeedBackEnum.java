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
 * @ClassName: AlarmIsFeedBackEunm 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午6:01:22 
 *  
 */
public enum AlarmIsFeedBackEnum
{
	NORMAL(0,"正常告警"),
	FEEDBACK(1,"反馈告警");
 
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private AlarmIsFeedBackEnum(int intValue, String strValue)
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
	public static AlarmIsFeedBackEnum getEnumByInt(int intValue)
	{
		for (AlarmIsFeedBackEnum c : AlarmIsFeedBackEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
}
