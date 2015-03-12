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
* @ClassName: KnowledgeKindEnum 
* @Description: TODO
* @author Aether
* @date 2015-3-12 上午10:53:14 
*  
*/ 
public enum KnowledgeKindEnum
{
	COMMON(0,"通用"),
	ENTERPRISE(1,"企业"),
	HOME(2,"家庭");
	
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private KnowledgeKindEnum(int intValue, String strValue)
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
	public static KnowledgeKindEnum getEnumByInt(int intValue)
	{
		for (KnowledgeKindEnum c : KnowledgeKindEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static KnowledgeKindEnum getEnumByStr(String strValue)
	{
		for (KnowledgeKindEnum c : KnowledgeKindEnum.values())
		{
			if (strValue.equals(c.strValue))
			{
				return c;
			}
		}
		return null;
	} 
}
