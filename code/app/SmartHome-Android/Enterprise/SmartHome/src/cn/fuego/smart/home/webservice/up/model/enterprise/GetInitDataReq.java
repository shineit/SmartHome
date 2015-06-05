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
	private int user_id;

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

	@Override
	public String toString()
	{
		return "GetInitDataReq [user_id=" + user_id + ", app_id=" + app_id
				+ ", token=" + token + "]";
	}


	

}
