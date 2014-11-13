package cn.fuego.common.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FuegoLog
{
	private Log log;
	private Class clazz;
	
	private FuegoLog(Class clazz)
	{
		this.clazz = clazz;
		log = LogFactory.getLog(clazz);
	}
	public static FuegoLog getLog(Class clazz)
	{
		FuegoLog instance = new FuegoLog(clazz);
		return instance;
	}

	public void info(String message)
	{
		log.info(message);
	}

	public void info(String message,Throwable e)
	{
		log.info(message, e);
	}
	
	public void warn(String message)
	{
		log.warn(message);
	}
	
	public void warn(String message,Throwable e)
	{
		log.warn(message, e);
	}
	public void error(String message)
	{
		log.error(message);
	}

	public void error(String message,Throwable e)
	{
		log.error(message, e);
	}


	public void trace(String message)
	{
		log.trace(message);
	}
	
	public void trace(String message,Throwable e)
	{
		log.trace(message, e);
	}

}
