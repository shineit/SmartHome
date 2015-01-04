package cn.fuego.common.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;


public class FuegoLog implements Log
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
	
	private String getLoactionInfo()
	{
		LogLocation logLocation = new LogLocation(new Throwable(), FuegoLog.class.getName());
		return  "["+logLocation.getClassName()+"." + logLocation.getMethodName()+ "(" + logLocation.getFileName() +":" + logLocation.getLineNumber() +")"+ "] ";
	}

	public void info(Object message)
	{
        log.info(getLoactionInfo()+message);
	}

	public void info(Object message,Throwable e)
	{
		log.info(getLoactionInfo()+message, e);
	}
	
	public void warn(Object message)
	{
		log.warn(getLoactionInfo()+message);
	}
	
	public void warn(Object message,Throwable e)
	{
		log.warn(getLoactionInfo()+message, e);
	}
	public void error(Object message)
	{
		log.error(getLoactionInfo()+message);
	}

	public void error(Object message,Throwable e)
	{
		log.error(message, e);
	}


	public void trace(Object message)
	{
		log.trace(message);
	}
	
	public void trace(Object message,Throwable e)
	{
		log.trace(message, e);
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object)
	 */
	@Override
	public void debug(Object arg0)
	{
		log.debug(arg0);
		
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object, java.lang.Throwable)
	 */
	@Override
	public void debug(Object arg0, Throwable arg1)
	{
		log.debug(arg0, arg1);
		
	}
 
	@Override
	public void fatal(Object arg0)
	{
		log.fatal(arg0);
		
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#fatal(java.lang.Object, java.lang.Throwable)
	 */
	@Override
	public void fatal(Object arg0, Throwable arg1)
	{
		log.fatal(arg0, arg1);
		
	}
	 
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isDebugEnabled()
	 */
	@Override
	public boolean isDebugEnabled()
	{
		// TODO Auto-generated method stub
		return log.isDebugEnabled();
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isErrorEnabled()
	 */
	@Override
	public boolean isErrorEnabled()
	{
		// TODO Auto-generated method stub
		return log.isErrorEnabled();
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isFatalEnabled()
	 */
	@Override
	public boolean isFatalEnabled()
	{
		// TODO Auto-generated method stub
		return log.isFatalEnabled();
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isInfoEnabled()
	 */
	@Override
	public boolean isInfoEnabled()
	{
		// TODO Auto-generated method stub
		return log.isInfoEnabled();
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isTraceEnabled()
	 */
	@Override
	public boolean isTraceEnabled()
	{
		// TODO Auto-generated method stub
		return log.isTraceEnabled();
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isWarnEnabled()
	 */
	@Override
	public boolean isWarnEnabled()
	{
		// TODO Auto-generated method stub
		return log.isWarnEnabled();
	}
 

}
