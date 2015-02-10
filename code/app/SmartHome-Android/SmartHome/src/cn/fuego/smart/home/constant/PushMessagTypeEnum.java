/**   
* @Title: PushMessagTypeEnum.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-4 上午12:03:00 
* @version V1.0   
*/ 
package cn.fuego.smart.home.constant;

 /** 
 * @ClassName: PushMessagTypeEnum 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-4 上午12:03:00 
 *  
 */
public enum PushMessagTypeEnum
{
	ALRAM_MSG(0,"告警通知"),
	NEWS_MSG(1,"公告通知"),
	FATAL_ALARM(2,"严重警告");
 
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private PushMessagTypeEnum(int intValue, String strValue)
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
	public static PushMessagTypeEnum getEnumByInt(int intValue)
	{
		for (PushMessagTypeEnum c : PushMessagTypeEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
}
