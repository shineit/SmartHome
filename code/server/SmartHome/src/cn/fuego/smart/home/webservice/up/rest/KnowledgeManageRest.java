package cn.fuego.smart.home.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.GetCommonSenseListReq;
import cn.fuego.smart.home.webservice.up.model.GetCommonSenseListRsp;
import cn.fuego.smart.home.webservice.up.model.GetHelpListReq;
import cn.fuego.smart.home.webservice.up.model.GetHelpListRsp;
import cn.fuego.smart.home.webservice.up.model.GetKnowledgeListReq;
import cn.fuego.smart.home.webservice.up.model.GetKnowledgeListRsp;

/**
 * 
* @ClassName: NewsManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:03:11 
*
 */
@Path("/knowledge")
@Produces("application/json")
@Consumes("application/json")  
public interface KnowledgeManageRest
{
	@POST	
	@Path("/list")
	public GetKnowledgeListRsp getKnowledgeList(GetKnowledgeListReq req);

	@POST	
	@Path("/commonsense/list")
	public GetCommonSenseListRsp getCommonSenseList(GetCommonSenseListReq req);
	
	@POST	
	@Path("/help/list")
	public GetHelpListRsp getHelpList(GetHelpListReq req);
 
}
