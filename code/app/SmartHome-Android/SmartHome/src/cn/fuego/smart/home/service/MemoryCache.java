package cn.fuego.smart.home.service;

public class MemoryCache
{
	private static String token;

	public static String getToken()
	{
		return token;
	}

	public static void setToken(String token)
	{
		MemoryCache.token = token;
	}
	
 

}
