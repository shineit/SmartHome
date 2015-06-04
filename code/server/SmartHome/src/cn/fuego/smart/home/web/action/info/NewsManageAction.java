/**   
* @Title: NewsManageAction.java 
* @Package cn.fuego.smart.home.web.action.info 
* @Description: TODO
* @author Aether
* @date 2014-11-3 下午5:43:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.info;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.service.NewsManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.NewsFilterModel;

/** 
 * @ClassName: NewsManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-3 下午5:43:58 
 *  
 */
public class NewsManageAction extends DWZTableAction<News>
{

	/**
	 * 
	 */
	private Log log = LogFactory.getLog(NewsManageAction.class);
	
	
	private static final long serialVersionUID = 1L;
	private NewsManageService newsService = ServiceContext.getInstance().getNewsManageService();
     private NewsFilterModel newsFilter = new NewsFilterModel();
     
    
    
    @Override
    public List<QueryCondition> getFilterCondition()
    {
		List<QueryCondition> conditionList = super.getFilterCondition();
		if(null == conditionList)
		{
			conditionList = new ArrayList<QueryCondition>();
		}
 
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "org_id",this.getLoginUser().getOrg_id()));

    	return conditionList;
    	
    }
    
    @Override
    public String show()
    {
    	this.getObj().setAuthor(this.getLoginUser().getUserName());
    	return super.show();
    }
    
  
	public NewsFilterModel getNewsFilter()
	{
		return newsFilter;
	}

	public void setNewsFilter(NewsFilterModel newsFilter)
	{
		this.newsFilter = newsFilter;
	}
 
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<News> getService()
	{
		// TODO Auto-generated method stub
		return this.newsService;
	}
	
	@Override
	public void CreateCallFoward(News obj)
	{
		// TODO Auto-generated method stub
		super.CreateCallFoward(obj);
		obj.setOrg_id(this.getLoginUser().getOrg_id());
	}

}
