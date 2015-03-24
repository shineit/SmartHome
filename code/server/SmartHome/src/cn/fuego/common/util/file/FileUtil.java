/**   
* @Title: FileUtil.java 
* @Package cn.fuego.common.util.file 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-18 上午9:42:09 
* @version V1.0   
*/ 
package cn.fuego.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;

 /** 
 * @ClassName: FileUtil 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-18 上午9:42:09 
 *  
 */
public class FileUtil
{
	private static FuegoLog log = FuegoLog.getLog(FileUtil.class);

	public static void createFile(File file,String path)
	{
		try
		{
			
			InputStream is = new FileInputStream(file);
 
			File destFile = new File(path);

			OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[400];

			int length = 0;
			while ((length = is.read(buffer)) > 0)
			{
				os.write(buffer, 0, length);
			}

			is.close();
			os.close();
		} catch (Exception e)
		{
			log.error("write file error,the destination path is " + path,e);
			throw new MISPException(MISPErrorMessageConst.ERROR_WRITE_FILE);
		}
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String filePath)
	{  

 
		String sPath= filePath;
 		boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) 
	    {  
	        file.delete();  
	        flag = true;  
	    } 
	    else
	    {
	    	throw new MISPException(MISPErrorMessageConst.ERROR_FILE_NOT_EXIST);
	    }
	    return flag;  
	} 


}
