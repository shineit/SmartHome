/**   
* @Title: LogManageAction.java 
* @Package cn.fuego.misp.web.action.log 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-8 上午10:46:17 
* @version V1.0   
*/ 
package cn.fuego.misp.web.action.log;

import java.util.Arrays;

import cn.fuego.misp.domain.OperLog;
import cn.fuego.misp.service.MISPOperLogService;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.log.LogFilterModel;
import cn.fuego.misp.web.model.page.TableDataModel;

/** 
 * @ClassName: LogManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-8 上午10:46:17 
 *  
 */

public class LogManageAction extends DWZTableAction
{
	private MISPOperLogService service = MISPServiceContext.getInstance().getMISPOperLogService();
	private TableDataModel<OperLog> logList = new TableDataModel<OperLog>();
 	private LogFilterModel filter = new LogFilterModel(); 
	public String execute()
	{
		logList.setPage(this.getPage());
		logList.setDataSource(service.getOperLogList(filter));
		return SUCCESS;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#create()
	 */
	@Override
	public String create()
	{
		// TODO Auto-generated method stub
		return execute();
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#delete()
	 */
	@Override
	public String delete()
	{
		service.deleteLog(this.getSelectedID());
		return execute();
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#deleteList()
	 */
	@Override
	public String deleteList()
	{
		service.deleteLog(Arrays.asList(this.getSelectedIDList()));
		return  MISP_DONE_PAGE;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#modify()
	 */
	@Override
	public String modify()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#show()
	 */
	@Override
	public String show()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public TableDataModel<OperLog> getLogList()
	{
		return logList;
	}
	public void setLogList(TableDataModel<OperLog> logList)
	{
		this.logList = logList;
	}
	public LogFilterModel getFilter()
	{
		return filter;
	}
	public void setFilter(LogFilterModel filter)
	{
		this.filter = filter;
	}
	
	

}
