package cn.fuego.smart.home.constant;

/** 
* @ClassName: BageKindEnum 
* @Description: TODO
* @author Aether
* @date 2015-6-5 下午8:43:00 
*  
*/
public enum BageKindEnum
{
	ALARM(0,"智慧告警"),
	STATUS(1,"设备状态"),
	CHECK_LOG(2,"智慧管理");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private BageKindEnum(int intValue, String strValue)
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
	public static BageKindEnum getEnumByInt(int intValue)
	{
		for (BageKindEnum c : BageKindEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
	public static BageKindEnum getEnumByStr(String strValue)
	{
		for (BageKindEnum c : BageKindEnum.values())
		{
			if (strValue.equals(c.strValue))
			{
				return c;
			}
		}
		return null;
	} 	
	

}
