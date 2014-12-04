
package cn.fuego.smart.home.device.listenser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.smart.home.constant.ConcentratorStatusEnum;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.ReceiveMessage;
import cn.fuego.smart.home.device.read.UdpDataReadThread;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.service.ServiceContext;

/**
 * 
* @ClassName: MessageHandler 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-31 上午12:23:55 
*
 */

public class UdpMessageHandler implements Runnable
{
	private Log log = LogFactory.getLog(UdpMessageHandler.class);	
  
	private String message; 
	private String ipAddr;
 	
	public UdpMessageHandler(String ipAddr,String message)
	{
 
		this.ipAddr = ipAddr;
		this.message = message;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		log.info("run the handle");
		handle();
	}
	
	private void handle()
	{
	    log.info("the device is " + ipAddr);
 
	    log.info("the string message is " + message);
	    log.info("the bytes is " + DataTypeConvert.toHexStringList(message));

		parseData(message);


	}

	private void parseData(String nowMessage)
	{
		  
		if(ApplicationProtocol.isValid(nowMessage))
		{
			String decodeMessage = ApplicationProtocol.decode(nowMessage);
		 
			ReceiveMessage message = new ReceiveMessage(decodeMessage,ipAddr);
			try
			{
				dispatchCmd(message);
			}
			catch(Exception e)
			{
				log.error("message handle error",e);
				log.error("the message is " + message);
			}
			returnData(message);
			 
		}
		else
		{
			log.error("the message is invalid." + nowMessage);
		}
	}
	
	private void dispatchCmd(ReceiveMessage message)
	{
		switch(message.getCmdCode())
		{
		case RecieveCommandConst.ONLINE_MSG :
			
			
			Concentrator concentrator = message.getConcentrator();
			concentrator.setStatus(ConcentratorStatusEnum.ONLINE.getIntValue());
			ServiceContext.getInstance().getConcentratorManageService().online(concentrator);
			
			break;
		case RecieveCommandConst.ALARM_MSG:
			if(0 == message.getDataNum())
			{
				log.info("the message is heart packet");
			}
			else
			{
				ServiceContext.getInstance().getAlarmManageService().create(message.getSensorAlarm());
			}
			
			break;
		case RecieveCommandConst.HISTORY_MSG:
			break;
  
		default:	
		
		}
	}
	
	private void returnData(ReceiveMessage message)
	{
 		try
		{
			StringBuffer buf = new StringBuffer();
 
			buf.append(DataTypeConvert.intToByteStr(message.getConcentratorID()));
			buf.append(DataTypeConvert.intToByteStr(RecieveCommandConst.PACKET_RECV_MSG,1));
			buf.append(DataTypeConvert.intToByteStr(0,1));

			//String encodeStr = ApplicationProtocol.encode(buf.toString());
			 
			//todo send message
 		}
		catch (Exception e)
		{
			log.error("write data error",e);
		}

	}

 

	
 
}
