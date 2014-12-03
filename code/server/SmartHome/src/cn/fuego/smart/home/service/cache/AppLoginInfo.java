/**   
* @Title: AppLoginInfo.java 
* @Package cn.fuego.smart.home.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 下午11:11:28 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.cache;

import cn.fuego.misp.domain.SystemUser;

 /** 
 * @ClassName: AppLoginInfo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 下午11:11:28 
 *  
 */
public class AppLoginInfo
{
	private String token;

	private SystemUser user;
	private AppDeviceInfo deviceInfo = new AppDeviceInfo();
	private BaiduPushInfo pushInfo =  new BaiduPushInfo();
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public SystemUser getUser()
	{
		return user;
	}
	public void setUser(SystemUser user)
	{
		this.user = user;
	}
	public AppDeviceInfo getDeviceInfo()
	{
		return deviceInfo;
	}
	public void setDeviceInfo(AppDeviceInfo deviceInfo)
	{
		this.deviceInfo = deviceInfo;
	}
	public BaiduPushInfo getPushInfo()
	{
		return pushInfo;
	}
	public void setPushInfo(BaiduPushInfo pushInfo)
	{
		this.pushInfo = pushInfo;
	}
	
	
}
