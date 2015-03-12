/**   
* @Title: HomeAlarmManageAction.java 
* @Package cn.fuego.smart.home.web.action.device 
* @Description: TODO
* @author Aether
* @date 2015-3-11 下午5:07:24 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.device;

import java.util.List;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.HomeAlarmView;
import cn.fuego.smart.home.service.HomeAlarmManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.AlarmFilterModel;

/** 
 * @ClassName: HomeAlarmManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-11 下午5:07:24 
 *  
 */
public class HomeAlarmManageAction extends DWZTableAction<HomeAlarmView>
{

	private HomeAlarmManageService service = ServiceContext.getInstance().getHomeAlarmService();
	
	private AlarmFilterModel filter = new  AlarmFilterModel();
	
	@Override
	public MispCommonService<HomeAlarmView> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public List<QueryCondition> getFilterCondition()
	{
 
		return this.getFilter().getConidtionList();
	}

	public AlarmFilterModel getFilter()
	{
		return filter;
	}

	public void setFilter(AlarmFilterModel filter)
	{
		this.filter = filter;
	}
	
	

}
