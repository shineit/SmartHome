package cn.fuego.smart.home.webservice.from.client.model;

import java.util.List;

import cn.fuego.smart.home.webservice.from.client.model.base.AttributeJson;
import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.PageJson;


/**
 * 
* @ClassName: GetHistoryAlarmListReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:58:33 
*
 */
public class GetHistoryAlarmListReq  extends BaseJsonReq
{
	private PageJson page;
	private int userID;
	private List<AttributeJson> filterList;

}
