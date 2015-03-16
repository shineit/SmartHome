/**   
* @Title: DataConvert.java 
* @Package cn.fuego.misp.util.format 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-17 上午11:42:16 
* @version V1.0   
*/ 
package cn.fuego.common.util.format;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fuego.common.log.FuegoLog;

/** 
 * @ClassName: DataConvert 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-17 上午11:42:16 
 *  
 */

public class DataTypeConvert
{
	private static final FuegoLog log = FuegoLog.getLog(DataTypeConvert.class);

	
	public static List<String> intToStr(List<Integer> idList)
	{
		List<String> strList = new ArrayList<String>();
		for(Integer e : idList)
		{
			strList.add(String.valueOf(e));
		}
		return strList;
	}
	
	public static List<Integer> strToInt(List<String> idList)
	{
		List<Integer> strList = new ArrayList<Integer>();
		for(String e : idList)
		{
			strList.add(Integer.valueOf(e));
		}
		return strList;
	}
	
	public static String bytesToStr(byte[] bytes)
	{
		String isoString = null;
		try
		{
			isoString = new String(bytes, "ISO-8859-1");
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			log.error("convert error",e);
		}
		return isoString;
	}
	public static byte[] strToBytes(String str)
	{
		byte[] bytes = null;
		try
		{
			bytes = str.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e)
		{
			log.error("convert error",e);
		}
		return bytes;
	}
	
	public static String intToByteStr(long value)
	{
		 return  intToByteStr(value,4);
	}
	public static String intToByteStr(long value,int bit)
	{
		 if(bit > 4)
		 {
			 bit = 4;
		 }
		 byte[] src = new byte[bit];  
		
		 for(int i=0;i<bit;i++)
		 {     
			    src[bit-1-i] =  (byte) (value>>(i*8) & 0xFF);   
		 }
  
		 
		 return DataTypeConvert.bytesToStr(src);
	}
	
	public static String intToByteStr(int value)
	{
		 return  intToByteStr(value,4);
	}
	
	public static String intToByteStr(int value,int bit)
	{
		 if(bit > 4)
		 {
			 bit = 4;
		 }
		 byte[] src = new byte[bit];  
		
		 for(int i=0;i<bit;i++)
		 {     
			    src[bit-1-i] =  (byte) (value>>(i*8) & 0xFF);   
		 }
  
		 
		 return DataTypeConvert.bytesToStr(src);
	}
	
	public static String floatToByteStr(float value)
	{ 
		int fbit = Float.floatToIntBits(value);  
	      
	    byte[] b = new byte[4];    
	    for (int i = 0; i < 4; i++) {    
	        b[3-i] = (byte) (fbit >> (24 - i * 8));    
	    } 
	    return DataTypeConvert.bytesToStr(b);
		 
	}
	public static List objectToList(Object str)
	{
		List strList = new ArrayList<Object>();
		if(null != str)
		{
			strList.add(str);
		}
 
		return strList;
	}
	
	public static byte[] toAsciiBytes(String str)
	{
		byte[] bytes;
		if(null != str)
		{
			bytes = new byte[str.length()];
			for(int i=0;i<str.length();i++)
			{
				bytes[i]=(byte) str.charAt(i);
			}
 		}
		else
		{
			bytes = new byte[1];
		}
		return bytes;
	}
	public static List<String> toHexStringList(String str)
	{
		List<String> hexList = new ArrayList<String>();
		byte[] bytes = strToBytes(str);
		if(null != str)
		{
			for(int i=0;i<bytes.length;i++)
			{
				int temp = bytes[i] & 0xFF;
				hexList.add(Integer.toHexString(temp));
			}
		}
		return hexList;
	}
	public static Object convertStrToObject(String value,Class fieldClass)
	{
		 Object object = null;
		if(fieldClass == int.class || fieldClass == Integer.class)
		{
 			object = Integer.valueOf(value);
		}
		if(fieldClass == long.class || fieldClass == Long.class)
		{
 			object = Long.valueOf(value);
		}
		else if(fieldClass == float.class || fieldClass == Float.class)
		{
			object = Float.valueOf(value);
		}
		else if(fieldClass == double.class || fieldClass == Double.class)
		{
			object = Double.valueOf(value);
		}
		else if(fieldClass == boolean.class || fieldClass == Boolean.class)
		{
			object = Boolean.valueOf(value);
		}
		else if(fieldClass == byte.class || fieldClass == Byte.class)
		{
			object = Byte.valueOf(value);
		}
		else if(fieldClass == String.class )
		{
			object = value;
		}
		else if(fieldClass == Date.class)
		{
			object = DateUtil.shortStrToDate(value);
		}
		else
		{
			log.error("can not convert to right type.the type is " + fieldClass + "the value is " + value);
		}
		return object;
	}
}
