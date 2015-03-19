/**
 *  ������: RestEasy���Գ���
 */
package cn.fuego.smart.home.webservice.up.rest;
 

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorReq;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorRsp;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDRsp;
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
	public BatchOperateSensorRsp enable(BatchOperateSensorReq req);
	
	@POST
	@Path("/batch/disable")
	public BatchOperateSensorRsp disable(BatchOperateSensorReq req);
	
	@POST
	@Path("/alarm")
	public GetHistoryAlarmListRsp getAlarmList(GetHistoryAlarmListReq req);
	
	@POST
	@Path("/alarm/id")
	public GetAlarmByIDRsp getAlarm(GetAlarmByIDReq req);
	
	@POST
	@Path("/alarm/clear/id")
	public ClearAlarmByIDRsp clearAlarm(ClearAlarmByIDReq req);
	
	@POST
	@Path("/alarm/clear/list")
	public ClearAlarmListRsp clearAlarm(ClearAlarmListReq req);
	
	@POST
	@Path("/fireAlarm/get")
	public GetFireAlarmByIDRsp getFireAlarm(GetFireAlarmByIDReq req);
	
 
 
}