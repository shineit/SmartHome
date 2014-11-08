/**   
* @Title: AlarmManageAction.java 
* @Package cn.fuego.smart.home.web.action.info 
* @Description: TODO
* @author Aether
* @date 2014-11-7 上午10:01:27 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.info;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.service.AlarmManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.AlarmFilterModel;

/** 
 * @ClassName: AlarmManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:01:27 
 *  
 */
public class AlarmManageAction extends DWZTableAction
{
	/**
	 * 
	 */
	private Log log = LogFactory.getLog(NewsManageAction.class);
	
	
	private static final long serialVersionUID = 1L;
	private AlarmManageService alarmService = ServiceContext.getInstance().getAlarmManageService();

	private TableDataModel<Alarm> alarmTable = new TableDataModel<Alarm>();
    private AlarmFilterModel alarmFilter = new AlarmFilterModel();

    @Override
    public String execute()
    {
      	
    	alarmTable.setPage(this.getPage());
    	alarmTable.setDataSource(alarmService.getAlarmDataSource(alarmFilter.getConidtionList()));
    	return SUCCESS;
    	
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

}
