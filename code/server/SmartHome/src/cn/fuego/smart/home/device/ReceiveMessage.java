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
	private int concentratorID;
	private int cmdCode;
	private int packetNum;	
	private int dataNum;
	
	public static int CTL_GROUP_DEFAULT = 0xff;
	public static int CTL_DESP_DEFAULT = 0x00;
	
	public ReceiveMessage(String allMessage,String ipAddr)
	{
		this.dataBytes = DataTypeConvert.strToBytes(allMessage);
		this.ipAddr = ipAddr;
		this.parseMessage();
		
	}
	public void parseMessage()
	{
		this.concentratorID = this.getIntValue(CONCENTRATOR_ID_START, CONCENTRATOR_ID_END);
		this.packetNum = getIntValue(PACKET_NUM_INDEX);

		this.cmdCode = getIntValue(CMD_CODE_INDEX);
	    this.dataNum = getIntValue(DATA_NUM_INDEX);
	}
	
	
	
	public Concentrator getConcentrator()
	{
		Concentrator concentrator = new Concentrator();
	 
		concentrator.setConcentratorID(this.concentratorID);
		concentrator.setIpAddr(ipAddr);
 
		concentrator.setLocationWE(this.getFloatValue(DATA_START_INDEX, DATA_START_INDEX+3));
		concentrator.setLocationNS(this.getFloatValue(DATA_START_INDEX+4, DATA_START_INDEX+7));
		
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
			
			if( dataBytes[i] == 256 && dataBytes[i+1] == 256)
			{
				int machineID = getIntValue(i+2,i+2);
				int loopID = getIntValue(i+3,i+3);
				int codeID = getIntValue(i+4,i+4);
				FireSensor sensor = ServiceContext.getInstance().getSensorManageService().getFireSensor(this.concentratorID, machineID, loopID,codeID);
				
		
				if(null != sensor)
				{
					
					alarm.setObjType(AlarmObjTypeEnmu.FIRE_SENSOR.getIntValue());
					alarm.setObjID(sensor.getId());
					alarmList.add(alarm);
				}
				else
				{
					log.error("can not find the sensor by the alarm so discard it");
				}

			}
			else
			{
				int sensorID = getIntValue(i,i+3);
				int channelID = getIntValue(i+4,i+4);;
				HomeSensor sensor = ServiceContext.getInstance().getSensorManageService().getHomeSensor(this.concentratorID, sensorID, channelID);
				if(null != sensor)
				{
					alarm.setObjType(AlarmObjTypeEnmu.HOME_SENSOR.getIntValue());
					alarm.setObjID(sensor.getId());
					alarmList.add(alarm);
				}
				else
				{
					log.error("can not find the sensor by the alarm so discard it");
					log.error("can not get sensor by concentrator is "+this.concentratorID + ",sensor id is" + sensorID + ",channel is is " + channelID);
 
				}
				
			}
				

		 
		}
		return alarmList;
	}
	
	public boolean isStart()
	{
		int index = this.DATA_START_INDEX;
		int cnt = this.getIntValue(index,index+1);
 
		int endNum = this.getIntValue(index+2,index+3);
		if(0 == endNum)
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
		if(cnt <= endNum + 8)
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
		int index = this.DATA_START_INDEX;
		//获取终端总数
		int cnt = this.getIntValue(index,index+1);
		if(cnt > 8)
		{
			log.info("");
			cnt = 8;
		}
		//获取本次包起始ID
		int endNum = this.getIntValue(index+2,index+3);
		for(int i=0;i<cnt;i ++)
		{
			int sensorID = this.getIntValue(index+4+i*6,index+4+i*6+3);
			int channelNum = this.getIntValue(index+4+i*6+4,index+4+i*6+5);
			for(int j=0;j<channelNum;j++)
			{
				HomeSensor sensor = new HomeSensor();
				sensor.setConcentratorID(this.concentratorID);
				sensor.setSensorID(sensorID);
				sensor.setChannelID(j);
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
		for(int i=index + 17; i<=index+22;i++)
		{
			int id = getIntValue(i,i);
			if(i != CTL_GROUP_DEFAULT)
			{
				idList.add(id);
			}
		}
 
		sensor.setCtrGroupIDWithIDList(idList);
		sensor.setDescription(getStrValue(index+22,index+47));

		return sensor;
	}
	
	private int getUnsignedInt(byte by)
	{
		return by&0xff;  
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
	
	private float getFloatValue(int startIndex,int endIndex)
	{
		return (float)getIntValue(startIndex,endIndex);
	}
	
	private String getStrValue(int startIndex,int endIndex)
	{
		int length = endIndex-startIndex + 1;
		byte[] temp = new byte[length];
		for(int i=0;i<length;i++)
		{
			temp[i] = this.dataBytes[i];
		}
		return DataTypeConvert.bytesToStr(temp);
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
