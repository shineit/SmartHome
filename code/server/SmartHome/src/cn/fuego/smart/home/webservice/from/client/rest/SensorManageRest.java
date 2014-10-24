/**
 *  ������: RestEasy���Գ���
 */
package cn.fuego.smart.home.webservice.from.client.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
 
public interface SensorManageRest
{

	@POST
	@Path("/hello")

	public String getHello();

	@POST
	@Path("/list")
	@Produces("application/json")  
	public GetSensorListRsp getSensorList();
	
	@POST
	@Path("/set")
	public SetSensorRsp setSensor(SetSensorReq sensorListReq);
 
}