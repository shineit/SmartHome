/**   
* @Title: CompanyManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:45:39 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.constant.PrivilegeAccessObjTypeEnum;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.service.CompanyManageService;
import cn.fuego.smart.home.web.model.UserCompanyModel;

 /** 
 * @ClassName: CompanyManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午6:45:39 
 *  
 */
public class CompanyManageServiceImpl extends MispCommonServiceImpl<Company>  implements  CompanyManageService 
{

	@Override
	public List<Company> getCompanyList(int userID)
	{
		Set<String> companyIDList=  MISPServiceContext.getInstance().getMISPPrivilegeManage().getObjectIDListByUser(PrivilegeAccessObjTypeEnum.COMPANY.getObjectType(), String.valueOf(userID));
 		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(companyIDList))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.IN,"companyID",new ArrayList<String>(companyIDList)));
		}
		
		List<Company> companyList  = new ArrayList<Company>();
		companyList = DaoContext.getInstance().getCompanyDao().getAll(conditionList);
		return companyList;
	}
	
	public Company getCompanyByConcentorID(long concentorID)
	{
		List<Company> concentorList = this.get("concentratorID", concentorID);
		if(!ValidatorUtil.isEmpty(concentorList))
		{
			return concentorList.get(0);
		}
		return null;
	}

	@Override
	public AbstractDataSource<SystemUser> getPermissionDataSource(List<String> userIDList)
	{
		AbstractDataSource<SystemUser> datasource = null;
		List<QueryCondition> conditionList=new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(userIDList))
		{
			
			conditionList.add(new QueryCondition(ConditionTypeEnum.IN, "userID", userIDList));
			
		}
		else
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.FALSE, "userID"));
		}
		datasource = new DataBaseSourceImpl<SystemUser>(SystemUser.class,conditionList);
		return datasource;
	}

	@Override
	public void addPermission(UserCompanyModel userPermission)
	{
		if(!ValidatorUtil.isEmpty(userPermission.getUserID())&&!ValidatorUtil.isEmpty(userPermission.getCompanyID()))
		{
			MISPServiceContext.getInstance().getMISPPrivilegeManage().createUserCompany(userPermission.getUserID(), userPermission.getCompanyID());
		}
		else
		{
			throw new MISPException(MISPErrorMessageConst.INPUT_NULL);
		}
		
	}

	@Override
	public void deletePermissionByID(UserCompanyModel userPermission)
	{
		if(!ValidatorUtil.isEmpty(userPermission.getUserID())&&!ValidatorUtil.isEmpty(userPermission.getCompanyID()))
		{
			MISPServiceContext.getInstance().getMISPPrivilegeManage().deleteUserCompany(userPermission.getUserID(), userPermission.getCompanyID());
		}
		else
		{
			throw new MISPException(MISPErrorMessageConst.INPUT_NULL);
		}
		
	}

 
}
