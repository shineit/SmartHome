package cn.fuego.smart.home.service;



public class MemoryCache
{
	private static String token;

	private static String version = "0.1";
	private static String serverIp = "192.168.1.102";
	private static String serverPort= "8080";
	private static AppDeviceInfo pushInfo= new AppDeviceInfo() ;
	
	private static LoginInfo loginInfo = new LoginInfo();
    public static Boolean isLogin()
    {
    	Boolean result= false;
    	if(getToken()!=null)
    	{
    		result = true;
    	}
    	else
    	{
    		result = false;
    	}
		return result;
		
    }
	 
	public static String getToken()
	{
		return token;
	}

	public static void setToken(String token)
	{
		MemoryCache.token = token;
	}

	public static String getVersion()
	{
		return version;
	}

 

	public static String getServerIp()
	{
		return serverIp;
	}

 

	public static String getServerPort()
	{
		return serverPort;
	}

	public static AppDeviceInfo getPushInfo()
	{
		return pushInfo;
	}

	public static void setPushInfo(AppDeviceInfo pushInfo)
	{
		MemoryCache.pushInfo = pushInfo;
	}

	public static LoginInfo getLoginInfo()
	{
		return loginInfo;
	}

	public static void setLoginInfo(LoginInfo loginInfo)
	{
		MemoryCache.loginInfo = loginInfo;
	}
 
	
 

}
