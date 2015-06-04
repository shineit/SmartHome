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

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.FireAlarmView;
import cn.fuego.smart.home.service.FireAlarmManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.AlarmFilterModel;

/** 
 * @ClassName: HomeAlarmManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-11 下午5:07:24 
 *  
 */
public class FireAlarmManageAction extends DWZTableAction<FireAlarmView>
{

	private FireAlarmManageService service = ServiceContext.getInstance().getFireAlarmService();
	
	private AlarmFilterModel filter = new  AlarmFilterModel();
	
	@Override
	public MispCommonService<FireAlarmView> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public List<QueryCondition> getFilterCondition()
	{
 
    	List<QueryCondition>  conditionList = this.filter.getConidtionList();
		conditionList.add(new QueryCondition(ConditionTypeEnum.LIKE_LEFT, "org_id", this.getLoginUser().getOrg_id()));

		return conditionList;
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
