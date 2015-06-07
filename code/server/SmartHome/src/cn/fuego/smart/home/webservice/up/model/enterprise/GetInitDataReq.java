/**   
* @Title: GetInitDataReq.java 
* @Package cn.fuego.smart.home.webservice.up.model.enterprise 
* @Description: TODO
* @author Aether
* @date 2015-6-5 下午8:32:39 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model.enterprise;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;

/** 
 * @ClassName: GetInitDataReq 
 * @Description: TODO
 * @author Aether
 * @date 2015-6-5 下午8:32:39 
 *  
 */
public class GetInitDataReq extends MispBaseReqJson
{
	private int userID;

	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	@Override
	public String toString()
	{
		return "GetInitDataReq [userID=" + userID + "]";
	}



}
