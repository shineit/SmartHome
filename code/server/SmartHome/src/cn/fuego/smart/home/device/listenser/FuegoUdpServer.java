/**   
* @Title: Server.java 
* @Package cn.fuego.dmsp.main.server 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-26 下午10:50:42 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.smart.home.device.ApplicationProtocol;

/** 
 * @ClassName: Server 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-26 下午10:50:42 
 *  
 */

public class FuegoUdpServer extends Thread
{
	private Log log = LogFactory.getLog(FuegoUdpServer.class);

	private static final int MILLIS_NUM_OF_SEC = 1000;
	private static final int DEFUALT_PERIOD = 30;


	private DatagramSocket  serverSocket;
 	private int listenPort = 8600;
 
	private ExecutorService threadPool = Executors.newFixedThreadPool(100);
  
  	private Queue<String>  messageBuffer = new LinkedList<String>();
  	
  	private Timer timer = new Timer();
  	
	private Map<String,Long> deviceCache = new HashMap<String,Long>();

 
	public void init(int listenPort)
	{
		this.listenPort = listenPort;
		DeviceOnlineTask collectorTask  = new DeviceOnlineTask(deviceCache);

		timer.schedule(collectorTask,0,DEFUALT_PERIOD*MILLIS_NUM_OF_SEC);
	}
	
    @Override
    public void run()
    {
    	log.info("the input server thread");

		try
		{
			serverSocket = new DatagramSocket(listenPort);
			
 			while(true)
			{
 				 
 				byte[] buf = new byte[ApplicationProtocol.MAX_LENGTH];
 				DatagramPacket packet = new DatagramPacket(buf, buf.length);  
 				
 				serverSocket.receive(packet);  
 				
 				
 				String ipAddr =  packet.getAddress().getHostAddress();
 				int port = packet.getPort();
 				String message = DataTypeConvert.bytesToStr(buf);
 				message = message.substring(0, packet.getLength());
 				
 				log.info("recieve data from remote " +ipAddr);
 				this.deviceCache.put(ipAddr, System.currentTimeMillis());
 				
 				
  				threadPool.execute(new UdpMessageHandler(ipAddr,port,message));
 
			}
		}
		catch (IOException e)
		{
			log.error("start server error",e);
		}
    }
 
}
