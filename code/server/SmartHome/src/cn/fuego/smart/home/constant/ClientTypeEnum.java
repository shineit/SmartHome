/**   
* @Title: ClientTypeEnum.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午10:40:08 
* @version V1.0   
*/ 
package cn.fuego.smart.home.constant;

 /** 
 * @ClassName: ClientTypeEnum 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午10:40:08 
 *  
 */
public enum ClientTypeEnum 
{
	WEB_CLIENT(0,"WEB"),
	ANDRIOD_CLIENT(3,"ANDRIOD"),
	IOS_CLIENT(4,"IOS");
	private int intValue;
	private String strValue;
	
	/**
	 * @param intValue
	 * @param strValue
	 */
	private ClientTypeEnum(int intValue, String strValue)
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

 
	
}
