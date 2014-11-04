/**   
* @Title: UserTypeEnumj.java 
* @Package cn.fuego.remote.medical.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-27 下午07:00:09 
* @version V1.0   
*/ 
package cn.fuego.smart.home.constant;

/** 
* @ClassName: UserTypeEnum 
* @Description: TODO
* @author Aether
* @date 2014-11-4 上午11:32:38 
*  
*/ 
public enum UserStatusEnum
{	
	CREATED(0,"已创建"),
	APPLIED(1,"已申请"),
	REGISTERED(2,"已注册"),
	CANCELED(3,"已注销"); 
    
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private UserStatusEnum(int intValue,String strValue)
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
	
	public static UserStatusEnum getEnumByInt(int intValue)
	{
		for (UserStatusEnum c : UserStatusEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static UserStatusEnum getEnumByStr(String strValue)
	{
		for (UserStatusEnum c : UserStatusEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	} 

}
