/**   
 * @Title: SocketCommunicatorImpl.java 
 * @Package cn.fuego.smart.home.device.communicator 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-10-31 下午3:23:18 
 * @version V1.0   
 */
package cn.fuego.smart.home.device.communicator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import net.sf.ezmorph.bean.MorphDynaBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.session.IoSession;

import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.listenser.mina.MinaServer;
import cn.fuego.smart.home.device.listenser.mina.MinaServerHandler;
import cn.fuego.smart.home.device.read.ReadStreamThread;

/**
 * @ClassName: SocketCommunicatorImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-31 下午3:23:18
 * 
 */
public class MinaCommunicatorImpl implements Communicator
{
	private Log log = LogFactory.getLog(ReadStreamThread.class);

	private String ip;
	private int port;

 	private IoSession session;

	public MinaCommunicatorImpl(String ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.smart.home.device.communicator.Communicator#open()
	 */
	@Override
	public void open()
	{
		session = MinaServer.getInstance().getClientSessionBy(ip,this.port);
		if (null == session)
		{
			log.error("can not connect to server,it is not online" + ip
					+ " the port is " + port);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.fuego.smart.home.device.communicator.Communicator#sendData(java.lang
	 * .String)
	 */
	@Override
	public void sendData(String data)
	{
		if(null == session)
		{
			throw new MISPException(ErrorMessageConst.DEVICE_IS_OFFLINE);
		}
		log.info("the send byte is :"+DataTypeConvert.toHexStringList(data));
		log.info("the send string is :"+data);
		
		byte[] bytes = DataTypeConvert.strToBytes(data);
		IoBuffer b = IoBuffer.allocate(bytes.length);
 
		b.put(bytes);
		b.flip();
		session.write(b);
		
		 
		
 	}
	
 

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.smart.home.device.communicator.Communicator#readData(int)
	 */
	@Override
	public String readData(int length)
	{
		if(null == session)
		{
			throw new MISPException(ErrorMessageConst.DEVICE_IS_OFFLINE);
		}
		ReadFuture readFuture = session.read();
		
 		String message = null;
		if (readFuture.awaitUninterruptibly(SystemConfigInfo.getDeviceTimeOut()*1000, TimeUnit.MILLISECONDS))
		{
			IoBuffer buf = (IoBuffer) readFuture.getMessage();
			 
			byte[] bytes = new byte[buf.limit()];   
			buf.get(bytes);   

			message = DataTypeConvert.bytesToStr(bytes);
		} 
		else
		{
			log.error("read data time out");
			throw new MISPException();
		}
		log.info("the read byte is :"+DataTypeConvert.toHexStringList(message));
		log.info("the read string is :"+message);
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.fuego.smart.home.device.communicator.Communicator#readData(java.lang
	 * .String)
	 */
	@Override
	public String readData(String end)
	{
		 
		long start = System.currentTimeMillis ();
		try
		{
			// wait client send data to server
			synchronized (session)
			{
			
				session.wait(ApplicationProtocol.HOLD_CONN_TIME);
			}
		} 
		catch (InterruptedException e)
		{
			log.error("interrupted", e);
		}

		long now = System.currentTimeMillis ();
		long timeSoFar = now - start; //计算已经等待的时间
		String message =   (String) session.getAttribute(MinaServerHandler.REVICE_DATA);
 		session.removeAttribute(MinaServerHandler.REVICE_DATA);
		if (timeSoFar < ApplicationProtocol.HOLD_CONN_TIME)
		{
			log.info("the message returned");
		} 
		else
		{
			log.error("read data time out");
			throw new MISPException();
		}

		
		log.info("the read byte is :"+DataTypeConvert.toHexStringList(message));
		log.info("the read string is :"+message);
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.smart.home.device.communicator.Communicator#close()
	 */
	@Override
	public void close()
	{
		log.info("connector of client , so no need to close");

	}

}
