/**   
* @Title: AppLoginCache.java 
* @Package cn.fuego.smart.home.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 上午11:29:20 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.cache;

import java.util.HashMap;
import java.util.Map;

 /** 
 * @ClassName: AppLoginCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 上午11:29:20 
 *  
 */
public class AppLoginCache
{
	private static Map<String,AppLoginInfo> loginUser = new HashMap<String,AppLoginInfo>();
	
	
	public static void login(String token, AppLoginInfo appLoginInfo)
	{
		loginUser.put(token, appLoginInfo);
	}
	
	public static boolean tokenValid(String token)
	{
		AppLoginInfo app = loginUser.get(token);
		if(null == app)
		{
			return false; 
		}
		return true;
	}
	
	public static boolean userLogined(String userName)
	{
		for (Map.Entry<String,AppLoginInfo> entry : loginUser.entrySet())
		{
			AppLoginInfo appInfo = entry.getValue();
			if(appInfo.getUser().getUserName() == userName)
			{
				return true;
			}
		}
		return false;
	}
	
	public static FuegoPushInfo getPushInfo(int userID)
	{
		for (Map.Entry<String,AppLoginInfo> entry : loginUser.entrySet())
		{
			AppLoginInfo appInfo = entry.getValue();
			if(appInfo.getUser().getUserID() == userID)
			{
				return appInfo.getPushInfo();
			}
		}
		return null;
	}
	
	public static AppLoginInfo getLoginInfo(String token)
	{
		return loginUser.get(token);
	}

}
