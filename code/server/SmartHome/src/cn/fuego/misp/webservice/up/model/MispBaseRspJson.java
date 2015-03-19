package cn.fuego.misp.webservice.up.model;

import java.io.Serializable;

import cn.fuego.smart.home.constant.ErrorMessageConst;


/**
 * 
* @ClassName: BaseJsonRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:45 
*
 */
public class MispBaseRspJson implements Serializable
{
	 
	protected int errorCode = ErrorMessageConst.SUCCESS;
	protected String errorMsg;
	protected Object obj;
	public int getErrorCode()
	{
		return errorCode;
	}
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
	public Object getObj()
	{
		return obj;
	}
	public void setObj(Object obj)
	{
		this.obj = obj;
	}
 
	
	
}
