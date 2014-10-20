/**
 *  ������: RestEasy���Գ���
 */
package cn.fuego.smart.home.webservice.from.client.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ProduceMime;

import cn.fuego.smart.home.webservice.from.client.model.GetSensorListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorReq;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorRsp;

/**
 * 
* @ClassName: SensorManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:53:57 
*
 */
@Path("/sensor")
@ProduceMime({ "application/json" })
public interface SensorManageService {

	@GET
	@Path("/hello")

	public String getWild();

	@POST
	@Path("/list")
	public GetSensorListRsp getSensorList(GetSensorListReq sensorListReq);
	
	@POST
	@Path("/set")
	public SetSensorRsp setSensor(SetSensorReq sensorListReq);
 
}