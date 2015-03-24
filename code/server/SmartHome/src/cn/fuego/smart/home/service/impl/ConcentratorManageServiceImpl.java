/**   
* @Title: ServiceOrderManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午3:05:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.ConcentratorPermissionEnum;
import cn.fuego.smart.home.constant.ConcentratorStatusEnum;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.UserTypeEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.ConcentratorManageService;

 /** 
 * @ClassName: ServiceOrderManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午3:05:35 
 *  
 */
public class ConcentratorManageServiceImpl extends MispCommonServiceImpl<Concentrator> implements ConcentratorManageService
{

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#getNewsDataSource()
	 */
	private FuegoLog log =  FuegoLog.getLog(getClass());

 
	@Override
	public void online(Concentrator concentrator)
	{
		Concentrator old = super.get(String.valueOf(concentrator.getConcentratorID()));
	    if(null != old)
	    {
	    	log.info("the concentor is existed in database");
	    	old.setAddr(concentrator.getAddr());
	    	old.setConcentratorID(concentrator.getConcentratorID());
	    	old.setStatus(concentrator.getStatus());
	    	old.setLocationNS(concentrator.getLocationNS());
	    	old.setLocationWE(concentrator.getLocationWE());
	    	old.setIpAddr(concentrator.getIpAddr());
	    	old.setPort(concentrator.getPort());
	    	super.modify(concentrator);
	    }
	    else
	    {
	    	log.info("the concentrator is new " + concentrator);
	    	super.create(concentrator);
	    }
 
	    //ServiceContext.getInstance().getSensorManageService().syncSensorList(concentrator.getConcentratorID());
	    
	    
	}
	@Override
	public void offline(String ipAddr,int port)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();

	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"ipAddr",ipAddr));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"port",port));
	     
		Concentrator old = this.getDao().getUniRecord(conditionList);
		if(null != old)
		{
			log.info("the concentrator is offline.the concentrator "+ old);
			old.setStatus(ConcentratorStatusEnum.OFFLINE.getIntValue());
			this.modify(old);
		}
		else
		{
			log.warn("can not find the concentrator by ip and port.the ipAddr is"+ipAddr + ",the port is "+port);
		}
	}
	
	
	@Override
	public String getOperatePemission(int userID, String concentratorID)
	{
		String operatePemission=null;
		UserConcentrator oldPermission =getPermissionByID(String.valueOf(userID),concentratorID);
		SystemUser user = MISPDaoContext.getInstance().getSystemUserDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",String.valueOf(userID)));
		if(user.getRole()==UserTypeEnum.ADMIN.getTypeValue())
		{
			operatePemission = String.valueOf(ConcentratorPermissionEnum.ALL.getIntValue());//超级管理员默认有全部权限
			return operatePemission;
		}
		if(oldPermission!=null)
		{
			operatePemission = String.valueOf( ConcentratorPermissionEnum.getEnumByInt(oldPermission.getOperate()).getIntValue());
		}
		else
		{
			log.info("the user don't have the permission");
			throw new MISPException(MISPErrorMessageConst.OPERATE_PROHIBITED);
		}
		
		return operatePemission;
	}

	@Override
	public void modify(int userID,Concentrator concent)
	{
		Concentrator oldConcent= (Concentrator) DaoContext.getInstance().getConcentratorDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID" ,String.valueOf(concent.getConcentratorID())));
		concent.setStatus(oldConcent.getStatus());
		concent.setAddr(oldConcent.getAddr());
		concent.setLocationNS(oldConcent.getLocationNS());
		concent.setLocationWE(oldConcent.getLocationWE());
		concent.setMark(oldConcent.getMark());
		concent.setIpAddr(oldConcent.getIpAddr());
		concent.setPort(oldConcent.getPort());
		DaoContext.getInstance().getConcentratorDao().update(concent);
	}

 

	@Override
	public Concentrator getDistributionInfo(List<QueryCondition> mapConidtionList)
	{
		Concentrator concent= (Concentrator) DaoContext.getInstance().getConcentratorDao().getAll(mapConidtionList);
		if(concent==null)
		{
			log.warn("get DistributionInfo from Concentrator failed");
			throw new MISPException(MISPErrorMessageConst.RESULT_NULL);
		}
		return concent;
	}


	@Override
	public AbstractDataSource<UserConcentrator> getPermissionDataSource(int accountType, List<QueryCondition> conditionList)
	{
		AbstractDataSource<UserConcentrator> datasource =null;
		if(accountType==UserTypeEnum.ADMIN.getTypeValue())
		{
			datasource = new DataBaseSourceImpl<UserConcentrator>(UserConcentrator.class,conditionList);
		}
		else
		{
			log.info("the user don't have the permission");
			throw new MISPException(MISPErrorMessageConst.OPERATE_PROHIBITED);
		}
		return datasource;
	}

	@Override
	public void addPermission(UserConcentrator userConcentrator)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();

	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",String.valueOf(userConcentrator.getConcentratorID())));
	    if(null!=userConcentrator.getUserID())
	    {
	    	conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",String.valueOf(userConcentrator.getUserID())));
	    	
	    }
	    else
	    {
	    	throw new MISPException(MISPErrorMessageConst.INPUT_NULL);
	    }
	    
		UserConcentrator oldPermission =  (UserConcentrator) DaoContext.getInstance().getUserConcentratorDao().getUniRecord(conditionList);
		SystemUser oldUser = (SystemUser) MISPDaoContext.getInstance().getSystemUserDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",String.valueOf(userConcentrator.getUserID())));
		if(oldUser==null)
		{
			throw new MISPException(MISPErrorMessageConst.ERROR_USER_NOT_EXISTED);
		}
		if(oldPermission!=null)
		{
			
			throw new MISPException(ErrorMessageConst.PERMISSION_EXISTED);
			
		}
		
		DaoContext.getInstance().getUserConcentratorDao().create(userConcentrator);

		
		
	}

	@Override
	public void deletePermissionByID(String userID, String concentratorID)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();

	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",userID));
		DaoContext.getInstance().getUserConcentratorDao().delete(conditionList);
		
	}

	@Override
	public  UserConcentrator getPermissionByID(String userID, String concentratorID)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",userID));
	    UserConcentrator userConcentrator= (UserConcentrator) DaoContext.getInstance().getUserConcentratorDao().getUniRecord(conditionList);
		return userConcentrator;
		
	}

	@Override
	public void modifyPermission(UserConcentrator userPermission)
	{
    
	    UserConcentrator oldPermission =getPermissionByID(String.valueOf(userPermission.getUserID()),String.valueOf(userPermission.getConcentratorID()));
	    oldPermission.setOperate(userPermission.getOperate());
	    DaoContext.getInstance().getUserConcentratorDao().update(oldPermission);
	}
    /**
     * 删除集中器同时删除相应的关联权限信息
     */
	@Override
	public void delete(String concentratorID)
	{
		
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID",concentratorID);
		Concentrator oldConcentrator= (Concentrator) DaoContext.getInstance().getConcentratorDao().getUniRecord(condition);
		List<UserConcentrator> permissionList = new ArrayList<UserConcentrator>();
		permissionList = (List<UserConcentrator>) DaoContext.getInstance().getUserConcentratorDao().getAll(condition);
		
		if(oldConcentrator!=null)
		{
			DaoContext.getInstance().getConcentratorDao().delete(condition);
		}
		else
		{
			throw new MISPException(MISPErrorMessageConst.TARGET_NOT_EXISTED);
		}
		if(permissionList!=null)
		{
			DaoContext.getInstance().getUserConcentratorDao().delete(condition);
		}
		
	}
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return Concentrator.PRI_KEY;
	}

	/**
	 * 获取该用户ID下的所有集中器信息
	 */
	@Override
	public List<UserConcentrator> getUserConcentListByID(int userID)
	{
		List<UserConcentrator> list= new ArrayList<UserConcentrator>();
		list= DaoContext.getInstance().getUserConcentratorDao().getAll(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",userID));

		return list;
	}
	@Override
	public List<Concentrator> getConcentListByID(List<Long> concentIDList)
	{
		List<Concentrator> list= new ArrayList<Concentrator>();
		list= DaoContext.getInstance().getConcentratorDao().getAll(new QueryCondition(ConditionTypeEnum.IN, "concentratorID", concentIDList));
		return list;
	}
	@Override
	public List<String> getConcentIDListByUser(List<String> userIDList)
	{
		List<String> concentIDList = new ArrayList<String>();
		List<QueryCondition> conditionList= new ArrayList<QueryCondition>();

		conditionList.add(new QueryCondition(ConditionTypeEnum.IN, "userID", userIDList));
		List<UserConcentrator> ucList= DaoContext.getInstance().getUserConcentratorDao().getAll(conditionList);
		if(!ValidatorUtil.isEmpty(ucList))
		{
			for(UserConcentrator uc:ucList)
			{
				concentIDList.add(String.valueOf(uc.getConcentratorID()));
			}
		}
		else 
			throw new MISPException(MISPErrorMessageConst.LINK_NOT_EXISTED);
		return concentIDList;
	}


}
