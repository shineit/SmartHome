package cn.fuego.smart.home.webservice.up.model.base;

import cn.fuego.smart.home.constant.ErrorMessageConst;


/**
 * 
* @ClassName: SetResultJson 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:16 
*
 */
public class SetResultJson
{
	private int errorCode = ErrorMessageConst.SUCCESS;
	private String errorMsg;
	private String obj;
 
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
	public String getObj()
	{
		return obj;
	}
	public void setObj(String obj)
	{
		this.obj = obj;
	}
	@Override
	public String toString()
	{
		return "SetResultJson [errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + ", obj=" + obj + "]";
	}
	
	

}
