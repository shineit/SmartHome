/**   
* @Title: ConcentManageRest.java 
* @Package cn.fuego.smart.home.webservice.up.rest 
* @Description: TODO
* @author Aether
* @date 2015-1-20 上午9:24:57 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.GetConcentratorListReq;
import cn.fuego.smart.home.webservice.up.model.GetConcentratorListRsp;
import cn.fuego.smart.home.webservice.up.model.SetConcentratorReq;
import cn.fuego.smart.home.webservice.up.model.SetConcentratorRsp;

/** 
 * @ClassName: ConcentManageRest 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-20 上午9:24:57 
 *  
 */
@Path("/concentrator")
@Produces("application/json")  
@Consumes("application/json")  
public interface ConcentManageRest
{
	
	@POST
	@Path("/list")
	public GetConcentratorListRsp getConcentList(GetConcentratorListReq req);
	
	@POST
	@Path("/modify")
    SetConcentratorRsp modifyConcent(SetConcentratorReq req);
	


}
