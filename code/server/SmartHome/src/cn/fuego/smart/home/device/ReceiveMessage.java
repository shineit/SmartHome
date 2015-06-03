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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DataTypeConvert;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.constant.DeviceKindEunm;
import cn.fuego.smart.home.constant.SensorStatusEnum;
import cn.fuego.smart.home.device.send.DeviceManagerImpl;
import cn.fuego.smart.home.device.send.SendCommandConst;
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
	private Log log = LogFactory.getLog(DeviceManagerImpl.class);	

	public static final int CONCENTRATOR_ID_START = 0;
	public static final int CONCENTRATOR_ID_END = 3;
	
	public static final int PACKET_NUM_INDEX = 4;
	public static final int CMD_CODE_INDEX = 5;

	public static final int DATA_NUM_INDEX = 6;
	
	public static final int DATA_START_INDEX = 7;
	
 	private byte[] dataBytes;

 	private String ipAddr;
	private long concentratorID;
	private int port;
	private int cmdCode;
	private int packetNum;	
	private int dataNum;
	

	
	public byte[] getDataBytes()
	{
		return dataBytes;
	}
	public void setDataBytes(byte[] dataBytes)
	{
		this.dataBytes = dataBytes;
	}
	public ReceiveMessage(String allMessage,String ipAddr,int port)
	{
		this.dataBytes = DataTypeConvert.strToBytes(allMessage);
		this.ipAddr = ipAddr;
		this.port = port;
		this.parseMessage();
		
	}
	public void parseMessage()
	{
		this.concentratorID = this.getLongValue(CONCENTRATOR_ID_START, CONCENTRATOR_ID_END);
		this.packetNum = (int) getIntValue(PACKET_NUM_INDEX);

		this.cmdCode = (int) getIntValue(CMD_CODE_INDEX);
	    this.dataNum = (int) getIntValue(DATA_NUM_INDEX);
	}
	
	
	
	public Concentrator getConcentrator()
	{
		Concentrator concentrator = new Concentrator();
	 
		concentrator.setConcentratorID(this.concentratorID);
		concentrator.setIpAddr(ipAddr);
		concentrator.setPort(port);
		concentrator.setConcentratorKind(ApplicationProtocol.getObjKindByID(this.concentratorID).getIntValue());
		
		if(this.isDataLengthEnough(DATA_START_INDEX+7))
		{
			concentrator.setLocationWE(this.getFloatValue(DATA_START_INDEX, DATA_START_INDEX+3));
			concentrator.setLocationNS(this.getFloatValue(DATA_START_INDEX+4, DATA_START_INDEX+7));
		}
 
		return concentrator;
	}
	
	public List<Alarm> getSensorAlarm()
	{
		List<Alarm> alarmList = new ArrayList<Alarm>();
		for(int i=DATA_START_INDEX;i<DATA_START_INDEX+this.dataNum*6;i+=6)
		{
	 
			Alarm alarm = new Alarm();
			 
			alarm.setConcentratorID(this.getConcentratorID());
			alarm.setAlarmType(getIntValue(i+5,i+5));
			alarm.setAlarmTime(DateUtil.getCurrentDateTime());
			
			int flag = this.getIntValue(i, i+1);
			if( flag == 0xffff)
			{
				log.info("the alarm is fire alarm");
				int machineID = getIntValue(i+2,i+2);
				int loopID = getIntValue(i+3,i+3);
				int codeID = getIntValue(i+4,i+4);
				
				alarm.setObjType(AlarmObjTypeEnmu.FIRE_SENSOR.getIntValue());
				alarm.setObjID(machineID);
				alarm.setObjID1(loopID);
				alarm.setObjID2(codeID);
				FireSensor sensor = ServiceContext.getInstance().getFireSensorManageService().getFireSensor(this.concentratorID, machineID, loopID,codeID);
  				if(null != sensor)
				{
 					alarmList.add(alarm);
				}
				else
				{
					log.error("can not find the sensor by the alarm so discard it.the machineID " + alarm);
				}

			}
			else
			{
				long sensorID = this.getLongValue(i, i + 3);
				int channelID = getIntValue(i + 4, i + 4);
				 
				alarm.setObjID(sensorID);
				alarm.setObjID1(channelID);
				HomeSensor sensor = ServiceContext
						.getInstance()
						.getSensorManageService()
						.getHomeSensor(this.concentratorID, sensorID, channelID);
				if (null != sensor)
				{
					alarm.setObjType(AlarmObjTypeEnmu.HOME_SENSOR.getIntValue());

					alarmList.add(alarm);
				} else
				{
					log.error("can not find the sensor by the alarm so discard it");
					log.error("can not get sensor by concentrator is " + this.concentratorID + ",sensor id is" + sensorID
							+ ",channel is is " + channelID);

				}
				
			}
				

		 
		}
		log.info("recieve device alarm" + alarmList);
		return alarmList;
	}
	
	public boolean isStart()
	{
		int index = this.DATA_START_INDEX;
		int cnt = this.getIntValue(index,index+1);
 
		int endNum = this.getIntValue(index+2,index+3);
		if(1 == endNum)
		{
			return true;
		}
		
		return false;
	}
	public boolean isEnd()
	{
		int index = this.DATA_START_INDEX;
		int cnt = this.getIntValue(index,index+1);
 
		int endNum = this.getIntValue(index+2,index+3);
		if(cnt <= endNum + 7)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * 解析终端列表
	 * @return
	 */
	public List<HomeSensor> getHomeSensorList()
	{
		Set<HomeSensor> sensorList = new HashSet<HomeSensor>();
		int index = DATA_START_INDEX;
		//获取终端总数
		int cnt = this.getIntValue(index,index+1);
		if(cnt > 7)
		{
			log.info("the cnt bigger than 8");
			cnt = 7;
		}
		//获取本次包起始ID
 		for(int i=0;i<cnt;i ++)
		{
			int sensorID = this.getIntValue(index+4+i*6,index+4+i*6+3);
			int channelNum = this.getIntValue(index+4+i*6+4,index+4+i*6+5);
			for(int j=0;j<channelNum;j++)
			{
				HomeSensor sensor = new HomeSensor();
				sensor.setConcentratorID(this.concentratorID);
				sensor.setSensorID(sensorID);
				sensor.setSensorKind(ApplicationProtocol.getObjKindByID(sensorID).getIntValue());
				sensor.setChannelID(j+1);
				sensorList.add(sensor);
			}
		}
		
		return new ArrayList<HomeSensor>(sensorList);
	}
	
	 
	
	public HomeSensor getHomeSensor()
	{
		HomeSensor sensor = new HomeSensor();
		sensor.setConcentratorID(this.concentratorID);
		
		int index = DATA_START_INDEX;
		sensor.setSensorID(getIntValue(index,index+3));
		sensor.setChannelID(getIntValue(index+4,index+4));
		
		if(getIntValue(index+5,index+5) == SendCommandConst.ENABLE_VALUE)
		{
			sensor.setStatus(SensorStatusEnum.ENABLE.getIntValue());
		}
		else
		{
			sensor.setStatus(SensorStatusEnum.DISABLE.getIntValue());
		}
		sensor.setSensorType(getIntValue(index+6,index+7));

		
		sensor.setWarnValue(getFloatValue(index+8,index+11));
		sensor.setErrorValue(getFloatValue(index+12,index+15));
		sensor.setGroupID(getIntValue(index+16,index+16));
		
		List<Integer> idList = new ArrayList<Integer>();
		
		if(dataBytes.length>=(index+22))
		{

			long ctrSensorID = this.getLongValue(index+17,index+20);
			int ctrChannelID = this.getIntValue(index+21);
			sensor.setCtrSensorID(ctrSensorID);
			sensor.setCtrChannelID(ctrChannelID);
		}
 
		sensor.setCtrGroupIDWithIDList(idList);
		sensor.setDescription(getStrValue(index+22,index+47));

		return sensor;
	}
	
	private int getUnsignedInt(byte by)
	{
		return by&0xff;  
	}
	
	private long getLongValue(int index)
	{
		int byteValue = 1;
		long intValue = 0;
		for (int i = index; i >= index; i--)
		{
			intValue += getUnsignedInt(dataBytes[i]) * byteValue;
			byteValue *= 256;
		}
		return intValue;
	}
	private long getLongValue(int startIndex,int endIndex)
	{
		long byteValue = 1;
		long intValue = 0;
		for (int i = endIndex; i >= startIndex; i--)
		{
			intValue += getUnsignedInt(dataBytes[i]) * byteValue;
			byteValue *= 256;
		}
		return intValue;
	}
	private int getIntValue(int index)
	{
		int byteValue = 1;
		int intValue = 0;
		for (int i = index; i >= index; i--)
		{
			intValue += getUnsignedInt(dataBytes[i]) * byteValue;
			byteValue *= 256;
		}
		return intValue;
	}
	private int getIntValue(int startIndex,int endIndex)
	{
		int byteValue = 1;
		int intValue = 0;
		for (int i = endIndex; i >= startIndex; i--)
		{
			intValue += getUnsignedInt(dataBytes[i]) * byteValue;
			byteValue *= 256;
		}
		return intValue;
	}
	
	private byte[] getDataBytes(int startIndex,int endIndex)
	{
		if(endIndex>startIndex)
		{
			byte[] bytes = new byte[endIndex-startIndex+1];
			
			for(int i=0;i<=endIndex-startIndex;i++)
			{
				bytes[i] = dataBytes[i+startIndex];
			}
			return bytes;

		}
		else
		{
			return new byte[0];
		}
	}
	
	private float getFloatValue(int startIndex,int endIndex)
	{
		float f2 = 0f;

		int i = this.getIntValue(startIndex,endIndex);
		f2 = Float.intBitsToFloat(i);
		if(Float.isNaN(f2))
		{
			f2=0.0f;
		}
		
		return f2;

	}
	
	private String getStrValue(int startIndex,int endIndex)
	{
		int length = endIndex-startIndex + 1;
		byte[] temp = new byte[length];
		for(int i=0;i<length;i++)
		{
			temp[i] = this.dataBytes[startIndex+i];
 		}
 		return DataTypeConvert.gbkBytesToStr(temp);
	}
	
	public boolean isDataLengthEnough(int index)
	{
		if(this.dataBytes.length<=index)
		{
			log.warn("the data length is not enough.the index is " + index +  ",the data length is " + this.dataBytes.length);
			return false;
		}
		return true;
	}
	


  
	public long getConcentratorID()
	{
		return concentratorID;
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
