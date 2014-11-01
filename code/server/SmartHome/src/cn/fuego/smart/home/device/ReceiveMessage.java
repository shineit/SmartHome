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
	
	public static int CTL_GROUP_DEFAULT = 0xff;
	public static int CTL_DESP_DEFAULT = 0x00;
	
	public ReceiveMessage(String allMessage,String ipAddr)
	{
		this.dataBytes = allMessage.getBytes();
		this.ipAddr = ipAddr;
		this.parseMessage();
		
	}
	public void parseMessage()
	{
		this.concentratorID = this.getIntValue(CONCENTRATOR_ID_START, CONCENTRATOR_ID_END);
		 
		this.cmdCode = getIntValue(CMD_CODE_INDEX);
		this.packetNum = getIntValue(PACKET_NUM_INDEX);
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
		for(int i=DATA_START_INDEX;i<this.dataNum;i+=4)
		{
	 
			Alarm alarm = new Alarm();
			if( dataBytes[i] > 128)
			{
				int sensorID = (dataBytes[i]-128)*256 + dataBytes[i+1];
				int channelID = getIntValue(i+2,i+2);;
				HomeSensor sensor = ServiceContext.getInstance().getSensorManageService().getHomeSensor(this.concentratorID, sensorID, channelID);
				alarm.setObjType(AlarmObjTypeEnmu.HOME_SENSOR.getIntValue());
				alarm.setObjID(sensor.getId());
			}
			else
			{
				int machineID = getIntValue(i,i);
				int loopID = getIntValue(i+1,i+1);
				int codeID = getIntValue(i+2,i+2);
				FireSensor sensor = ServiceContext.getInstance().getSensorManageService().getFireSensor(this.concentratorID, machineID, loopID,codeID);
				alarm.setObjType(AlarmObjTypeEnmu.FIRE_SENSOR.getIntValue());
				alarm.setObjID(sensor.getId());
			}
				
 
			alarm.setAlarmType(getIntValue(i+3,i+3));
			alarmList.add(alarm);
		}
		return alarmList;
	}
	
	public HomeSensor getHomeSensor()
	{
		HomeSensor sensor = new HomeSensor();
		sensor.setConcentratorID(this.concentratorID);
		
		int index = DATA_START_INDEX;
		sensor.setSensorID(getIntValue(index,index+1));
		sensor.setId(getIntValue(index+2,index+5));
		sensor.setChannelID(getIntValue(index+6,index+6));
		sensor.setStatus(getIntValue(index+7,index+7));
		sensor.setSensorType(getIntValue(index+8,index+9));
		sensor.setWarnValue(getFloatValue(index+10,index+13));
		sensor.setErrorValue(getFloatValue(index+14,index+17));
		sensor.setGroupID(getIntValue(index+18,index+18));
		
		List<Integer> idList = new ArrayList<Integer>();
		for(int i=index + 19; i<=index+23;i++)
		{
			int id = getIntValue(i,i);
			if(i != CTL_GROUP_DEFAULT)
			{
				idList.add(id);
			}
		}
 
		sensor.setCtrGroupIDWithIDList(idList);
		sensor.setDescription(getStrValue(index+24,index+47));

		return sensor;
	}
	
	
	private int getIntValue(int index)
	{
		int byteValue = 1;
		int intValue = 0;
		for (int i = index; i >= index; i--)
		{
			intValue += dataBytes[i - 1] * byteValue;
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
			intValue += dataBytes[i - 1] * byteValue;
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
		return new String(temp);
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
