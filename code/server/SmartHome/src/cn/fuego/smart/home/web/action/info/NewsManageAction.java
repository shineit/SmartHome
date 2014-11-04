/**   
* @Title: NewsManageAction.java 
* @Package cn.fuego.smart.home.web.action.info 
* @Description: TODO
* @author Aether
* @date 2014-11-3 下午5:43:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.info;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.dao.file.MispMessageReader;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.page.TableDataModel;
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
public class NewsManageAction extends DWZTableAction
{

	/**
	 * 
	 */
	private Log log = LogFactory.getLog(NewsManageAction.class);
	
	
	private static final long serialVersionUID = 1L;
	private NewsManageService newsService = ServiceContext.getInstance().getNewsManageService();
	private TableDataModel<News> newsTable = new TableDataModel<News>();
    private NewsFilterModel newsFilter = new NewsFilterModel();
    private News news ;
    @Override
    public String execute()
    {
      	
    	newsTable.setPage(this.getPage());
    	newsTable.setDataSource(newsService.getNewsDataSource(newsFilter.getConidtionList()));
    	return SUCCESS;
    	
    }
    
	@Override
	public String create()
	{
		newsService.saveNewsInfo(news);
		this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
		return MISP_DONE_PAGE;
	}

	@Override
	public String delete()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteList()
	{
		newsService.deleteNewsList(Arrays.asList(this.getSelectedIDList()));
		return  MISP_DONE_PAGE;
	}

	@Override
	public String modify()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String show()
	{
		news = new News();
		news.setAuthor(this.getLoginUser().getUserName());
		return EDIT_INFO;
	}

	public NewsManageService getNewsService()
	{
		return newsService;
	}

	public void setNewsService(NewsManageService newsService)
	{
		this.newsService = newsService;
	}

	public TableDataModel<News> getNewsTable()
	{
		return newsTable;
	}

	public void setNewsTable(TableDataModel<News> newsTable)
	{
		this.newsTable = newsTable;
	}

	public NewsFilterModel getNewsFilter()
	{
		return newsFilter;
	}

	public void setNewsFilter(NewsFilterModel newsFilter)
	{
		this.newsFilter = newsFilter;
	}

	public News getNews()
	{
		return news;
	}

	public void setNews(News news)
	{
		this.news = news;
	}

}
