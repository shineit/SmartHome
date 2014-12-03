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
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.ReceiveMessage;
import cn.fuego.smart.home.device.communicator.Communicator;
import cn.fuego.smart.home.device.communicator.CommunicatorFactory;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.HomeSensor;

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

		 
		String sendMessage = makeSendData(SendCommandConst.GET_SENOR_LIST,null);
		String readMessage = getData(sendMessage);
		
		ReceiveMessage recvMessage = new ReceiveMessage(readMessage, this.concentrator.getAddr());
		
		List<HomeSensor> sensorList =recvMessage.getHomeSensorList();
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

		data +=  DataTypeConvert.intToByteStr(sensor.getStatus(),1);
		
		data += DataTypeConvert.intToByteStr(sensor.getSensorType(),2);
	 
		
		data += DataTypeConvert.floatToByteStr(sensor.getWarnValue());
		data += DataTypeConvert.floatToByteStr(sensor.getErrorValue());

		data += DataTypeConvert.intToByteStr(sensor.getGroupID(),1);

		for(int i=0;i<5;i++)
		{
		   if(i<sensor.getCtrGroupIDList().size())
		   {
			   data += DataTypeConvert.intToByteStr(sensor.getCtrGroupIDList().get(i),1);
		   }
		   else
		   {
			   data += DataTypeConvert.intToByteStr(0x00,1);
			   
		   }
		}
		
		data += sensor.getDescription();
		
		
		
		String sendMessage = makeSendData(SendCommandConst.SET_SENSOR_CONFIG,data);
		String readMessage = getData(sendMessage);
		
		log.info("the read message is " + readMessage);

		
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
		Random random1 = new Random(128);
		String sendMessage; 
		sendMessage = DataTypeConvert.intToByteStr(this.concentrator.getConcentratorID());
		sendMessage += DataTypeConvert.intToByteStr(random1.nextInt(), 1);
		sendMessage +=  DataTypeConvert.intToByteStr(cmdCode, 1); 
	 
		return ApplicationProtocol.encode(sendMessage);
		
	}
	private String getData(String sendMessage)
	{
		String readMessage = null;
		Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(this.concentrator.getIpAddr(), port);
		communicator.open();
		communicator.sendData(sendMessage);
		readMessage = communicator.readData(ApplicationProtocol.PACKET_END);
		communicator.close();
		
		return ApplicationProtocol.decode(readMessage);
	}

}
