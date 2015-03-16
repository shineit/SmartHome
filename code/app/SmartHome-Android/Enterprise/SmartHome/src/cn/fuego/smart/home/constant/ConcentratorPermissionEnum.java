package cn.fuego.smart.home.constant;


/**
 * 
* @ClassName: ConcentratorStatusEnum 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:39 
*
 */
public enum ConcentratorPermissionEnum
{
	READ(0,"只读"),
	MODIFY(1,"修改"),
	DELETE(2,"删除"),
	ALL(3,"所有");
	private Integer intValue;
	private String strValue;
	
	/**
	 * @param intValue
	 * @param strValue
	 */
	private ConcentratorPermissionEnum(Integer intValue, String strValue)
	{
		this.intValue = intValue;
		this.strValue = strValue;
	}
	public Integer getIntValue()
	{
		return intValue;
	}
	public String getStrValue()
	{
		return strValue;
	}
	public static ConcentratorPermissionEnum getEnumByInt(Integer intValue)
	{
		for (ConcentratorPermissionEnum c : ConcentratorPermissionEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static ConcentratorPermissionEnum getEnumByStr(String strValue)
	{
		for (ConcentratorPermissionEnum c : ConcentratorPermissionEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	} 
}
