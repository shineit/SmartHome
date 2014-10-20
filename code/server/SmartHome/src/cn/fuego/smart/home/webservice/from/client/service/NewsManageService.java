package cn.fuego.smart.home.webservice.from.client.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ProduceMime;

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
@ProduceMime({ "application/json" })
public interface NewsManageService
{
	@POST
	@Path("/list")
	public GetNewsListRsp getNewsList(GetNewsListReq newsListReq);

}
