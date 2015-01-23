/**   
* @Title: TokenResultRsp.java 
* @Package com.hikvision 
* @Description: TODO
* @author Aether
* @date 2015-1-13 下午2:04:28 
* @version V1.0   
*/ 
package com.hikvision;

/** 
 * @ClassName: TokenResultRsp 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-13 下午2:04:28 
 *  
 */
public class CameraResultRsp
{
	private CameraResultModel result;

	public CameraResultModel getResult()
	{
		return result;
	}

	public void setResult(CameraResultModel result)
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "CameraResultRsp [result=" + result + "]";
	}


	
}
