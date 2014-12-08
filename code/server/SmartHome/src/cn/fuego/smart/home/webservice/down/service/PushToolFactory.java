/**   
* @Title: PushToolFactory.java 
* @Package cn.fuego.smart.home.webservice.down.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-8 上午11:35:34 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.service;

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

}
