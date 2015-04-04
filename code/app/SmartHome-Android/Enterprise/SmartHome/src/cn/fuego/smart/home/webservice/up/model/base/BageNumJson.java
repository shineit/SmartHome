/**   
* @Title: BageNumJson.java 
* @Package cn.fuego.smart.home.webservice.up.model.base 
* @Description: TODO
* @author Aether
* @date 2015-4-2 下午3:40:25 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;

/** 
 * @ClassName: BageNumJson 
 * @Description: TODO
 * @author Aether
 * @date 2015-4-2 下午3:40:25 
 *  
 */
public class BageNumJson implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long num;
	private int companyID;

	public long getNum()
	{
		return num;
	}
	public void setNum(long num)
	{
		this.num = num;
	}
	public int getCompanyID()
	{
		return companyID;
	}
	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}
	@Override
	public String toString()
	{
		return "BageNumJson [num=" + num + ", companyID=" + companyID + "]";
	}
	
	
}
