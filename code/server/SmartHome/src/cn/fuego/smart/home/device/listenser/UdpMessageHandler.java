
package cn.fuego.smart.home.device.listenser;

import java.net.DatagramPacket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.smart.home.constant.ConcentratorStatusEnum;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.ReceiveMessage;
import cn.fuego.smart.home.device.communicator.Communicator;
import cn.fuego.smart.home.device.communicator.CommunicatorFactory;
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
  
	private String receiveData; 
	private String ipAddr;
	private int remotePort;
 	
	public UdpMessageHandler(String ipAddr,int remotePort,String receiveData)
	{
 
		this.ipAddr = ipAddr;
		this.receiveData = receiveData;
		this.remotePort = remotePort;
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
 
	    log.info("the string message is " + receiveData);
	    log.info("the bytes is " + DataTypeConvert.toHexStringList(receiveData));

		parseData(receiveData);


	}

	private void parseData(String receiveData)
	{
		  
		if(ApplicationProtocol.isValid(receiveData))
		{
			String decodeMessage = ApplicationProtocol.decode(receiveData);
		 
			ReceiveMessage message = new ReceiveMessage(decodeMessage,ipAddr);
			returnData(message);
			try
			{
				dispatchCmd(message);
			}
			catch(Exception e)
			{
				log.error("message handle error",e);
				log.error("the message is " + message);
			}
			
			 
		}
		else
		{
			log.error("the message is invalid." + receiveData);
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
			buf.append(DataTypeConvert.intToByteStr(message.getPacketNum(),1));
			buf.append(DataTypeConvert.intToByteStr(RecieveCommandConst.PACKET_RECV_MSG,1));
			buf.append(DataTypeConvert.intToByteStr(0,1));

			Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(this.ipAddr, this.remotePort);
			communicator.open();
			communicator.sendData(buf.toString());
			communicator.close();
			 
 		}
		catch (Exception e)
		{
			log.error("send data error",e);
		}

	}

 

	
 
}
