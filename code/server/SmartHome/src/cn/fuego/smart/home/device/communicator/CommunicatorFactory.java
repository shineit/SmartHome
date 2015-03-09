/**   
* @Title: CommunicatorFactory.java 
* @Package cn.fuego.smart.home.device.communicator 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-31 下午4:29:45 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.communicator;


 /** 
 * @ClassName: CommunicatorFactory 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-31 下午4:29:45 
 *  
 */
public class CommunicatorFactory
{
	private static CommunicatorFactory instance;
 
	private CommunicatorFactory()
	{

	}

	public static synchronized CommunicatorFactory getInstance()
	{
		if (null == instance)
		{
			instance = new CommunicatorFactory();
		}
		return instance;
	}

	public synchronized Communicator getCommunicator(String ip,int port)
	{
		 
		return new MinaCommunicatorImpl(ip,port);
	}
}
