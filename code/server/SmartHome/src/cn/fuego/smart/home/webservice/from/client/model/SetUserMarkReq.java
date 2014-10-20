package cn.fuego.smart.home.webservice.from.client.model;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.UserMarkJson;


/**
 * 
* @ClassName: SetUserMarkReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:00:03 
*
 */
public class SetUserMarkReq extends BaseJsonReq
{
	private String command;
	private UserMarkJson userMark;
}
