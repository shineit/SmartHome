/**
 * TODO
 * 上午01:07:45
 */
package cn.fuego.misp.dao.file;

import java.io.InputStream;
import java.util.Properties;

import cn.fuego.common.log.FuegoLog;

/**
 * @author Administrator
 * 
 */
public class MispMessageReader
{

    private static final FuegoLog log = FuegoLog.getLog(MispMessageReader.class);
 
    private static final String CONFIG_PATH = "mispMessage_en_US.properties";
    private static MispMessageReader instance;
    private Properties prop;

    private MispMessageReader()
    {

        try
        {
            /* 而采用类加载器的话，能够更具有通用性 */
            /* 使用文件的读写的方式，文件的路径的相对路径确定了，不能修改 */
            prop = new Properties();
            InputStream inStream = MispMessageReader.class.getClassLoader()
                    .getResourceAsStream(CONFIG_PATH);
            prop.load(inStream);
            
            log.info(prop.toString());

        } catch (Exception e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }
    
	public static synchronized MispMessageReader getInstance()
	{
		if (null == instance)
		{
			instance = new MispMessageReader();
		}
		return instance;
	}

    public String getPropertyByName(String name)
    {
        return prop.getProperty(name);
    }

    
 

}
