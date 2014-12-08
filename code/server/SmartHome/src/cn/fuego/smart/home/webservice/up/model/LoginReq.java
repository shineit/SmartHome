package cn.fuego.smart.home.webservice.up.model;


/**
 * 
* @ClassName: LoginReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:19 
*
 */
public class LoginReq
{
	private String userName;
	private String password;
	private int clientType;
	private String clientVersion;
	private String devToken;
	
	private  String push_appID;
	private  String push_userID;
	private  String push_channelID;
	
	
	
	public String getPush_appID()
	{
		return push_appID;
	}
	public void setPush_appID(String push_appID)
	{
		this.push_appID = push_appID;
	}
	public String getPush_userID()
	{
		return push_userID;
	}
	public void setPush_userID(String push_userID)
	{
		this.push_userID = push_userID;
	}
	public String getPush_channelID()
	{
		return push_channelID;
	}
	public void setPush_channelID(String push_channelID)
	{
		this.push_channelID = push_channelID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
 
	public int getClientType()
	{
		return clientType;
	}
	public void setClientType(int clientType)
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
	@Override
	public String toString()
	{
		return "LoginReq [userName=" + userName + ", password=" + password
				+ ", clientType=" + clientType + ", clientVersion="
				+ clientVersion + ", devToken=" + devToken + ", push_appID="
				+ push_appID + ", push_userID=" + push_userID
				+ ", push_channelID=" + push_channelID + "]";
	}
 
	

}
