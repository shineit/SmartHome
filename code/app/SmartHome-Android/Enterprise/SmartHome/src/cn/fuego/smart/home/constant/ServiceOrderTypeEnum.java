package cn.fuego.smart.home.constant;


/** 
* @ClassName: ServiceOrderTypeEnum 
* @Description: TODO
* @author Aether
* @date 2014-11-3 上午11:34:28 
*  
*/ 
public enum ServiceOrderTypeEnum
{

	REPAIR(0,"维修"),
	INSTALL(1,"安装"),
	CONSULT(2,"咨询");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private ServiceOrderTypeEnum(int intValue,String strValue)
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
	
	public static ServiceOrderTypeEnum getEnumByInt(int intValue)
	{
		for (ServiceOrderTypeEnum c : ServiceOrderTypeEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static ServiceOrderTypeEnum getEnumByStr(String strValue)
	{
		for (ServiceOrderTypeEnum c : ServiceOrderTypeEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	}	 

}
