/**   
* @Title: KeepAliveFilter.java 
* @Package cn.fuego.smart.home.device.listenser.mina 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-5 下午8:08:01 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser.mina;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

 /** 
 * @ClassName: KeepAliveFilter 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-5 下午8:08:01 
 *  
 */
public class FuegoKeepAliveFilter extends  KeepAliveFilter
{
	private   static   final   int  INTERVAL =  30 ; //in seconds   
    private   static   final   int  TIMEOUT =  10 ;  //in seconds   

	/**
	 * @param messageFactory
	 */
	public FuegoKeepAliveFilter(KeepAliveMessageFactory messageFactory)
	{
		super (messageFactory, IdleStatus.BOTH_IDLE,  new  KeepAliveTimeoutHandler(), INTERVAL, TIMEOUT);  
		// TODO Auto-generated constructor stub
	}
	
	public  FuegoKeepAliveFilter()
	{  
        super(new  KeepAliveMessageFactoryImpl(), IdleStatus.BOTH_IDLE,  new  KeepAliveTimeoutHandler(), INTERVAL, TIMEOUT);  
       // this .setForwardEvent( false );  //此消息不会继续传递，不会被业务层看见   
    } 

}
