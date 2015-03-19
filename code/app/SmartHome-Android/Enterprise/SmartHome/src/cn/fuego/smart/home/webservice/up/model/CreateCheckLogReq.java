/**   
* @Title: CreateCheckLog.java 
* @Package cn.fuego.smart.home.webservice.up.model 
* @Description: TODO
* @author Aether
* @date 2015-3-17 下午10:19:31 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.webservice.up.model.MispBaseReqJson;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;

/** 
 * @ClassName: CreateCheckLog 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-17 下午10:19:31 
 *  
 */
public class CreateCheckLogReq extends MispBaseReqJson
{
	
	private List<CheckLogJson> checkLogList = new ArrayList<CheckLogJson>();

	public List<CheckLogJson> getCheckLogList()
	{
		return checkLogList;
	}

	public void setCheckLogList(List<CheckLogJson> checkLogList)
	{
		this.checkLogList = checkLogList;
	}

	@Override
	public String toString()
	{
		return "CreateCheckLogReq [checkLogList=" + checkLogList + ", token="
				+ token + "]";
	}
	

}
