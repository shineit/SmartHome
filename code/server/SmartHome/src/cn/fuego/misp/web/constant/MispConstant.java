package cn.fuego.misp.web.constant;

import java.io.File;
import java.net.URLDecoder;

import cn.fuego.common.util.SystemConfigInfo;

public class MispConstant
{
 
	public static String getUploadPath()
	{
		return getWebAppPath()+File.separator+SystemConfigInfo.getConfigItem("UPLOAD_PATH");
	}
	public static String getWebAppPath()
	{
		String path = MispConstant.class.getClassLoader().getResource(File.separator).getPath();
		path=URLDecoder.decode(path);
		path += ".."+File.separator + ".."; 
		
		
		return path;
 	}
}
