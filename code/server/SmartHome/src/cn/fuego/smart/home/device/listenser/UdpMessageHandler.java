
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
import cn.fuego.smart.home.device.send.DeviceManagerFactory;
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
	    log.info("the device ip is " + ipAddr + " the port is " + this.remotePort);
 
	    log.info("the string message is " + receiveData);
	    log.info("the bytes is " + DataTypeConvert.toHexStringList(receiveData));

		parseData(receiveData);


	}

	private void parseData(String receiveData)
	{
		  
		if(ApplicationProtocol.isValid(receiveData))
		{
			String decodeMessage = ApplicationProtocol.decode(receiveData);
		 
			ReceiveMessage message = new ReceiveMessage(decodeMessage,ipAddr,this.remotePort);
			DeviceManagerFactory.getInstance().getDeviceManger(message.getConcentrator()).sendReturnData(message.getPacketNum());
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
		Concentrator concentrator = message.getConcentrator();
		concentrator.setStatus(ConcentratorStatusEnum.ONLINE.getIntValue());
		DeviceOnlineCache.getInstance().refresh(concentrator);
		switch(message.getCmdCode())
		{
		case RecieveCommandConst.ONLINE_MSG :
			
			log.info("the message is online");

			DeviceOnlineCache.getInstance().online(concentrator);

			
			break;
		case RecieveCommandConst.ALARM_MSG:
			if(0 == message.getDataNum())
			{
				log.info("the message is heart packet");
			}
			else
			{
				log.info("the message is alarm");
				ServiceContext.getInstance().getAlarmManageService().create(message.getSensorAlarm());
			}
			
			break;
		case RecieveCommandConst.HISTORY_MSG:
			break;
  
		default:	
		
		}
	}
	
 

 

	
 
}
