/**   
* @Title: ErrorMessageConst.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午10:42:34 
* @version V1.0   
*/ 
package cn.fuego.misp.constant;

 /** 
 * @ClassName: ErrorMessageConst 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午10:42:34 
 *  
 */
public class MISPErrorMessageConst
{
	public static final int SUCCESS = 0;  //成功

	public static final int ERROR_MSG_WRONG = 1; //消息错误
	public static final int ERROR_USER_NOT_EXISTED = 2; //用户不存在
	public static final int ERROR_LOGIN_FAILED = 3;  //登录失败
	public static final int ERROR_OLD_PASSWORD_WORD = 4; //原始密码错误
	
	public static final int CLIENT_VERSION_LOW = 5 ; // 
	
	public static String getMessageByErrorCode(int errorCode)
	{
		return String.valueOf(errorCode);
	}
	
}
