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
* @ClassName: KnowledgeTypeEnum 
* @Description: TODO
* @author Aether
* @date 2015-3-12 上午10:50:19 
*  
*/ 
public enum KnowledgeTypeEnum
{
	COMMON_SENSE(0,"常识"),
	HELP(1,"帮助");
 
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private KnowledgeTypeEnum(int intValue, String strValue)
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
	public static KnowledgeTypeEnum getEnumByInt(int intValue)
	{
		for (KnowledgeTypeEnum c : KnowledgeTypeEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static KnowledgeTypeEnum getEnumByStr(String strValue)
	{
		for (KnowledgeTypeEnum c : KnowledgeTypeEnum.values())
		{
			if (strValue.equals(c.strValue))
			{
				return c;
			}
		}
		return null;
	} 
}
