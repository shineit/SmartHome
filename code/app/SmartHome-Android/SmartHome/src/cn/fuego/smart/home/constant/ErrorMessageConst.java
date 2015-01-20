/**   
* @Title: ErrorMessageConst.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午10:42:34 
* @version V1.0   
*/ 
package cn.fuego.smart.home.constant;

import cn.fuego.misp.constant.MISPErrorMessageConst;

 /** 
 * @ClassName: ErrorMessageConst 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午10:42:34 
 *  
 */
public class ErrorMessageConst extends MISPErrorMessageConst
{
 
	
	public static final int OPREATE_DEVICE_FAiLED= 10000 ; // 操作设备失败 
	public static final int PERMISSION_EXISTED= 10001 ; // 该用户对集中器权限已经存在
	
	public static final int DEVICE_IS_OFFLINE= 10002 ; // 设备离线
	
	public static final int CAMERA_LINK_ERROR=10003;//摄像头连接异常
	public static final int CAMERA_ACCOUNT_NOT_BUNDLE=10004; // 私有云账户未绑定

}
