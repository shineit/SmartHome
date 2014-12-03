/**   
* @Title: ReadStreamThread.java 
* @Package cn.fuego.dmsp.server 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-27 下午07:00:11 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.read;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.smart.home.device.ApplicationProtocol;

/**
 * 
* @ClassName: ReadStreamThread 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-31 上午12:23:47 
*
 */
public class UdpDataReadThread extends Thread
{
	private Log log = LogFactory.getLog(UdpDataReadThread.class);

    private DatagramSocket socket;
    private Queue<String> messageBuffer;
     

    public UdpDataReadThread(DatagramSocket inputStream,Queue<String>  messageBuffer)
    {
    	this.socket = inputStream;
     	this.messageBuffer = messageBuffer;
    }
    @Override
    public void run()
    {
 
    	try
		{
    		byte[] buf = new byte[512];
    		DatagramPacket packet = new DatagramPacket(buf, buf.length);  
    		socket.receive(packet);
    		messageBuffer.add(new String(packet.getData(),0,packet.getLength()));
    		messageBuffer.notify();
			
		}
		catch (IOException e)
		{
			log.error("read data error",e);
		}
    }
}
