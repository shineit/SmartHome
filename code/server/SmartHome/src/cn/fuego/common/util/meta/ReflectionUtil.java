/**   
* @Title: ReflectionUtil.java 
* @Package cn.fuego.util 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 下午06:16:47 
* @version V1.0   
*/ 
package cn.fuego.common.util.meta;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.exception.CommonExceptionMsg;
import cn.fuego.common.exception.SystemOperateException;
import cn.fuego.common.util.format.DataTypeConvert;

/** 
 * @ClassName: ReflectionUtil 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 下午06:16:47 
 *  
 */

public class ReflectionUtil
{
	private static final Log log = LogFactory.getLog(ReflectionUtil.class);

	public static Class getTypeByFieldName(Class clazz,String fieldName) throws NoSuchFieldException, SecurityException
	{
		Class filedClass = clazz.getDeclaredField(fieldName).getType();
		
		return filedClass;
	}
 
 
	public static Object convertToFieldObject(Class clazz,String fieldName,String value)  
	{
		Object object = null;
		
		try
		{
			Class fieldClass = clazz.getDeclaredField(fieldName).getType();
			object = DataTypeConvert.convertStrToObject(value, fieldClass);

		}
		catch(Exception e)
		{
			log.error("can not convert to right type.the class is " + clazz + " the field name is " + fieldName + "the value is " + value);
			log.error("convert data failed",e);
			throw new SystemOperateException(CommonExceptionMsg.DATA_CONVERT_ERROR);
		}
		
	 
		
		return object;
	}

}
