/**   
* @Title: ReadStreamThread.java 
* @Package cn.fuego.dmsp.server 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-27 下午07:00:11 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser;

import java.io.IOException;
import java.io.InputStream;
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
public class ReadStreamThread extends Thread
{
	private Log log = LogFactory.getLog(ReadStreamThread.class);

    private InputStream inputStream;
    private Queue<String> messageBuffer;

    public ReadStreamThread(InputStream inputStream,Queue<String>  messageBuffer)
    {
    	this.inputStream = inputStream;
    	this.messageBuffer = messageBuffer;
    }
    @Override
    public void run()
    {

		int ch;
		StringBuffer line = new StringBuffer();
    	try
		{
    		
    			while((ch=inputStream.read())>=0)
				{	 
    				line.append((char)ch);
    				
    				if(line.toString().endsWith(ApplicationProtocol.PACKET_END))
    				{
    					synchronized(messageBuffer)
        	    		{
    					   log.info("read line string is :" + line);
    					   log.info("read line byte is :" + DataTypeConvert.toHexStringList(line.toString()));

    					   messageBuffer.add(line.toString());

    					   messageBuffer.notify();
        	    		}
    					line.setLength(0);
    				}

    				
    				
				}
    		
			
		}
		catch (IOException e)
		{
			log.error("read data error",e);
		}
    }
}
