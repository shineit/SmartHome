/**   
* @Title: AppDeviceInfo.java 
* @Package cn.fuego.smart.home.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 上午11:32:39 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.cache;

import cn.fuego.smart.home.constant.ClientTypeEnum;

 /** 
 * @ClassName: AppDeviceInfo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 上午11:32:39 
 *  
 */
public class AppDeviceInfo
{
	
	private ClientTypeEnum clientType;
	private String clientVersion;
	private String devToken;
	private String deviceVersion;
	public ClientTypeEnum getClientType()
	{
		return clientType;
	}
	public void setClientType(ClientTypeEnum clientType)
	{
		this.clientType = clientType;
	}
	public String getClientVersion()
	{
		return clientVersion;
	}
	public void setClientVersion(String clientVersion)
	{
		this.clientVersion = clientVersion;
	}
	public String getDevToken()
	{
		return devToken;
	}
	public void setDevToken(String devToken)
	{
		this.devToken = devToken;
	}
	public String getDeviceVersion()
	{
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion)
	{
		this.deviceVersion = deviceVersion;
	}
	 
	 
 

}
