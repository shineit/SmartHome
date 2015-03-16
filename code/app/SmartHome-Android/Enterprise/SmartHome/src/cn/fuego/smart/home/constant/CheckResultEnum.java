package cn.fuego.smart.home.constant;


/** 
* @ClassName: CheckResultEnum 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午2:49:33 
*  
*/ 
public enum CheckResultEnum
{
	NONE_SET(0,"未设置"),
	NORMAL(1,"正常"),
	ABNORMAL(2,"异常");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private CheckResultEnum(int intValue, String strValue)
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
	public static CheckResultEnum getEnumByInt(int intValue)
	{
		for (CheckResultEnum c : CheckResultEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}	
	public static CheckResultEnum getEnumByStr(String strValue)
	{
		for (CheckResultEnum c : CheckResultEnum.values())
		{
			if (strValue.equals(c.strValue))
			{
				return c;
			}
		}
		return null;
	} 	
	

}
