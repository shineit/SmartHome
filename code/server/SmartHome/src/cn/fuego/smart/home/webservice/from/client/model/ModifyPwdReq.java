/**   
* @Title: ModifyPwdReq.java 
* @Package cn.fuego.smart.home.webservice.from.client.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-4 下午11:07:00 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.model;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;

 /** 
 * @ClassName: ModifyPwdReq 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-4 下午11:07:00 
 *  
 */
public class ModifyPwdReq extends BaseJsonReq
{
	 
	private String userName;
	private String oldPwd;
	private String newPwd;
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getOldPwd()
	{
		return oldPwd;
	}
	public void setOldPwd(String oldPwd)
	{
		this.oldPwd = oldPwd;
	}
	public String getNewPwd()
	{
		return newPwd;
	}
	public void setNewPwd(String newPwd)
	{
		this.newPwd = newPwd;
	}
	@Override
	public String toString()
	{
		return "ModifyPwdReq [userName=" + userName + ", oldPwd=" + oldPwd
				+ ", newPwd=" + newPwd + "]";
	}
	
	

}
