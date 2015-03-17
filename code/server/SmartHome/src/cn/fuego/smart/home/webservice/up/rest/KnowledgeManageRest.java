package cn.fuego.smart.home.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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

 
}
