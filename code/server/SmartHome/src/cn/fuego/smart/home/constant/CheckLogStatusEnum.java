package cn.fuego.smart.home.constant;


/** 
* @ClassName: CheckLogStatusEnum 
* @Description: TODO
* @author Aether
* @date 2015-3-18 下午12:08:00 
*  
*/ 
public enum CheckLogStatusEnum
{
	PREVIOUS(0,"历史日志"),
	LATEST(1,"最新日志");
	
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private CheckLogStatusEnum(int intValue,String strValue)
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
	
	public static CheckLogStatusEnum getEnumByInt(int intValue)
	{
		for (CheckLogStatusEnum c : CheckLogStatusEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static CheckLogStatusEnum getEnumByStr(String strValue)
	{
		for (CheckLogStatusEnum c : CheckLogStatusEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	}	 

}
