/**   
* @Title: CollectProtocol.java 
* @Package cn.fuego.bse.service.collector 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午11:17:27 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device;

import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.format.DataTypeConvert;


/** 
 * @ClassName: CollectProtocol 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午11:17:27 
 *  
 */

public class ApplicationProtocol
{
	public static final int MAX_LENGTH = 512;
	
	public static final int MIN_LENGTH = 12;
	public static final String PACKET_HEAD = "@@";
	public static final String PACKET_END = "##";
	
  
	public static final int DATA_NUM_LENGTH = 48;
	
	
	public static final int HOLD_CONN_TIME = SystemConfigInfo.getDeviceTimeOut()*1000;
	
	public static int CTL_GROUP_DEFAULT = 0xff;
	public static int CTL_DESP_DEFAULT = 0x00;
	
	public static boolean isValid(String data)
	{
		
		if(!data.startsWith(PACKET_HEAD))
		{
			return false;
		}
		if(!data.endsWith(PACKET_END))
		{
			return false;
		}
		if(data.length() < MIN_LENGTH)
		{
			return false;
		}
		return true;
	}
	
	public static String decode(String data)
	{
		String message = null;
		if(!isValid(data))
		{
			return message;
		}
		message = data.substring(PACKET_HEAD.length(),data.length()-PACKET_END.length());
		return message;
	}
	
 
 
	public static String getResID(String data)
	{
		String resID = "";
		int resNum = 0;
		byte[] dataBytes = data.getBytes();
		if(dataBytes.length>1)
		{
			resNum = dataBytes[0]*256 + dataBytes[1];
		}
		resID = String.valueOf(resNum);
		return resID;
	}
	
	public static String encode(String encode)
	{
		return PACKET_HEAD+encode+ DataTypeConvert.intToByteStr(getCRC8(encode), 1) + PACKET_END;
	}
	
	public static int getCRC8(String encode)
	{
		return 0;
	}
	

}
