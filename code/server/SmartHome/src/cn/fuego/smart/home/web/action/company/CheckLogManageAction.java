/**   
* @Title: CompanyManageAction.java 
* @Package cn.fuego.smart.home.web.action.company 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:39:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.company;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.CheckLog;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.service.CheckLogManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.CheckLogFilterModel;

/** 
* @ClassName: CheckLogManageAction 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午3:07:18 
*  
*/ 
public class CheckLogManageAction extends DWZTableAction<CheckLog>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CheckLogManageService service  = ServiceContext.getInstance().getCheckLogService();

	private CheckLogFilterModel filter = new  CheckLogFilterModel();
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<CheckLog> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
	@Override
	public List<QueryCondition> getFilterCondition()
	{
		List<QueryCondition> conditionList = super.getFilterCondition();
		if(null == conditionList)
		{
			conditionList = new ArrayList<QueryCondition>();
		}
		
		//只能看到自己公司的巡检日志
		QueryCondition comCon = new QueryCondition(ConditionTypeEnum.EQUAL, "org_id",this.getLoginUser().getOrg_id());
		List<Company> comList = MISPDaoContext.getInstance().getDao(Company.class).getAll(comCon);
		
		List<Integer> commID = new ArrayList<Integer>();
		for(Company comm : comList)
		{
			commID.add(comm.getCompanyID());
		}
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, "companyID",commID));

    	return conditionList;
	}
	public CheckLogFilterModel getFilter()
	{
		return filter;
	}
	public void setFilter(CheckLogFilterModel filter)
	{
		this.filter = filter;
	}


}
