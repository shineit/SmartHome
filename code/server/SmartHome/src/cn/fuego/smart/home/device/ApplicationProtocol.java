/**   
* @Title: CollectProtocol.java 
* @Package cn.fuego.bse.service.collector 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午11:17:27 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device;

import java.util.Collection;
import java.util.List;

/** 
 * @ClassName: CollectProtocol 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午11:17:27 
 *  
 */

public class ApplicationProtocol
{
	public static final String PACKET_HEAD = "@@";
	public static final String PACKET_END = "go";
	
  
	public static final int DATA_NUM_LENGTH = 48;
	
	
	public static final int HOLD_CONN_TIME = 60*1000; //unit is second 
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
		return PACKET_HEAD+encode+PACKET_END;
	}
	

}
