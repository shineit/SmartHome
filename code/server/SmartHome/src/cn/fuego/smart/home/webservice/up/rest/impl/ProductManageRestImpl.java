/**   
* @Title: MallManageRestImpl.java 
* @Package cn.fuego.smart.home.webservice.up.rest.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-17 上午10:22:22 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.List;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.web.model.page.PageModel;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.Product;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetProductListReq;
import cn.fuego.smart.home.webservice.up.model.GetProductListRsp;
import cn.fuego.smart.home.webservice.up.model.base.ProductJson;
import cn.fuego.smart.home.webservice.up.rest.ProductManageRest;

 /** 
 * @ClassName: MallManageRestImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-17 上午10:22:22 
 *  
 */
public class ProductManageRestImpl implements ProductManageRest
{

	private FuegoLog log = FuegoLog.getLog(getClass());
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.up.rest.MallManageRest#getProductList(cn.fuego.smart.home.webservice.up.model.GetNewsListReq)
	 */
	@Override
	public GetProductListRsp getProductList(GetProductListReq req)
	{
		// TODO Auto-generated method stub
		GetProductListRsp rsp = new GetProductListRsp();
		
		try
		{
 			PageModel page = new PageModel();
			
			if(null != req.getPage())
			{
				page.setPageSize(req.getPage().getPageSize());
				page.setCurrentPage(req.getPage().getCurrentPage());
			}

			List<Product> productList = ServiceContext.getInstance().getProductManageService().getDataSource().getCurrentPageData(page.getStartNum(), page.getPageSize());
			for(Product product : productList)
			{
				ProductJson json = ModelConvert.productToJson(product);
				rsp.getProductList().add(json);
			}

 		}
		catch(Exception e)
		{
			log.error("get sensor list error",e);
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}

}
