/**   
* @Title: BaiduPushInfo.java 
* @Package cn.fuego.smart.home.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 下午10:44:21 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.cache;

 /** 
 * @ClassName: BaiduPushInfo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 下午10:44:21 
 *  
 */
public class BaiduPushInfo
{
	private int deviceType = 3;
	private String appID;
	private String channelID;
	private String user_id;

	public int getDeviceType()
	{
		return deviceType;
	}

	public void setDeviceType(int deviceType)
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

	public String getChannelID()
	{
		return channelID;
	}

	public void setChannelID(String channelID)
	{
		this.channelID = channelID;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	
	

}
