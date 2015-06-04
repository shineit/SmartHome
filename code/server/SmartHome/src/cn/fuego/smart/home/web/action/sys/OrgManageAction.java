/**   
* @Title: OrgManage.java 
* @Package cn.fuego.smart.home.web.action.sys 
* @Description: TODO
* @author Tang Jun   
* @date 2015-6-3 下午3:49:18 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.sys;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.constant.SysConstant;
import cn.fuego.smart.home.constant.UserTypeEnum;
import cn.fuego.smart.home.domain.Organization;
import cn.fuego.smart.home.service.ServiceContext;

 /** 
 * @ClassName: OrgManage 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-6-3 下午3:49:18 
 *  
 */
public class OrgManageAction extends DWZTableAction<Organization>
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Organization> getService()
	{
		// TODO Auto-generated method stub
		return ServiceContext.getInstance().getOrgManageService();
	}


	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.EasyUITableAction#GetFilterCondition()
	 */
	@Override
	public List<QueryCondition> getFilterCondition()
	{
		List<QueryCondition> conditionList = super.getFilterCondition();
		if(null == conditionList)
		{
			conditionList = new ArrayList<QueryCondition>();
		}
		conditionList.add(new QueryCondition(ConditionTypeEnum.LIKE_LEFT, "org_id", this.getLoginUser().getOrg_id()));

		return conditionList;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#CreateCallFoward(java.lang.Object)
	 */
	@Override
	public void CreateCallFoward(Organization obj)
	{
		obj.setOrg_parent_id(this.getLoginUser().getOrg_id());
		obj.setAdmin_role(UserTypeEnum.SUB_ADMIN.getTypeValue());
	}
 
}
