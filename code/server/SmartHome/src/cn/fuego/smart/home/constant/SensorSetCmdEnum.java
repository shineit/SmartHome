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
	private int statusInt;
	private SensorSetCmdEnum(int statusInt)
	{
		this.statusInt = statusInt;
	}
	public int getStatusInt()
	{
		return statusInt;
	}
}
