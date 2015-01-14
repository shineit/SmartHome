package cn.fuego.smart.home.webservice.up.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.GetCaTokenByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCaTokenByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetCustomerByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCustomerByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListReq;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListRsp;
import cn.fuego.smart.home.webservice.up.model.LoginReq;
import cn.fuego.smart.home.webservice.up.model.LoginRsp;
import cn.fuego.smart.home.webservice.up.model.ModifyPwdReq;
import cn.fuego.smart.home.webservice.up.model.ModifyPwdRsp;
import cn.fuego.smart.home.webservice.up.model.SetCustomerReq;
import cn.fuego.smart.home.webservice.up.model.SetCustomerRsp;
import cn.fuego.smart.home.webservice.up.model.SetUserMarkReq;
import cn.fuego.smart.home.webservice.up.model.SetUserMarkRsp;


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
@Consumes("application/json")  
public interface UserManageRest
{
	@POST
	@Path("/login")
	LoginRsp login(LoginReq req);
	
	@POST
	@Path("/logout")
	LoginRsp logout(LoginReq req);
	
	
	@POST
	@Path("/password/modify")
    ModifyPwdRsp modifyPassword(ModifyPwdReq req);
	
	@POST
	@Path("/mark/list")
	GetUserMarkListRsp getUserMarkList(GetUserMarkListReq req);
	
	@POST
	@Path("/mark/add")
	SetUserMarkRsp addUserMark(SetUserMarkReq req);
	
	@POST
	@Path("/mark/delete")
	SetUserMarkRsp deleteUserMark(SetUserMarkReq req);
	
	@POST
	@Path("/customer/get")
	GetCustomerByIDRsp getCustomer(GetCustomerByIDReq req);

	@POST
	@Path("/customer/modify")
	SetCustomerRsp modifyCustomer(SetCustomerReq req);
	
	@POST
	@Path("/caToken/get")
	GetCaTokenByIDRsp getCaToken(GetCaTokenByIDReq req);
}
