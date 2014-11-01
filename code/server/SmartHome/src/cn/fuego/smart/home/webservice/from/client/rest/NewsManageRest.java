package cn.fuego.smart.home.webservice.from.client.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.from.client.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetNewsListRsp;

/**
 * 
* @ClassName: NewsManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:03:11 
*
 */
@Path("/news")
@Produces({ "application/json" })
public interface NewsManageRest
{
	@POST	
	@Path("/list")
	public GetNewsListRsp getNewsList(GetNewsListReq req);

}
