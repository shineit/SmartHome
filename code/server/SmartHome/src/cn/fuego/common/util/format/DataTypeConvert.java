/**   
* @Title: DataConvert.java 
* @Package cn.fuego.misp.util.format 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-17 上午11:42:16 
* @version V1.0   
*/ 
package cn.fuego.common.util.format;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @ClassName: DataConvert 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-17 上午11:42:16 
 *  
 */

public class DataTypeConvert
{
	private static final Log log = LogFactory.getLog(DataTypeConvert.class);

	public static String intToByteStr(int value)
	{
		 byte[] src = new byte[4];  
		    src[3] =  (byte) ((value>>24) & 0xFF);  
		    src[2] =  (byte) ((value>>16) & 0xFF);  
		    src[1] =  (byte) ((value>>8) & 0xFF);    
		    src[0] =  (byte) (value & 0xFF);     
		 
		 return new String(src);
	}
	
	public static String floatToByteStr(float value)
	{ 
		int fbit = Float.floatToIntBits(value);  
	      
	    byte[] b = new byte[4];    
	    for (int i = 0; i < 4; i++) {    
	        b[i] = (byte) (fbit >> (24 - i * 8));    
	    } 
	    return new String(b);
		 
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
		if(null != str)
		{
			for(int i=0;i<str.length();i++)
			{
				hexList.add(Integer.toHexString(str.charAt(i)));
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
			object = DateUtil.stringToDate(value);
		}
		else
		{
			log.error("can not convert to right type.the type is " + fieldClass + "the value is " + value);
		}
		return object;
	}
}
