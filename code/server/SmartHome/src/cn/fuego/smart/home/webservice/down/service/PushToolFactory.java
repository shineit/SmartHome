/**   
* @Title: PushToolFactory.java 
* @Package cn.fuego.smart.home.webservice.down.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-8 上午11:35:34 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.service;

import cn.fuego.misp.domain.SystemUser;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.FuegoPushInfo;
import cn.fuego.smart.home.webservice.down.service.impl.JPushToolImpl;

 /** 
 * @ClassName: PushToolFactory 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-8 上午11:35:34 
 *  
 */
public class PushToolFactory
{
	private static PushToolFactory instance;

	private PushToolFactory()
	{

	}

	public static synchronized PushToolFactory getInstance()
	{
		if (null == instance)
		{
			instance = new PushToolFactory();
		}
		return instance;
	}

	public synchronized PushToolInterface getPushTool()
	{
		 
		return new JPushToolImpl();
	}
	
	/**
	 * 采用极光推送,获取特定用户推送的推送信息，极光推送采用别名的方式，可以把系统用户名注册到
	 * 极光推送，所以只要根据系统用户名就可以推送到特定的用户。
	 * @param userID
	 * @return
	 */
	public FuegoPushInfo getPushInfo(int userID)
	{
		FuegoPushInfo push = new FuegoPushInfo();
		
		SystemUser user = ServiceContext.getInstance().getUserManageService().get(userID);
		
		push.setUserID(user.getUserName());
		
		return push;
	}

}
