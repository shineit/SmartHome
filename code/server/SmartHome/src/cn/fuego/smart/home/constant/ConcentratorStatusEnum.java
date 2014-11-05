package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: ConcentratorStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:39 
*
 */
public enum ConcentratorStatusEnum
{
	OFFLINE(0,"离线"),
	ONLINE(1,"在线");
	private int intValue;
	private String strValue;
	
	/**
	 * @param intValue
	 * @param strValue
	 */
	private ConcentratorStatusEnum(int intValue, String strValue)
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
	public static ConcentratorStatusEnum getEnumByInt(int intValue)
	{
		for (ConcentratorStatusEnum c : ConcentratorStatusEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static ConcentratorStatusEnum getEnumByStr(String strValue)
	{
		for (ConcentratorStatusEnum c : ConcentratorStatusEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	} 
}
