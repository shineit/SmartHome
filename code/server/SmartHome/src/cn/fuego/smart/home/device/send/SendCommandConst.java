/**   
* @Title: DeviceCommand.java 
* @Package cn.fuego.smart.home.device.send 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-30 下午3:26:42 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.send;

 /** 
 * @ClassName: DeviceCommand 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-30 下午3:26:42 
 *  
 */
public class SendCommandConst
{
	public static final int CTL =  1; //查询/控制 命令
	public static final int RESET_CONCENTRATOR =  2; //集中器复位 命令
	public static final int SET_CONCENTRATOR_CONFIG =  3; //集中器设置 命令
 	public static final int GET_CONCENTRATOR_CONFIG =  4; //集中器设置查询 命令
	public static final int CLEAR_SET =  5; //集中器设置清除 命令
	public static final int GET_SENOR_LIST =  6; //终端ID号读取 命令
	public static final int SET_SENSOR_CONFIG =  7; //终端设置 命令
	public static final int GET_SENSOR_CONFIG =  8; //终端设置查询 命令
	
	public static final int MODE_CHANGE =  9; //模式转换 命令
	
	



}
