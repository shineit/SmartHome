/**   
* @Title: IntentCodeConst.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Aether
* @date 2014-12-26 下午3:25:49 
* @version V1.0   
*/ 
package cn.fuego.smart.home.constant;

/** 
 * @ClassName: IntentCodeConst 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-26 下午3:25:49 
 *  
 */
public class IntentCodeConst
{
	public static final int REQUEST_CODE= 1;
	public static final int RESULT_CODE= 10;
	
	public static final String JUMP_CLASS_NAME = "jumpClass";//intent 跳转activity名称
	
	public static final String COMPANY_ID = "companyID";//intent 传递推送的告警信息companyID
	
	public static final String COMPANY_INFO="companyInfo";//公司信息
	
	public static final String HOME_REFRESH="home_refresh";//刷新首页bageNum广播参数
	
	public static final String ALARM_KIND = "alarm_kind"; //查看历史记录时,需要传入告警类型
}
