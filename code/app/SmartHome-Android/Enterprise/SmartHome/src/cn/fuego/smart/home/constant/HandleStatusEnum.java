package cn.fuego.smart.home.constant;

/**
 * 
* @ClassName: ServiceOrderStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:56 
*
 */
public enum HandleStatusEnum
{
	APPLYED(0,"待处理"),
	HANDLED(1,"已处理");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private HandleStatusEnum(int intValue,String strValue)
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
	
	public static HandleStatusEnum getEnumByInt(int intValue)
	{
		for (HandleStatusEnum c : HandleStatusEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static HandleStatusEnum getEnumByStr(String strValue)
	{
		for (HandleStatusEnum c : HandleStatusEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	}	 

}
