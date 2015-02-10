/**   
* @Title: DeviceManagerImpl.java 
* @Package cn.fuego.smart.home.device.send 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-31 下午3:09:23 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.send;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.SensorStatusEnum;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.ReceiveMessage;
import cn.fuego.smart.home.device.communicator.Communicator;
import cn.fuego.smart.home.device.communicator.CommunicatorFactory;
import cn.fuego.smart.home.device.listenser.DeviceOnlineCache;
import cn.fuego.smart.home.device.listenser.RecieveCommandConst;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.domain.SensorType;
import cn.fuego.smart.home.service.ServiceContext;

 /** 
 * @ClassName: DeviceManagerImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-31 下午3:09:23 
 *  
 */
public class DeviceManagerImpl implements DeviceManager
{

	private Log log = LogFactory.getLog(DeviceManagerImpl.class);	

	private Concentrator concentrator;
	private int port = Integer.valueOf(SystemConfigInfo.getDevicePort());
	 
	
	public DeviceManagerImpl(Concentrator concentrator)
	{
		this.concentrator = concentrator;
	}
	
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#reset(cn.fuego.smart.home.domain.Concentrator)
	 */
	@Override
	public void reset(Concentrator concentrator)
	{
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#clear(cn.fuego.smart.home.domain.Concentrator)
	 */
	@Override
	public void clear(Concentrator concentrator)
	{
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#setConfig(cn.fuego.smart.home.domain.Concentrator)
	 */
	@Override
	public void setConfig(Concentrator concentrator)
	{
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#getConfig()
	 */
	@Override
	public Concentrator getConfig()
	{
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#getSensorData(java.util.List)
	 */
	@Override
	public void getSensorData(List<HomeSensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#getSensorList(java.lang.String)
	 */
	@Override
	public List<HomeSensor> getSensorList()
	{
		log.info("get home sensor list");

		 
		String sendMessage = makeSendData(SendCommandConst.GET_SENOR_LIST,null);
		String readMessage = getData(sendMessage);
		
		ReceiveMessage recvMessage = new ReceiveMessage(readMessage, this.concentrator.getIpAddr(),this.concentrator.getPort());
		
		while(!recvMessage.isStart())
		{
			log.warn("the message is not start, so discard it");
			recvMessage = new ReceiveMessage(getData(sendMessage), this.concentrator.getIpAddr(),this.concentrator.getPort());
		}
		List<HomeSensor> sensorList = new ArrayList<HomeSensor>();
	    while(!recvMessage.isEnd())
	    {
	    	log.info("the message is not end, read again");
	    	sensorList.addAll(recvMessage.getHomeSensorList());
			recvMessage = new ReceiveMessage(getData(sendMessage), this.concentrator.getIpAddr(),this.concentrator.getPort());
	    }
    	sensorList.addAll(recvMessage.getHomeSensorList());

	    
		return sensorList;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#setSensor(cn.fuego.smart.home.domain.Sensor)
	 */
	@Override
	public void setSensor(HomeSensor sensor)
	{
		String data = ""; 	
		
		data += DataTypeConvert.intToByteStr(sensor.getSensorID(),4);
		data += DataTypeConvert.intToByteStr(sensor.getChannelID(),1);

		if(sensor.getStatus() == SensorStatusEnum.ENABLE.getIntValue())
		{
			data +=  DataTypeConvert.intToByteStr(SendCommandConst.ENABLE_VALUE,1);
		}
		else
		{
			data +=  DataTypeConvert.intToByteStr(SendCommandConst.DISABLE_VALUE,1);
		}

		
		data += DataTypeConvert.intToByteStr(sensor.getSensorType(),2);
	 
		
		data += DataTypeConvert.floatToByteStr(sensor.getWarnValue());
		data += DataTypeConvert.floatToByteStr(sensor.getErrorValue());

		data += DataTypeConvert.intToByteStr(sensor.getGroupID(),1);

	    data += DataTypeConvert.intToByteStr(sensor.getCtrSensorID(),4);
		data += DataTypeConvert.intToByteStr(sensor.getCtrChannelID(),1);
		 
 
		String temp = sensor.getDescription();
		int i = 0;
		if(null != temp)
		{
			i=temp.length();
		}
 
		for(;i<26;i++)
		{
			temp += " ";
		}
		data += DataTypeConvert.bytesToStr(DataTypeConvert.strToGbkBytes(temp));
		
		
		
		String sendMessage = makeSendData(SendCommandConst.SET_SENSOR_CONFIG,data);
		String readMessage = getData(sendMessage);
		
		log.info("the read message is " + readMessage);

		
	}
	public void getSensor(HomeSensor sensor)
	{
		log.info("get home sensor config,the sensor is" + sensor);
        String data = ""; 	
		
		data += DataTypeConvert.intToByteStr(sensor.getSensorID(),4);
		data += DataTypeConvert.intToByteStr(sensor.getChannelID(),1);
		String sendMessage = makeSendData(SendCommandConst.GET_SENSOR_CONFIG,data);
		String readMessage = getData(sendMessage);
		
		ReceiveMessage recvMessage = new ReceiveMessage(readMessage, this.concentrator.getIpAddr(),this.concentrator.getPort());
		HomeSensor deviceSensor  = recvMessage.getHomeSensor();
 
		if(null != deviceSensor)
		{
			sensor.setCtrGroupID(deviceSensor.getCtrGroupID());
			sensor.setDescription(deviceSensor.getDescription());
			sensor.setCtrSensorID(deviceSensor.getCtrSensorID());
			sensor.setCtrChannelID(deviceSensor.getCtrChannelID());
			sensor.setGroupID(deviceSensor.getGroupID());
			sensor.setErrorValue(deviceSensor.getErrorValue());
			sensor.setWarnValue(deviceSensor.getWarnValue());
	 
			sensor.setSensorType(deviceSensor.getSensorType());
			SensorType type = ServiceContext.getInstance().getSensorTypeManageService().get(sensor.getSensorType());
			if(null != type)
		    {
				sensor.setSensorTypeName(type.getTypeName());	
		    }
			
			sensor.setStatus(deviceSensor.getStatus());
			//sensor.setMark(deviceSensor.getMark());
			//sensor.setDescription(deviceSensor.getDescription());

		}
 

	}


	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#enableSensor(java.util.List)
	 */
	@Override
	public void enableSensor(List<HomeSensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#disableSensor(java.util.List)
	 */
	@Override
	public void disableSensor(List<HomeSensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#startSensor(java.util.List)
	 */
	@Override
	public void startSensor(List<HomeSensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#stopSensor(java.util.List)
	 */
	@Override
	public void stopSensor(List<HomeSensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}
	
	private String makeSendData(int cmdCode,String data)
	{
		int dataLength = 0;
		if(null != data)
		{
			dataLength = data.length();
		}
		Random random1 = new Random(128);
		String sendMessage; 
		sendMessage = DataTypeConvert.intToByteStr(this.concentrator.getConcentratorID());
		sendMessage += DataTypeConvert.intToByteStr(random1.nextInt(), 1);
		sendMessage +=  DataTypeConvert.intToByteStr(cmdCode, 1); 
		sendMessage += DataTypeConvert.intToByteStr(dataLength,1);
		sendMessage += data;
	 
		return ApplicationProtocol.encode(sendMessage);
		
	}
	private String getData(String sendMessage)
	{
		String readMessage = null;
		
		

		synchronized (this)
		{
			if(!DeviceOnlineCache.getInstance().isOnline(concentrator))
			{
				throw new MISPException(ErrorMessageConst.DEVICE_IS_OFFLINE);
			}
			Concentrator cacheDevice = DeviceOnlineCache.getInstance().getConcentrator(concentrator.getConcentratorID());
			if(null == cacheDevice)
			{
				throw new MISPException(ErrorMessageConst.DEVICE_IS_OFFLINE);
			}
			this.concentrator.setIpAddr(cacheDevice.getIpAddr());
			this.concentrator.setPort(cacheDevice.getPort());

			Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(this.concentrator.getIpAddr(), concentrator.getPort());
			communicator.open();
			communicator.sendData(sendMessage);
			readMessage = communicator.readData(ApplicationProtocol.PACKET_END);
			communicator.close();
		}

		
		return ApplicationProtocol.decode(readMessage);
	}
	
	public void sendReturnData(int packetNum)
	{
		synchronized (this)
		{
			try
			{
				StringBuffer buf = new StringBuffer();
	 
				buf.append(DataTypeConvert.intToByteStr(this.concentrator.getConcentratorID()));
				buf.append(DataTypeConvert.intToByteStr(packetNum,1));
				buf.append(DataTypeConvert.intToByteStr(RecieveCommandConst.PACKET_RECV_MSG,1));
				buf.append(DataTypeConvert.intToByteStr(0,1));
				
				Concentrator cacheDevice = DeviceOnlineCache.getInstance().getConcentrator(concentrator.getConcentratorID());
				if(null != cacheDevice)
				{
					this.concentrator.setIpAddr(cacheDevice.getIpAddr());
					this.concentrator.setPort(cacheDevice.getPort());
				}
				
				Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(this.concentrator.getIpAddr(), concentrator.getPort());
				communicator.open();
				communicator.sendData(ApplicationProtocol.encode(buf.toString()));
				communicator.close();
				 
	 		}
			catch (Exception e)
			{
				log.error("send data error",e);
			}
		}
 

	}

}
