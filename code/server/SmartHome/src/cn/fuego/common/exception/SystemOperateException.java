package cn.fuego.common.exception;

public class SystemOperateException extends RuntimeException
{
	/**
	 * 
	 */

	public SystemOperateException()
	{
		super();
	}

	public SystemOperateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		// super(message, cause, enableSuppression, writableStackTrace);
	}

	public SystemOperateException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SystemOperateException(String message)
	{
		super(message);
	}

	public SystemOperateException(Throwable cause)
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
