package cn.fuego.smart.home.webservice.up.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.CreateCheckLogReq;
import cn.fuego.smart.home.webservice.up.model.CreateCheckLogRsp;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetCheckLogByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckLogByIDRsp;



/** 
* @ClassName: CheckManageRest 
* @Description: TODO
* @author Aether
* @date 2015-3-17 上午9:36:31 
*  
*/
@Path("/check")
@Produces("application/json")  
@Consumes("application/json")  
public interface CheckManageRest
{
	
	@POST
	@Path("/item/id")
	GetCheckItemByIDRsp getItemByID(GetCheckItemByIDReq req);
	
	@POST
	@Path("/checkLog/create")
	CreateCheckLogRsp createCheckLog(CreateCheckLogReq req);
	
	@POST
	@Path("/checkLog/get")
	GetCheckLogByIDRsp getCheckLogByID(GetCheckLogByIDReq req);

	
}
