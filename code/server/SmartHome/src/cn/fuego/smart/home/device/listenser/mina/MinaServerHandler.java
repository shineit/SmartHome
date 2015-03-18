package cn.fuego.smart.home.device.listenser.mina;

//Download by http://www.codefans.net
import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.smart.home.constant.ConcentratorStatusEnum;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.ReceiveMessage;
import cn.fuego.smart.home.device.communicator.Communicator;
import cn.fuego.smart.home.device.communicator.CommunicatorFactory;
import cn.fuego.smart.home.device.listenser.DeviceOnlineCache;
import cn.fuego.smart.home.device.listenser.RecieveCommandConst;
import cn.fuego.smart.home.device.send.DeviceManagerFactory;
import cn.fuego.smart.home.device.send.SendCommandConst;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.service.ServiceContext;

public class MinaServerHandler extends IoHandlerAdapter
{

	public static String REVICE_DATA = "ReceiveMessage";
	private final FuegoLog log = FuegoLog.getLog(getClass());

	/**
	 * 连接创建事件
	 */
	public void sessionCreated(IoSession session)
	{
		// 显示客户端的ip和端口
		log.info(session.getRemoteAddress().toString());
	}

	@Override
	/**
	 * 消息接收事件
	 */
	public void messageReceived(IoSession session, Object message)
			throws Exception
	{
		IoBuffer buf = (IoBuffer) message;
 
		byte[] bytes = new byte[buf.limit()];   
		buf.get(bytes);   


		parseData(session,DataTypeConvert.bytesToStr(bytes));
 
 	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception
	{
		super.sessionIdle(session, status);

		log.warn("the client time out," + session.getRemoteAddress());
		

		session.close(true);
		
 	}
	
	
	
	@Override
	public void sessionClosed(IoSession session) throws Exception
	{
		
		String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		int port = ((InetSocketAddress)session.getRemoteAddress()).getPort();		
		
		ServiceContext.getInstance().getConcentratorManageService().offline(clientIP, port);
		
		super.sessionClosed(session);
	}

	public void sendReturnData(IoSession session,long concentratorID,int packetNum)
	{
		synchronized (this)
		{
			try
			{
				StringBuffer buf = new StringBuffer();
	 
				buf.append(DataTypeConvert.intToByteStr(concentratorID));
				buf.append(DataTypeConvert.intToByteStr(packetNum,1));
				buf.append(DataTypeConvert.intToByteStr(RecieveCommandConst.PACKET_RECV_MSG,1));
				buf.append(DataTypeConvert.intToByteStr(0,1));
				
				byte[] bytes = DataTypeConvert.strToBytes(ApplicationProtocol.encode(buf.toString()));
				IoBuffer b = IoBuffer.allocate(bytes.length);
		 
				b.put(bytes);
				b.flip();
				session.write(b);
 
	 		}
			catch (Exception e)
			{
				log.error("send data error",e);
			}
		}
 

	}
	private void parseData(IoSession session,String receiveData)
	{
		int port = ((InetSocketAddress)session.getRemoteAddress()).getPort();
		
		String ipAddr = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		  
		if(ApplicationProtocol.isValid(receiveData))
		{
			String decodeMessage = ApplicationProtocol.decode(receiveData);
		 
			ReceiveMessage message = new ReceiveMessage(decodeMessage,ipAddr,port);
			
			sendReturnData(session,message.getConcentratorID(),message.getPacketNum());
			//DeviceManagerFactory.getInstance().getDeviceManger(message.getConcentrator()).sendReturnData(message.getPacketNum());
			try
			{
				dispatchCmd(session,message,receiveData);
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
	
	private void dispatchCmd(IoSession session,ReceiveMessage message,String allMessage)
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
		case SendCommandConst.GET_SENOR_LIST:
		{
			synchronized(session)
			{
				session.setAttribute(REVICE_DATA, allMessage);
  				session.notify();
			}
		}
		break;
  
		default:	
		{
 
		}
		
		}
	}
	
}
