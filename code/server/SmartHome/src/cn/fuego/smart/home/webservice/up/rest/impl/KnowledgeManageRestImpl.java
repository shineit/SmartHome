/**   
* @Title: MallManageRestImpl.java 
* @Package cn.fuego.smart.home.webservice.up.rest.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-17 上午10:22:22 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.web.model.page.PageModel;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.KnowledgeTypeEnum;
import cn.fuego.smart.home.domain.Knowledge;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetCommonSenseListReq;
import cn.fuego.smart.home.webservice.up.model.GetCommonSenseListRsp;
import cn.fuego.smart.home.webservice.up.model.GetHelpListReq;
import cn.fuego.smart.home.webservice.up.model.GetHelpListRsp;
import cn.fuego.smart.home.webservice.up.model.GetKnowledgeListReq;
import cn.fuego.smart.home.webservice.up.model.GetKnowledgeListRsp;
import cn.fuego.smart.home.webservice.up.model.base.KnowledgeJson;
import cn.fuego.smart.home.webservice.up.rest.KnowledgeManageRest;

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
		catch(MISPException e)
		{
			log.error("get KnowledgeList error",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get KnowledgeList error",e);
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}
	@Override
	public GetCommonSenseListRsp getCommonSenseList(GetCommonSenseListReq req)
	{
		GetCommonSenseListRsp rsp = new GetCommonSenseListRsp();
		
		try
		{
 			PageModel page = new PageModel();
			
			if(null != req.getPage())
			{
				page.setPageSize(req.getPage().getPageSize());
				page.setCurrentPage(req.getPage().getCurrentPage());
			}


			List<QueryCondition> conditionList= new ArrayList<QueryCondition>();
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "knowledgeType", KnowledgeTypeEnum.COMMON_SENSE.getIntValue()));
			List<Knowledge> knowledgeList = ServiceContext.getInstance().getKnowledgeManageService().getDataSource(conditionList)
					.getCurrentPageData(page.getStartNum(), page.getPageSize());
			for(Knowledge knowledge : knowledgeList)
			{
				KnowledgeJson json = ModelConvert.knowledgeToJson(knowledge);
				rsp.getKnowledgeList().add(json);
			}

 		}
		catch(MISPException e)
		{
			log.error("get CommonSenseList error",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get CommonSenseList error",e);
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}
		
		return rsp;
	}
	@Override
	public GetHelpListRsp getHelpList(GetHelpListReq req)
	{
		GetHelpListRsp rsp = new GetHelpListRsp();
		
		try
		{
 			PageModel page = new PageModel();
			
			if(null != req.getPage())
			{
				page.setPageSize(req.getPage().getPageSize());
				page.setCurrentPage(req.getPage().getCurrentPage());
			}


			List<QueryCondition> conditionList= new ArrayList<QueryCondition>();
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "knowledgeType", KnowledgeTypeEnum.HELP.getIntValue()));
			List<Knowledge> knowledgeList = ServiceContext.getInstance().getKnowledgeManageService().getDataSource(conditionList)
					.getCurrentPageData(page.getStartNum(), page.getPageSize());
			for(Knowledge knowledge : knowledgeList)
			{
				KnowledgeJson json = ModelConvert.knowledgeToJson(knowledge);
				rsp.getKnowledgeList().add(json);
			}

 		}
		catch(MISPException e)
		{
			log.error("get HelpList error",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get HelpList error",e);
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}
		
		return rsp;
	}

}
