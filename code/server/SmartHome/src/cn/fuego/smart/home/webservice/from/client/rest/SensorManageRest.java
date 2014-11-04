/**
 *  ������: RestEasy���Գ���
 */
package cn.fuego.smart.home.webservice.from.client.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.from.client.model.BatchSetSensorReq;
import cn.fuego.smart.home.webservice.from.client.model.BatchSetSensorRsp;
import cn.fuego.smart.home.webservice.from.client.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetHistoryAlarmListRsp;
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
@Produces("application/json")  
public interface SensorManageRest
{

	@GET
	@Path("/hello")

	public String getHello();

	@POST
	@Path("/list")
	public GetSensorListRsp getSensorList();
	
	@POST
	@Path("/set")
	public SetSensorRsp setSensor(SetSensorReq req);
	
	@POST
	@Path("/batch/enable")
	public BatchSetSensorRsp enable(BatchSetSensorReq req);
	
	@POST
	@Path("/batch/disable")
	public BatchSetSensorRsp disable(BatchSetSensorReq req);
	
	@POST
	@Path("/alarm")
	public GetHistoryAlarmListRsp setSensor(GetHistoryAlarmListReq req);
	
 
}