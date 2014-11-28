/**   
* @Title: LogManageAction.java 
* @Package cn.fuego.misp.web.action.log 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-8 上午10:46:17 
* @version V1.0   
*/ 
package cn.fuego.misp.web.action.log;

import java.util.List;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.domain.OperLog;
import cn.fuego.misp.service.MISPOperLogService;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.log.LogFilterModel;

/** 
 * @ClassName: LogManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-8 上午10:46:17 
 *  
 */

public class LogManageAction extends DWZTableAction<OperLog>
{
	private MISPOperLogService service = MISPServiceContext.getInstance().getMISPOperLogService();
  	private LogFilterModel filter = new LogFilterModel(); 
	
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
    @Override
    public List<QueryCondition> getFilterCondition()
    {
    	return filter.getConidtionList();
    }
	@Override
	public MispCommonService<OperLog> getService()
	{
		// TODO Auto-generated method stub
		return service;
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
