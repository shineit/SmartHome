package cn.fuego.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.file.PropertyReader;

public class SystemConfigInfo
{
    private static final Log log = LogFactory.getLog(SystemConfigInfo.class);

	public static String getSystemRootPath()
	{
		String path = SystemConfigInfo.class.getResource("/").getPath();
		try
		{
			path = java.net.URLDecoder.decode(path,"utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			log.error("decode failed",e);
		}  
		return path;
	}

	public static String getProductName()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.PRODUCT_NAME);
	}
	
	public static String getDefaultPassword()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.USER_DEFAULT_PASSWOARD);
	}
	public static String getTemplatePath()
	{
		return  getSystemRootPath() + PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.TEMPLATE_PATH);
		
	}
	public static String getDevicePort()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.DEVICE_PORT);
	}

 
}
