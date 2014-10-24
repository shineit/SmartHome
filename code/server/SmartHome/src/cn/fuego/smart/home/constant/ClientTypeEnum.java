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
	WEB_CLIENT(0),
	ANDRIOD_CLIENT(1),
	IOS_CLIENT(2);
	private int statusInt;

	private ClientTypeEnum(int statusInt)
	{
		this.statusInt = statusInt;
		
	}
	public int getStatusInt()
	{
		return statusInt;
	}
	
}
