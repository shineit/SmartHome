package cn.fuego.smart.home.service;

import android.content.Context;
import android.telephony.TelephonyManager;


public class MemoryCache
{
	private static String token;

	private static String version = "0.1";
	private static String serverIp = "192.168.1.102";
	private static String serverPort= "8080";
	 
 
	 
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
 
	
 

}
