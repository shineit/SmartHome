/**   
* @Title: IndexAction.java 
* @Package cn.fuego.misp.web.action.login 
* @Description: TODO
* @author Aether
* @date 2014-11-6 下午10:35:25 
* @version V1.0   
*/ 
package cn.fuego.misp.web.action.login;

import java.util.Arrays;
import java.util.List;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.service.AlarmManageService;
import cn.fuego.smart.home.service.NewsManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.AlarmFilterModel;
import cn.fuego.smart.home.web.model.NewsFilterModel;

/** 
 * @ClassName: IndexAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-6 下午10:35:25 
 *  
 */
public class IndexAction extends DWZTableAction<Alarm>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //首页获取内容
    private List<News> newsContent ;
    private NewsManageService newsService = ServiceContext.getInstance().getNewsManageService();

    private AlarmManageService alarmService = ServiceContext.getInstance().getAlarmManageService();
	private TableDataModel<Alarm> alarmTable = new TableDataModel<Alarm>();
    private AlarmFilterModel alarmFilter = new AlarmFilterModel();
    
	public String execute()
	{
		
		newsContent = newsService.getIndexNews(this.getLoginUser().getUserName());
		alarmTable.setPage(this.getPage());
    	alarmTable.setDataSource(alarmService.getDataSource(alarmFilter.getConidtionList()));
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Alarm> getService()
	{
		// TODO Auto-generated method stub
		return alarmService;
	}


	@Override
	public String create()
	{
		// TODO Auto-generated method stub
		return null;
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
		alarmService.deleteAlarmList(Arrays.asList(this.getSelectedIDList()));
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
		// TODO Auto-generated method stub
		return null;
	}



	public List<News> getNewsContent()
	{
		return newsContent;
	}


	public void setNewsContent(List<News> newsContent)
	{
		this.newsContent = newsContent;
	}


	public AlarmManageService getAlarmService()
	{
		return alarmService;
	}

	public void setAlarmService(AlarmManageService alarmService)
	{
		this.alarmService = alarmService;
	}


	public TableDataModel<Alarm> getAlarmTable()
	{
		return alarmTable;
	}


	public void setAlarmTable(TableDataModel<Alarm> alarmTable)
	{
		this.alarmTable = alarmTable;
	}


	public AlarmFilterModel getAlarmFilter()
	{
		return alarmFilter;
	}


	public void setAlarmFilter(AlarmFilterModel alarmFilter)
	{
		this.alarmFilter = alarmFilter;
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
