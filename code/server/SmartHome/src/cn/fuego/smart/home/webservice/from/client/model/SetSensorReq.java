package cn.fuego.smart.home.webservice.from.client.model;

import cn.fuego.smart.home.webservice.from.client.model.base.BaseJsonReq;
import cn.fuego.smart.home.webservice.from.client.model.base.SensorJson;


/**
 * 
* @ClassName: SetSensorReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:45 
*
 */
public class SetSensorReq extends BaseJsonReq
{
	private String command;
	private SensorJson sensor;
}
