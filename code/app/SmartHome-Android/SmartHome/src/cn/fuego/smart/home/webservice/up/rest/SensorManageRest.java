/**
 *  ������: RestEasy���Գ���
 */
package cn.fuego.smart.home.webservice.up.rest;
 

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.BatchSetSensorReq;
import cn.fuego.smart.home.webservice.up.model.BatchSetSensorRsp;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorListReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.up.model.SetSensorReq;
import cn.fuego.smart.home.webservice.up.model.SetSensorRsp;

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
@Consumes("application/json")  
public interface SensorManageRest
{

	@GET
	@Path("/hello")

	public String getHello();

	@POST
	@Path("/list")
	public GetSensorListRsp getSensorList(GetSensorListReq req);
	
	@POST
	@Path("/id")
	public GetSensorByIDRsp getSensor(GetSensorByIDReq req);
	
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
	public GetHistoryAlarmListRsp getAlarmList(GetHistoryAlarmListReq req);
	
	@POST
	@Path("/alarm/id")
	public GetAlarmByIDRsp getAlarm(GetAlarmByIDReq req);
 
}