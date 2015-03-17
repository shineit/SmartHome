package cn.fuego.smart.home.webservice.up.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDRsp;



/** 
* @ClassName: CheckManageRest 
* @Description: TODO
* @author Aether
* @date 2015-3-17 上午9:36:31 
*  
*/
@Path("/user")
@Produces("application/json")  
@Consumes("application/json")  
public interface CheckManageRest
{
	
	@POST
	@Path("/item/id")
	GetCheckItemByIDRsp getItemByID(GetCheckItemByIDReq req);

	
}
