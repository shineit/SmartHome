/**   
* @Title: BaiduPushInfo.java 
* @Package cn.fuego.smart.home.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 下午10:44:21 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.cache;

import cn.fuego.smart.home.constant.ClientTypeEnum;

 /** 
 * @ClassName: BaiduPushInfo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 下午10:44:21 
 *  
 */
public class FuegoPushInfo
{
	private ClientTypeEnum deviceType;
	private String appID;
	private String deviceID;
	private String userID;
 
	public ClientTypeEnum getDeviceType()
	{
		return deviceType;
	}
	public void setDeviceType(ClientTypeEnum deviceType)
	{
		this.deviceType = deviceType;
	}
	public String getAppID()
	{
		return appID;
	}
	public void setAppID(String appID)
	{
		this.appID = appID;
	}
	public String getDeviceID()
	{
		return deviceID;
	}
	public void setDeviceID(String deviceID)
	{
		this.deviceID = deviceID;
	}
	public String getUserID()
	{
		return userID;
	}
	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	

}
