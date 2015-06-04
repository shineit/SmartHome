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
 
	
	public static final int OPREATE_DEVICE_FAILED= 10000 ; // 操作设备失败 
	public static final int PERMISSION_EXISTED= 10001 ; // 该用户对集中器权限已经存在
	
	public static final int DEVICE_IS_OFFLINE= 10002 ; // 设备离线
	
	public static final int CAMERA_LINK_ERROR=10003;//摄像头连接异常
	public static final int CAMERA_ACCOUNT_NOT_BUNDLE=10004; // 私有云账户未绑定
	
	public static final int CONCENTRATOR_NOT_EXISTED=10005;  //集中器不存在
	public static final int SENSOR_NOT_EXISTED = 10006;   //传感器不存在
	
	public static final int CONCENTRATOR_ID_WRONG = 10007;   //集中器编号输入格式不正确
	public static final int SENSOR_EXISTED = 10008;			//传感器已经存在。请检查编号
	
	  
	public static final int BUILDING_NOT_DELETED = 10009;   //楼层还未删除
	public static final int PLAN_NOT_DELETED = 10010;		//平面图还未删除
	public static final int SENSOR_LIST_NOT_DELETED = 10011;  //传感器尚未删除
	public static final int CONCENTRATOR_PASSWORD_WRONG=10012;  //集中器密码错误
	public static final int CONCENTRATOR_HAVE_BIND=10013;  //集中器已经被绑定

	
	
	
 
}
