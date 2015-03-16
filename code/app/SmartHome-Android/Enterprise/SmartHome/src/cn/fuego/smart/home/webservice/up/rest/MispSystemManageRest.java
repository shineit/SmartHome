package cn.fuego.smart.home.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.GetClientVersionReq;
import cn.fuego.smart.home.webservice.up.model.GetClientVersionRsp;


@Path("/sys")
@Produces("application/json")  
@Consumes("application/json")  
public interface MispSystemManageRest
{
	
	@POST
	@Path("/ClientVersionManage/GetLatestVersion")
	GetClientVersionRsp getAppVersion(GetClientVersionReq req);
}
