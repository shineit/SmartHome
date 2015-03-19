/**   
* @Title: ModifyPwdReq.java 
* @Package cn.fuego.smart.home.webservice.from.client.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-4 下午11:07:00 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

 /** 
 * @ClassName: ModifyPwdReq 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-4 下午11:07:00 
 *  
 */
public class ModifyPwdReq extends MispBaseReqJson
{
	 
	private String userName;
	private String oldPwd;
	private String pwdNew;
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
 
	public String getPwdNew()
	{
		return pwdNew;
	}
	public void setPwdNew(String pwdNew)
	{
		this.pwdNew = pwdNew;
	}
	@Override
	public String toString()
	{
		return "ModifyPwdReq [userName=" + userName + ", oldPwd=" + oldPwd
				+ ", pwdNew=" + pwdNew + "]";
	}
 
	

}
