/**   
* @Title: NewsManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午2:46:32 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.service.NewsManageService;
import cn.fuego.smart.home.webservice.down.service.WebServiceContext;

 /** 
 * @ClassName: NewsManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午2:46:32 
 *  
 */
public class NewsManageServiceImpl extends MispCommonServiceImpl<News> implements NewsManageService
{


	@Override
	public void validator(News obj)
	{
		// TODO Auto-generated method stub
		super.validator(obj);
	}

	@Override
	public void create(int userID, News obj)
	{
		// TODO Auto-generated method stub
		super.create(userID, obj);
		List<News> objList = new ArrayList<News>();
		objList.add(obj);
		WebServiceContext.getInstance().getPushService().pushNews(objList);
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getIndexNews(String userName)
	{
		List<News> newsList = new ArrayList<News>();
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		Date today=DateUtil.getCurrentDateTime();
		conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"date",DateUtil.DateToString(today)));
		conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER,"date",DateUtil.DateToString(DateUtil.dayCalculate(today, 1))));
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"author",userName));
		
		newsList = (List<News>) DaoContext.getInstance().getNewsDao().getAll(conditionList);
		return newsList;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#GetPrimaryName()
	 */
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return News.PRI_KEY;
	}


}
