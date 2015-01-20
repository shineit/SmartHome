package cn.fuego.smart.home.service;

public class MemoryCache
{
	private static String token;

	private static String version = "0.1";
	//private static String serverIp = "115.231.168.14"; //嘉兴服务器地址
	private static String serverIp = "192.168.1.106"; //本地1
	//private static String serverIp = "192.168.0.102"; //本地2
	private static String serverPort= "8080";
	private static AppDeviceInfo pushInfo= new AppDeviceInfo() ;
	
	private static LoginInfo loginInfo = new LoginInfo();
	
	private static int flag=0;//判断进入页面方式，0-表示首次进入，1-其他切换进入
	private static String cachePhone=null;//缓存电话号码，用于云视验证
	
	//public static String hostURL = "http://192.168.0.102:8080/SmartHome/rest";
	//public static String hostURL = "http://120.24.217.173:8080/SmartHome/rest";//阿里云地址
	//public static String hostURL = "http://115.231.168.14:8080/SmartHome/rest";//嘉兴服务器地址
	//public static String hostURL = "http://192.168.1.104:8080/SmartHome/rest";
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

	public static int getFlag()
	{
		return flag;
	}

	public static void setFlag(int flag)
	{
		MemoryCache.flag = flag;
	}

	public static String getCachePhone()
	{
		return cachePhone;
	}

	public static void setCachePhone(String cachePhone)
	{
		MemoryCache.cachePhone = cachePhone;
	}
 
	public static String getHostUrl()
	{
		return "http://"+MemoryCache.getServerIp()+":"+MemoryCache.getServerPort();
	}
	public static String getWebContextUrl()
	{
		return getHostUrl()+"/SmartHome";
	}
	public static String getRestUrl()
	{
		return getWebContextUrl()+"/rest";
		
	}

}
