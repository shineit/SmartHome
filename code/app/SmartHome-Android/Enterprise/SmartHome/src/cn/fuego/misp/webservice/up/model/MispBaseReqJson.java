package cn.fuego.misp.webservice.up.model;

import cn.fuego.misp.service.MemoryCache;
 
/**
 * 
* @ClassName: BaseJsonReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:41 
*
 */
public class MispBaseReqJson
{
	protected String app_id = "1";
	protected String token = MemoryCache.getToken();

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	@Override
	public String toString()
	{
		return "MispBaseReqJson [app_id=" + app_id + ", token=" + token + "]";
	}

 

}
