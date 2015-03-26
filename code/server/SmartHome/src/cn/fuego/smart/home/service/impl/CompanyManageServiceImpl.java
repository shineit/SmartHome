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
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.constant.PrivilegeAccessObjTypeEnum;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.DeviceKindEunm;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.CompanyManageService;
import cn.fuego.smart.home.service.ServiceContext;
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
	private FuegoLog log = FuegoLog.getLog(getClass());

	@Override
	public void validator(Company obj)
	{
		// TODO Auto-generated method stub
		DeviceKindEunm kind = ApplicationProtocol.getObjKindByID(obj.getConcentratorID());
		if(DeviceKindEunm.FIRE_CONCENTRATOR != kind)
		{
			log.error("the concentor id is not right" + obj.getConcentratorID());
			
			throw new MISPException(ErrorMessageConst.CONCENTRATOR_ID_WRONG);
		}
	}

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
			/**
			 * 添加用户 公司权限，要自动 用户 集中器权限
			 */
			MISPServiceContext.getInstance().getMISPPrivilegeManage().createUserCompany(userPermission.getUserID(), userPermission.getCompanyID());
			Company company = this.get(userPermission.getCompanyID());
			UserConcentrator userCon = new UserConcentrator();
			userCon.setConcentratorID(company.getConcentratorID());
			userCon.setUserID(Integer.valueOf(userPermission.getUserID()));
			ServiceContext.getInstance().getConcentratorManageService().addPermission(userCon); 
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
			/**
			 * 删除用户 公司权限，要同时删除 用户 集中器权限
			 */
			MISPServiceContext.getInstance().getMISPPrivilegeManage().deleteUserCompany(userPermission.getUserID(), userPermission.getCompanyID());
			Company company = this.get(userPermission.getCompanyID());
			ServiceContext.getInstance().getConcentratorManageService().deletePermissionByID(userPermission.getUserID(), String.valueOf(company.getConcentratorID())); 
		}
		else
		{
			throw new MISPException(MISPErrorMessageConst.INPUT_NULL);
		}
		
	}


	
	

 
}
