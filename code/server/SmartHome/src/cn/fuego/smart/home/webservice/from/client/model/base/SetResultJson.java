package cn.fuego.smart.home.webservice.from.client.model.base;


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
	private String errorCode;
	private String errorMsg;
	private String obj;
	public String getErrorCode()
	{
		return errorCode;
	}
	public void setErrorCode(String errorCode)
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
	
	

}
