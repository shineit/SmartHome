/**   
* @Title: NewsManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午2:46:32 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.service.NewsManageService;
import cn.fuego.smart.home.web.model.NewsFilterModel;

 /** 
 * @ClassName: NewsManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午2:46:32 
 *  
 */
public class NewsManageServiceImpl implements NewsManageService
{

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.NewsManageService#getNewsDataSource()
	 */
	@Override
	public AbstractDataSource<News> getNewsDataSource(List<QueryCondition> conditionList)
	{
		AbstractDataSource<News> datasource = new DataBaseSourceImpl<News>(News.class,conditionList);
	 
		return datasource;
	}

	@Override
	public void saveNewsInfo(News news)
	{
		news.setDate(DateUtil.getCurrentDateTime());
		DaoContext.getInstance().getNewsDao().update(news);
		
	}

	@Override
	public void deleteNewsList(List<String> newsIDList)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, "newsID", newsIDList);	
		DaoContext.getInstance().getNewsDao().delete(condition);
		
	}


}
