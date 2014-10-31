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

import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.smart.home.constant.SensorStatusEnum;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.device.communicator.Communicator;
import cn.fuego.smart.home.device.communicator.CommunicatorFactory;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.Sensor;

 /** 
 * @ClassName: DeviceManagerImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-31 下午3:09:23 
 *  
 */
public class DeviceManagerImpl implements DeviceManager
{

	private Concentrator concentrator;
	private int port = Integer.valueOf(SystemConfigInfo.getDevicePort());
	 
	
	public DeviceManagerImpl(Concentrator concentrator)
	{
		this.concentrator = concentrator;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#getSensorList(java.lang.String)
	 */
	@Override
	public List<Sensor> getSensorList()
	{

		 
		String sendMessage = makeSendData(DeviceCommand.GET_SENOR_LIST,null);
		String readMessage = getData(sendMessage);
		
		List<Sensor> sensorList = new ArrayList<Sensor>();
		return sensorList;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#setSensor(cn.fuego.smart.home.domain.Sensor)
	 */
	@Override
	public void setSensor(Sensor sensor)
	{
		String data = ""; 	
		data += (byte)(sensor.getSensorID()/256);
		data += (byte)(sensor.getSensorID()%256);
		data += DataTypeConvert.intToByteStr(sensor.getDevID());
		
		data += (byte)SensorStatusEnum.ENABLE.getStatusInt();
		
		data += (byte)(sensor.getSensorType()/256);
		data += (byte)(sensor.getSensorType()%256);
		
		data += DataTypeConvert.floatToByteStr(sensor.getWarnValue());
		data += DataTypeConvert.floatToByteStr(sensor.getErrorValue());

		data += (byte)sensor.getGroupID();

		for(int i=0;i<4;i++)
		{
		   if(i<sensor.getCtrGroupIDList().size())
		   {
			   data += sensor.getCtrGroupIDList().get(i).byteValue();
		   }
		   else
		   {
			   data += 0xff;
			   
		   }
		}
		
		data += sensor.getDescription();
		
		
		
		String sendMessage = makeSendData(DeviceCommand.SET_SENSOR_CONFIG,null);
		String readMessage = getData(sendMessage);

		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#enableSensor(java.util.List)
	 */
	@Override
	public void enableSensor(List<Sensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#disableSensor(java.util.List)
	 */
	@Override
	public void disableSensor(List<Sensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#startSensor(java.util.List)
	 */
	@Override
	public void startSensor(List<Sensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.device.send.DeviceManager#stopSensor(java.util.List)
	 */
	@Override
	public void stopSensor(List<Sensor> sensorList)
	{
		// TODO Auto-generated method stub
		
	}
	
	private String makeSendData(int cmdCode,String data)
	{
		Random random1 = new Random(128);
		String sendMessage; 
		sendMessage = DataTypeConvert.intToByteStr(this.concentrator.getConcentratorID());
		sendMessage += (char)random1.nextInt();
		sendMessage += (char)cmdCode;
	 
		return ApplicationProtocol.encode(sendMessage);
		
	}
	private String getData(String sendMessage)
	{
		String readMessage = null;
		Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(this.concentrator.getIpAddr(), port);
		communicator.open();
		communicator.sendData(sendMessage);
		readMessage = communicator.readData(ApplicationProtocol.PACKET_END);
		
		return readMessage;
	}

}
