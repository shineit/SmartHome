
package cn.fuego.smart.home.device.listenser.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.smart.home.constant.ConcentratorStatusEnum;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.ReceiveMessage;
import cn.fuego.smart.home.device.listenser.RecieveCommandConst;
import cn.fuego.smart.home.device.read.ReadStreamThread;
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

public class MessageHandler implements Runnable
{
	private Log log = LogFactory.getLog(MessageHandler.class);	
	private Socket socket;
 
 	
 	private Queue<String>  messageBuffer = new LinkedList<String>();
	
	public MessageHandler(Socket socket)
	{
 
		this.socket = socket;
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
		InputStream inputStream = null;
		ReadStreamThread readThread = null;
 		if(null != socket)
		{
			log.info("handling a new connection.the remote socket address is " + socket.getRemoteSocketAddress());
			try
			{
				inputStream = socket.getInputStream();
				
				
				//start a thread to read data from input stream.
				readThread = new ReadStreamThread(inputStream,messageBuffer);
				readThread.start();
 				while(true)
				{	
 
					try
					{
						//wait client send data to server
						synchronized(messageBuffer)
						{
							messageBuffer.wait(ApplicationProtocol.HOLD_CONN_TIME);
						}
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
 					if(!this.messageBuffer.isEmpty())
					{
 
 						while(!this.messageBuffer.isEmpty())
 						{	
	 						String nowMessage = messageBuffer.poll();
	 						
							parseData(nowMessage);
 						}
 					}
 					else
 					{
						log.error("the connection time out, now release the connection," + this.socket.getRemoteSocketAddress());
 						break;
 					}
  
				}
				
 			}
			catch (IOException e)
			{
				log.error("read data error",e);
			}
			finally
			{
				if(null != inputStream)
				{	
					if(null != readThread)
					{
						readThread.stop();
					}

					try
					{
						inputStream.close();
						
					}
					catch (IOException e)
					{
						log.error("colse input stream failed");
					}
					if(socket!=null)
					{
						try
						{
							socket.close();
						}
						catch (IOException e)
						{
							log.error("colse socket error",e);
						}
					}
	                    
				}
			}

		}
 
		log.info("handle done.the connection is " + socket.getRemoteSocketAddress());


	}

	private void parseData(String nowMessage)
	{
		if(ApplicationProtocol.isValid(nowMessage))
		{
			String decodeMessage = ApplicationProtocol.decode(nowMessage);
		 
			ReceiveMessage message = new ReceiveMessage(decodeMessage,this.socket.getRemoteSocketAddress().toString(),this.socket.getPort());
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
		OutputStream outputStream = null;
		try
		{
			outputStream = this.socket.getOutputStream();
			StringBuffer buf = new StringBuffer();
 
			buf.append(DataTypeConvert.intToByteStr(message.getConcentrator().getConcentratorID()));
			buf.append(DataTypeConvert.intToByteStr(RecieveCommandConst.PACKET_RECV_MSG,1));
			buf.append(DataTypeConvert.intToByteStr(0,1));

			String encodeStr = ApplicationProtocol.encode(buf.toString());
			 
			outputStream.write(encodeStr.getBytes());
		}
		catch (IOException e)
		{
			log.error("write data error",e);
		}

	}

 

	
 
}
