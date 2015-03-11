/**   
* @Title: IndexAction.java 
* @Package cn.fuego.misp.web.action.login 
* @Description: TODO
* @author Aether
* @date 2014-11-6 下午10:35:25 
* @version V1.0   
*/ 
package cn.fuego.misp.web.action.login;

import java.util.List;

import cn.fuego.misp.web.action.basic.MISPAction;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.service.NewsManageService;
import cn.fuego.smart.home.service.ServiceContext;

/** 
 * @ClassName: IndexAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-6 下午10:35:25 
 *  
 */
public class IndexAction extends MISPAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //首页获取内容
    private List<News> newsContent ;
    private NewsManageService newsService = ServiceContext.getInstance().getNewsManageService();

    
	public String execute()
	{
		
		newsContent = newsService.getIndexNews(this.getLoginUser().getUserName());
		//alarmTable.setPage(this.getPage());
    	//alarmTable.setDataSource(alarmService.getDataSource(alarmFilter.getConidtionList()));
		return SUCCESS;
	}



	public List<News> getNewsContent()
	{
		return newsContent;
	}


	public void setNewsContent(List<News> newsContent)
	{
		this.newsContent = newsContent;
	}


	public NewsManageService getNewsService()
	{
		return newsService;
	}


	public void setNewsService(NewsManageService newsService)
	{
		this.newsService = newsService;
	}



	
}
