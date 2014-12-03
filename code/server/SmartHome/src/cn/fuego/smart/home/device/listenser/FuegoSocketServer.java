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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @ClassName: Server 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-26 下午10:50:42 
 *  
 */

public class FuegoSocketServer extends Thread
{
	private Log log = LogFactory.getLog(FuegoSocketServer.class);

	private ServerSocket serverSocket;
	private Socket socket;
	private int listenPort = 8600;
 
	private ExecutorService threadPool = Executors.newFixedThreadPool(100);
	private DataCollectionCache dataCache = new DataCollectionCache();
 
	public void init(int listenPort)
	{
		this.listenPort = listenPort;
	}
	
    @Override
    public void run()
    {
    	log.info("the input server thread");

		try
		{
			serverSocket = new ServerSocket(listenPort,20);
	    	log.info("the server started suceessfully!");

			while(true)
			{
 				log.info("the data cache is ");
 				socket = this.serverSocket.accept();
 
 				log.info("recevie a message " + socket.getRemoteSocketAddress());
 				threadPool.execute(new MessageHandler(socket, dataCache));
 				log.info("receive done" + socket.getRemoteSocketAddress());

			}
		}
		catch (IOException e)
		{
			log.error("start server error",e);
		}
    }
 
}
