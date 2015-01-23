/**   
* @Title: GetTokenResultModel.java 
* @Package com.hikvision 
* @Description: TODO
* @author Aether
* @date 2015-1-12 下午7:30:52 
* @version V1.0   
*/ 
package com.hikvision;

/** 
 * @ClassName: GetTokenResultModel 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-12 下午7:30:52 
 *  
 */
public class CameraResultModel
{
    private String msg;
    private String code;
    private CameraInfoModel data;
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public CameraInfoModel getData()
	{
		return data;
	}
	public void setData(CameraInfoModel data)
	{
		this.data = data;
	}
	@Override
	public String toString()
	{
		return "CameraResultModel [msg=" + msg + ", code=" + code + ", data="
				+ data + "]";
	}

    
}
