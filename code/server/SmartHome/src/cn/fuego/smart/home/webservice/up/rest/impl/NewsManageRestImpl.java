/**   
* @Title: NewsManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:22:37 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.web.model.page.PageModel;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.service.NewsManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AppLoginCache;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetNewsByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.up.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.rest.NewsManageRest;

 /** 
 * @ClassName: NewsManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:22:37 
 *  
 */
public class NewsManageRestImpl implements NewsManageRest
{
	private Log log = LogFactory.getLog(NewsManageRestImpl.class);

	private NewsManageService newsService = ServiceContext.getInstance().getNewsManageService();
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.NewsManageService#getNewsList(cn.fuego.smart.home.webservice.from.client.model.GetNewsListReq)
	 */
	@Override
	public GetNewsListRsp getNewsList(GetNewsListReq req)
	{
		GetNewsListRsp rsp = new GetNewsListRsp();
		PageModel page = new PageModel();
		
		if(null != req.getPage())
		{
			page.setPageSize(req.getPage().getPageSize());
			page.setCurrentPage(req.getPage().getCurrentPage());
		}
		List<QueryCondition> condtionList = new ArrayList<QueryCondition>();
		condtionList.add(new QueryCondition(ConditionTypeEnum.DESC_ORDER,"date"));
		int userID=AppLoginCache.getUserID(req.getToken());
		SystemUser user=MISPServiceContext.getInstance().getUserService().get(userID);
		if(user!=null)
		{
			condtionList.add(new QueryCondition(ConditionTypeEnum.LIKE_LEFT, "org_id", user.getOrg_id()));
		}
		List<News> newsList = newsService.getDataSource(condtionList).getCurrentPageData(page.getStartNum(),page.getPageSize());
		for(News e : newsList)
		{
			NewsJson newsJson = ModelConvert.newsToJson(e);
			rsp.getNewsList().add(newsJson);
		}
		
 		return rsp;
	}
	@Override
	public GetNewsByIDRsp getNews(GetNewsByIDReq req)
	{
		GetNewsByIDRsp rsp = new GetNewsByIDRsp();
		
		try
		{
			News news = newsService.get(String.valueOf(req.getNewsID()));
		    NewsJson json = ModelConvert.newsToJson(news);
		    rsp.setNews(json);
		}
		catch(MISPException e)
		{
			log.error("get alarm error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get alarm error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}

}
