package cn.fuego.smart.home.webservice.from.client.model;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.UserMarkJson;


/**
 * 
* @ClassName: SetUserMarkRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:08 
*
 */
public class SetUserMarkRsp extends BaseJsonRsp
{
	private UserMarkJson userMark;

	public UserMarkJson getUserMark()
	{
		return userMark;
	}

	public void setUserMark(UserMarkJson userMark)
	{
		this.userMark = userMark;
	}

 
	
}
