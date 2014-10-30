/**   
* @Title: MISPException.java 
* @Package cn.fuego.misp.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午9:15:06 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

 /** 
 * @ClassName: MISPException 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午9:15:06 
 *  
 */
public class MISPException extends RuntimeException
{
	/**
	 * 
	 */

	public MISPException()
	{
		super();
	}
	
	public MISPException(int errorCode)
	{
		super(String.valueOf(errorCode));
	}

	public MISPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		// super(message, cause, enableSuppression, writableStackTrace);
	}

	public MISPException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public MISPException(String message)
	{
		super(message);
	}

	public MISPException(Throwable cause)
	{
		super(cause);
	}

	@Override
	public String toString()
	{
		String message = getLocalizedMessage();
		return (message != null) ? (message) : "";

	}
}
