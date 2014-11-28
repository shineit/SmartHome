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
import cn.fuego.smart.home.constant.ErrorMessageConst;
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void offline(Concentrator concentrator)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Concentrator getConcentByID(String concentID)
	{
		Concentrator concent= (Concentrator) DaoContext.getInstance().getConcentratorDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentID));
		return concent;
	}

	@Override
	public void modifyConcentInfo(Concentrator concent)
	{
		Concentrator oldConcent= (Concentrator) DaoContext.getInstance().getConcentratorDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID" ,String.valueOf(concent.getConcentratorID())));
		concent.setStatus(oldConcent.getStatus());
		concent.setAddr(oldConcent.getAddr());
		concent.setLocationNS(oldConcent.getLocationNS());
		concent.setLocationWE(oldConcent.getLocationWE());
		concent.setMark(oldConcent.getMark());
		DaoContext.getInstance().getConcentratorDao().update(concent);
	}

	@Override
	public void deleteConcentList(List<String> concentIDList)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, "concentratorID", concentIDList);	
		DaoContext.getInstance().getConcentratorDao().delete(condition);
		
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
	public AbstractDataSource<UserConcentrator> getPermissionDataSourceByID(List<QueryCondition> conidtionList)
	{
		AbstractDataSource<UserConcentrator> datasource = new DataBaseSourceImpl<UserConcentrator>(UserConcentrator.class,conidtionList);
		
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
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",String.valueOf(userPermission.getConcentratorID())));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",String.valueOf(userPermission.getUserID())));
	    
	    UserConcentrator oldPermission =(UserConcentrator) DaoContext.getInstance().getUserConcentratorDao().getUniRecord(conditionList);
	    oldPermission.setOperate(userPermission.getOperate());
	    DaoContext.getInstance().getUserConcentratorDao().update(oldPermission);
	}

	@Override
	public void deleteConcentByConcentID(String concentratorID)
	{
		
		Concentrator oldConcentrator= (Concentrator) DaoContext.getInstance().getConcentratorDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID",concentratorID));
		//UserConcentrator oldPermission = (UserConcentrator) DaoContext.getInstance().getUserConcentratorDao().getAll(new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID",concentratorID));
		if(oldConcentrator!=null)
		{
			DaoContext.getInstance().getConcentratorDao().delete(new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID",concentratorID));
		}
		else
		{
			throw new MISPException(MISPErrorMessageConst.TARGET_NOT_EXISTED);
		}
		
		DaoContext.getInstance().getUserConcentratorDao().delete(new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID",concentratorID));
	}
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return Concentrator.PRI_KEY;
	}




}
