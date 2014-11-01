/**   
* @Title: ReceiveMessage.java 
* @Package cn.fuego.smart.home.device 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-30 下午10:44:15 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.service.ServiceContext;

 /** 
 * @ClassName: ReceiveMessage 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-30 下午10:44:15 
 *  
 */
public class ReceiveMessage
{
	public static final int CONCENTRATOR_ID_START = 0;
	public static final int CONCENTRATOR_ID_END = 3;
	
	public static final int CMD_CODE_INDEX = 4;
	public static final int PACKET_NUM_INDEX = 5;
	public static final int DATA_NUM_INDEX = 6;
	
	public static final int DATA_START_INDEX = 7;
	
 	private byte[] dataBytes;

 	private String ipAddr;
	private int concentratorID;
	private int cmdCode;
	private int packetNum;	
	private int dataNum;
	
	public ReceiveMessage(String allMessage,String ipAddr)
	{
		this.dataBytes = allMessage.getBytes();
		this.ipAddr = ipAddr;
		this.parseMessage();
		
	}
	public void parseMessage()
	{
		int byteValue = 1;
		for (int i = CONCENTRATOR_ID_END; i >= CONCENTRATOR_ID_START; i--)
		{
			this.concentratorID += dataBytes[i - 1] * byteValue;
			byteValue *= 256;
		}
		this.cmdCode = dataBytes[CMD_CODE_INDEX];
		this.packetNum = dataBytes[PACKET_NUM_INDEX];
	    this.dataNum = dataBytes[DATA_NUM_INDEX];
	}
	
	public List<Alarm> getSensorAlarm()
	{
		List<Alarm> alarmList = new ArrayList<Alarm>();
		for(int i=DATA_START_INDEX;i<this.dataNum;i+=4)
		{
	 
			Alarm alarm = new Alarm();
			if( dataBytes[i] > 128)
			{
				int sensorID = (dataBytes[i]-128)*256 + dataBytes[i+1];
				int channelID = dataBytes[i+2];
				HomeSensor sensor = ServiceContext.getInstance().getSensorManageService().getHomeSensor(this.concentratorID, sensorID, channelID);
				alarm.setObjType(AlarmObjTypeEnmu.HOME_SENSOR.getIntValue());
				alarm.setObjID(sensor.getId());
			}
			else
			{
				int machineID = dataBytes[i];
				int loopID = dataBytes[i+1];
				int codeID = dataBytes[i+2];
				FireSensor sensor = ServiceContext.getInstance().getSensorManageService().getFireSensor(this.concentratorID, machineID, loopID,codeID);
				alarm.setObjType(AlarmObjTypeEnmu.FIRE_SENSOR.getIntValue());
				alarm.setObjID(sensor.getId());
			}
				
 
			alarm.setAlarmType(dataBytes[i+3]);
			alarmList.add(alarm);
		}
		return alarmList;
	}
	

	
	public Concentrator getConcentrator()
	{
		Concentrator concentrator = new Concentrator();
	 
		concentrator.setConcentratorID(this.concentratorID);
		concentrator.setIpAddr(ipAddr);
		int ns = 0;
		int byteValue = 1;
		for (int i = DATA_START_INDEX+3; i >= CONCENTRATOR_ID_START; i--)
		{
			ns += dataBytes[i - 1] * byteValue;
			byteValue *= 256;
		}
		
		int we = 0;
		byteValue = 1;
		for (int i = DATA_START_INDEX+7; i >= CONCENTRATOR_ID_START+4; i--)
		{
			we += dataBytes[i - 1] * byteValue;
			byteValue *= 256;
		}
		
		concentrator.setLocationWE((float)we);
		concentrator.setLocationNS((float)ns);
		
		return concentrator;
	}
 
	public int getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(int concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public int getCmdCode()
	{
		return cmdCode;
	}
	public void setCmdCode(int cmdCode)
	{
		this.cmdCode = cmdCode;
	}
	public int getPacketNum()
	{
		return packetNum;
	}
	public void setPacketNum(int packetNum)
	{
		this.packetNum = packetNum;
	}
	public int getDataNum()
	{
		return dataNum;
	}
	public void setDataNum(int dataNum)
	{
		this.dataNum = dataNum;
	}
	
	

}
