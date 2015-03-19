package cn.fuego.smart.home.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.smart.home.webservice.up.model.GetAdListReq;
import cn.fuego.smart.home.webservice.up.model.GetAdListRsp;
import cn.fuego.smart.home.webservice.up.model.GetProductListReq;
import cn.fuego.smart.home.webservice.up.model.GetProductListRsp;

/**
 * 
* @ClassName: NewsManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:03:11 
*
 */
@Path("/")
@Produces("application/json")
@Consumes("application/json")  
public interface MallManageRest
{
	@POST	
	@Path("/product/list")
	public GetProductListRsp getProductList(GetProductListReq req);
	@POST	
	@Path("/ad/list")
	public GetAdListRsp getAdList(GetAdListReq req);

 
 
 
}
