/**   
* @Title: AlarmObjTypeEnmu.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-1 上午10:27:47 
* @version V1.0   
*/ 
package cn.fuego.smart.home.constant;

 /** 
 * @ClassName: AlarmObjTypeEnmu 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-1 上午10:27:47 
 *  
 */
public enum AlarmObjTypeEnmu  
{
	CONCENTRATOR_ALARM(0,"集中器"),
	HOME_SENSOR(1,"家庭终端"),
	FIRE_SENSOR(2,"消防终端");
 
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private AlarmObjTypeEnmu(int intValue, String strValue)
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
