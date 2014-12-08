/**   
* @Title: SensorSetCmdEnum.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午11:03:33 
* @version V1.0   
*/ 
package cn.fuego.smart.home.constant;

 /** 
 * @ClassName: SensorSetCmdEnum 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午11:03:33 
 *  
 */
public enum SensorSetCmdEnum
{
	MODIFY(0),
	STOP(1),
	START(2),
	CLEAR(3);
	private int intValue;
	private SensorSetCmdEnum(int intValue)
	{
		this.intValue = intValue;
	}
 
	public int getIntValue()
	{
		return intValue;
	}

	public static SensorSetCmdEnum getEnumByInt(int intValue)
	{
		for (SensorSetCmdEnum c : SensorSetCmdEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
}
