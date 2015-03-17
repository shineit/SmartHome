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
import cn.fuego.smart.home.domain.Knowledge;
import cn.fuego.smart.home.domain.Product;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetKnowledgeListReq;
import cn.fuego.smart.home.webservice.up.model.GetKnowledgeListRsp;
import cn.fuego.smart.home.webservice.up.model.GetProductListReq;
import cn.fuego.smart.home.webservice.up.model.GetProductListRsp;
import cn.fuego.smart.home.webservice.up.model.base.KnowledgeJson;
import cn.fuego.smart.home.webservice.up.model.base.ProductJson;
import cn.fuego.smart.home.webservice.up.rest.KnowledgeManageRest;
import cn.fuego.smart.home.webservice.up.rest.ProductManageRest;

 /** 
 * @ClassName: MallManageRestImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-17 上午10:22:22 
 *  
 */
public class KnowledgeManageRestImpl implements KnowledgeManageRest
{

	private FuegoLog log = FuegoLog.getLog(getClass());
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.up.rest.MallManageRest#getProductList(cn.fuego.smart.home.webservice.up.model.GetNewsListReq)
	 */
	@Override
	public GetKnowledgeListRsp getKnowledgeList(GetKnowledgeListReq req)
	{
		// TODO Auto-generated method stub
		GetKnowledgeListRsp rsp = new GetKnowledgeListRsp();
		
		try
		{
 			PageModel page = new PageModel();
			
			if(null != req.getPage())
			{
				page.setPageSize(req.getPage().getPageSize());
				page.setCurrentPage(req.getPage().getCurrentPage());
			}

			List<Knowledge> knowledgeList = ServiceContext.getInstance().getKnowledgeManageService().getDataSource().getCurrentPageData(page.getStartNum(), page.getPageSize());
			for(Knowledge knowledge : knowledgeList)
			{
				KnowledgeJson json = ModelConvert.knowledgeToJson(knowledge);
				rsp.getKnowledgeList().add(json);
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
