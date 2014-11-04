package cn.fuego.smart.home.webservice.from.client.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.from.client.model.GetUserMarkListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetUserMarkListRsp;
import cn.fuego.smart.home.webservice.from.client.model.LoginReq;
import cn.fuego.smart.home.webservice.from.client.model.LoginRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetUserMarkReq;
import cn.fuego.smart.home.webservice.from.client.model.SetUserMarkRsp;


/**
 * 
* @ClassName: UserManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:53:45 
*
 */

@Path("/user")
@Produces("application/json")  
public interface UserManageRest
{
	@POST
	@Path("/login")
	LoginRsp login(LoginReq req);
	
	@POST
	@Path("/logout")
	LoginRsp logout(LoginReq req);
	
	@POST
	@Path("/mark/list")
	GetUserMarkListRsp getUserMarkList(GetUserMarkListReq req);
	
	@POST
	@Path("/mark/add")
	SetUserMarkRsp addUserMark(SetUserMarkReq req);
	
	@POST
	@Path("/mark/delete")
	SetUserMarkRsp deleteUserMark(SetUserMarkReq req);
}
