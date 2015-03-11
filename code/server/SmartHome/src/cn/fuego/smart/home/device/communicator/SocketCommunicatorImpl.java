/**   
 * @Title: SocketCommunicatorImpl.java 
 * @Package cn.fuego.smart.home.device.communicator 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-10-31 下午3:23:18 
 * @version V1.0   
 */
package cn.fuego.smart.home.device.communicator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.read.ReadStreamThread;

/**
 * @ClassName: SocketCommunicatorImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-31 下午3:23:18
 * 
 */
public abstract class SocketCommunicatorImpl implements Communicator
{
	private Log log = LogFactory.getLog(ReadStreamThread.class);

	private Queue<String> messageBuffer = new LinkedList<String>();

	private Socket socket;
	private String ip;
	private int port;

	
	public SocketCommunicatorImpl(String ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.fuego.smart.home.device.communicator.Communicator#open(java.lang.String
	 * , java.lang.String, java.lang.String)
	 */
	@Override
	public void open()
	{
		try
		{
			socket = new Socket(ip, port);

		} catch (UnknownHostException e)
		{
			log.error("can not connect to server " + ip + " the port is " + port, e);
			throw new MISPException(e);
		
		} catch (IOException e)
		{
			log.error("can not connect to server " + ip + " the port is " + port, e);
			throw new MISPException(e);
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
		log.info("the send string is "+data);
		log.info("the send byte is :"+DataTypeConvert.toHexStringList(data));
		try
		{
			OutputStream os = socket.getOutputStream();

			os.write(data.getBytes());
		} catch (IOException e)
		{
			log.error("send data error",e);
			throw new MISPException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.smart.home.device.communicator.Communicator#readData(int)
	 */
	@Override
	public String readData(int length)
	{

		return null;
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
		String message = null;
		ReadStreamThread readThread = null;

		try
		{
			readThread = new ReadStreamThread(this.socket.getInputStream(),
					messageBuffer);

		} catch (Exception e)
		{
			log.error("open input stream failed", e);
			throw new MISPException(e);
		}

		readThread.start();

		while (true)
		{

			try
			{
				// wait client send data to server
				synchronized (messageBuffer)
				{
					messageBuffer.wait(ApplicationProtocol.HOLD_CONN_TIME);
				}
			} 
			catch (InterruptedException e)
			{
				log.error("interrupted", e);
			}

			if (!this.messageBuffer.isEmpty())
			{

				message += messageBuffer.poll();
				if(message.endsWith(end))
				{
					log.info("read message succecss");
					break;
				}
				else
				{
					log.info("the message is not finish,keep on");
				}
				 
			} 
			else
			{
				log.error("read data time out");
				throw new MISPException();
			}

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
		try
		{
			this.socket.getInputStream().close();
			this.socket.getOutputStream().close();
			this.socket.close();
		}
		catch(Exception e)
		{
			log.error("close failed",e);
			throw new MISPException(e);
		}


	}

}
