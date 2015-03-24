/**   
* @Title: MISPPrivilegeManageImpl.java 
* @Package cn.fuego.misp.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-21 上午10:30:04 
* @version V1.0   
*/ 
package cn.fuego.misp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.constant.PrivilegeAccessObjTypeEnum;
import cn.fuego.misp.constant.PrivilegeMasterTypeEnum;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.Privilege;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPPrivilegeManage;

 /** 
 * @ClassName: MISPPrivilegeManageImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-21 上午10:30:04 
 *  
 */
public class MISPPrivilegeManageImpl implements MISPPrivilegeManage
{

	/**
	 * 
	 * @param userID
	 * @return
	 */
	public String getUserRole(String userID)
	{
		String role=null;
		SystemUser targetUser = (SystemUser) MISPDaoContext.getInstance().getSystemUserDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,SystemUser.getUserIDAttr(),userID));

		if(null!=targetUser)
		{
			role=String.valueOf(targetUser.getRole());
		}
		else
		{
			throw new MISPException(MISPErrorMessageConst.TARGET_NOT_EXISTED);
		}
		return role;
	}
	/*
	 * (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPPrivilegeManage#getMenuIDListByUser(java.lang.String)
	 */
	public Set<String> getObjectIDListByUser(String accessObjType,String userID)
	{
		
 
		//根据用户ID，获取具有权限的菜单ID
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterTypeAttr(),PrivilegeMasterTypeEnum.USER.getMasterType()));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterValueAttr(), userID));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeAttr(),accessObjType));
		List<Privilege> privilegeList = (List<Privilege>) MISPDaoContext.getInstance().getPrivilegeDao().getAll(conditionList);
		
		Set<String> objIDList = new HashSet<String>();
		for(Privilege e : privilegeList)
		{
			objIDList.add(e.getAccessObjValue());
		}
		
		//根据用户角色获取具有权限的菜单ID
 		objIDList.addAll(getObjectIDListByRole(accessObjType,getUserRole(userID)));
 		
 		
			
		return objIDList;
	}
	public Set<String> getObjectIDListByRole(String accessObjType,String roleID)
	{
		
 
		//根据用户ID，获取具有权限的菜单ID
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterTypeAttr(),PrivilegeMasterTypeEnum.ROLE.getMasterType()));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterValueAttr(), roleID));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeAttr(),accessObjType));
		List<Privilege> privilegeList = (List<Privilege>) MISPDaoContext.getInstance().getPrivilegeDao().getAll(conditionList);
		
		Set<String> objIDList = new HashSet<String>();
		for(Privilege e : privilegeList)
		{
			objIDList.add(e.getAccessObjValue());
		}
 
			
		return objIDList;
	}
	/*
	 * (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPPrivilegeManage#getMenuIDListByUser(java.lang.String)
	 */
	public Set<String> getMenuIDListByUser(String userID)
	{
		
 
		//根据用户ID，获取具有权限的菜单ID
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterTypeAttr(),PrivilegeMasterTypeEnum.USER.getMasterType()));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterValueAttr(), userID));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeAttr(),PrivilegeAccessObjTypeEnum.MENU.getObjectType()));
		List<Privilege> privilegeList = (List<Privilege>) MISPDaoContext.getInstance().getPrivilegeDao().getAll(conditionList);
		
		Set<String> menuIDList = new HashSet<String>();
		for(Privilege e : privilegeList)
		{
			menuIDList.add(e.getAccessObjValue());
		}
		
		//根据用户角色获取具有权限的菜单ID
 		menuIDList.addAll(getMenuIDListByRole(getUserRole(userID)));
 		
 		
			
		return menuIDList;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPPrivilegeManage#getMenuIDListByRole(java.lang.String)
	 */
	@Override
	public Set<String> getMenuIDListByRole(String roleID)
	{
		// TODO Auto-generated method stub
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterTypeAttr(),PrivilegeMasterTypeEnum.ROLE.getMasterType()));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterValueAttr(),roleID));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeAttr(),PrivilegeAccessObjTypeEnum.MENU.getObjectType()));
		List<Privilege> privilegeList = (List<Privilege>) MISPDaoContext.getInstance().getPrivilegeDao().getAll(conditionList);
		
		Set<String> menuIDList = new HashSet<String>();
		for(Privilege e : privilegeList)
		{
			menuIDList.add(e.getAccessObjValue());
		}
		
			
		return menuIDList;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPPrivilegeManage#hasPrivilege(java.lang.String, int)
	 */
	@Override
	public boolean hasPrivilege(String userID, String accessObjType,String accessObjValue)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterTypeAttr(),PrivilegeMasterTypeEnum.USER.getMasterType()));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterValueAttr(), userID));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeAttr(),accessObjType));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, "accessObjValue",accessObjValue));

		List<Privilege> privilegeList = (List<Privilege>) MISPDaoContext.getInstance().getPrivilegeDao().getAll(conditionList);
		
		Set<String> objIDList = new HashSet<String>();
		for(Privilege e : privilegeList)
		{
			objIDList.add(e.getAccessObjValue());
		}
 
		return false;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPPrivilegeManage#hasPrivilege(java.lang.String, int)
	 */
	@Override
	public boolean hasPrivilege(String userID, int prvilegeID)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 创建用户公司关联
	 */
	@Override
	public void createUserCompany(String userID, String companyID)
	{
		Set<String> companyIDList= getObjectIDListByUser(PrivilegeAccessObjTypeEnum.COMPANY.getObjectType(),userID);
		if(!companyIDList.contains(companyID))
		{
			Privilege userCompany= new Privilege();
			userCompany.setMasterType(PrivilegeMasterTypeEnum.USER.getMasterType());
			userCompany.setMasterValue(userID);
			userCompany.setAccessObjType(PrivilegeAccessObjTypeEnum.COMPANY.getObjectType());
			userCompany.setAccessObjValue(companyID);
			MISPDaoContext.getInstance().getPrivilegeDao().create(userCompany);
		}
		else
		{
			
			throw new MISPException(MISPErrorMessageConst.LINK_EXISTED);
		}

	}
	/**
	 * 删除用户公司关联
	 */
	@Override
	public void deleteUserCompany(String userID, String companyID)
	{
		Set<String> companyIDList= getObjectIDListByUser(PrivilegeAccessObjTypeEnum.COMPANY.getObjectType(),userID);
		if(companyIDList.contains(companyID))
		{
			List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
			
			conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterTypeAttr(),PrivilegeMasterTypeEnum.USER.getMasterType()));
			conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterValueAttr(), userID));
			conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeAttr(),PrivilegeAccessObjTypeEnum.COMPANY.getObjectType()));
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeValue(), companyID));
			MISPDaoContext.getInstance().getPrivilegeDao().delete(conditionList);
		}
		else
		{
			throw new MISPException(MISPErrorMessageConst.LINK_NOT_EXISTED);
		}
	}
	@Override
	public Set<String> getUserIDListByCommpany(String companyID)
	{
		Set<String> userIDList= new HashSet<String>();
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeValue(), companyID));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getMasterTypeAttr(),PrivilegeMasterTypeEnum.USER.getMasterType()));
		conditionList.add( new QueryCondition(ConditionTypeEnum.EQUAL, Privilege.getAccessObjTypeAttr(),PrivilegeAccessObjTypeEnum.COMPANY.getObjectType()));
		List<Privilege> userCompanyList=MISPDaoContext.getInstance().getPrivilegeDao().getAll(conditionList);
		if(!ValidatorUtil.isEmpty(userCompanyList))
		{
			for(Privilege p:userCompanyList)
			{
				userIDList.add(p.getMasterValue());
			}
		}
		return userIDList;
	}
	

}
