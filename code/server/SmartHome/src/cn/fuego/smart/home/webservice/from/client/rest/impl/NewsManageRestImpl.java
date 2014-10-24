/**   
* @Title: NewsManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:22:37 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.rest.impl;

import java.util.List;

import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.service.NewsManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.from.client.model.GetNewsListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetNewsListRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.NewsJson;
import cn.fuego.smart.home.webservice.from.client.rest.NewsManageRest;

 /** 
 * @ClassName: NewsManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:22:37 
 *  
 */
public class NewsManageRestImpl implements NewsManageRest
{

	private NewsManageService newsService = ServiceContext.getInstance().getNewsManageService();
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.NewsManageService#getNewsList(cn.fuego.smart.home.webservice.from.client.model.GetNewsListReq)
	 */
	@Override
	public GetNewsListRsp getNewsList(GetNewsListReq req)
	{
		GetNewsListRsp rsp = new GetNewsListRsp();
		List<News> newsList = newsService.getNewsDataSource().getAllPageData();
		for(News e : newsList)
		{
			NewsJson newsJson = new NewsJson();
			newsJson.loadWithNews(e);
			rsp.getNewsList().add(newsJson);
		}
		
 		return rsp;
	}

}
