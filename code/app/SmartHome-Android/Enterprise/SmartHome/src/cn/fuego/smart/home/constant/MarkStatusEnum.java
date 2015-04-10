package cn.fuego.smart.home.constant;

/** 
* @ClassName: MarkStatusEnum 
* @Description: TODO
* @author Aether
* @date 2015-4-7 下午12:32:44 
*  
*/ 
public enum MarkStatusEnum
{
	NONE_MARK(0,"未标记"),
	ALREADY_MARK(1,"已标记");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private MarkStatusEnum(int intValue, String strValue)
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
	public static MarkStatusEnum getEnumByInt(int intValue)
	{
		for (MarkStatusEnum c : MarkStatusEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
	public static MarkStatusEnum getEnumByStr(String strValue)
	{
		for (MarkStatusEnum c : MarkStatusEnum.values())
		{
			if (strValue.equals(c.strValue))
			{
				return c;
			}
		}
		return null;
	} 	
	

}
