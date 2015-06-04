/**   
* @Title: OrgManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2015-6-3 下午4:01:14 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.SysConstant;
import cn.fuego.smart.home.domain.Organization;
import cn.fuego.smart.home.service.OrgManageService;

 /** 
 * @ClassName: OrgManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-6-3 下午4:01:14 
 *  
 */
public class OrgManageServiceImpl extends MispCommonServiceImpl<Organization> implements OrgManageService
{
	private FuegoLog log = FuegoLog.getLog(getClass());


	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#create(int, java.lang.Object)
	 */
	@Override
	public void create(int userID, Organization obj)
	{
		//创建管理员
		SystemUser admin = new SystemUser();
		admin.setUserName(obj.getAdmin_name());
		admin.setRole(obj.getAdmin_role());// 超级管理员
		
		MISPServiceContext.getInstance().getUserService().validatorForCreate(admin);

		
		//生成org_id
		if(ValidatorUtil.isEmpty(obj.getOrg_id()))
		{
			String now_id = getNextCode(obj.getOrg_parent_id());
		 
			obj.setOrg_id(now_id);
 		}
		admin.setOrg_id(obj.getOrg_id());
		// 创建组织机构，同时在内部执行验重
		super.create(userID, obj);

		MISPServiceContext.getInstance().getUserService().create(userID, admin);
		
	}


	public List<String> getAllParent(String org_id)
	{
		List<String> idList = new ArrayList<String>();
		
		if(!ValidatorUtil.isEmpty(org_id))
		{
			String[] strs = org_id.split("\\.");
			if(null != strs && strs.length >0)
			{
				String code = "";
				for(int i=0;i<strs.length-1;i++)
				{
				    code = code + strs[i];
				    String parent = new String(code);
				    idList.add(parent);
				}
			}
		}
		return idList;
	}
	private String getNextCode(String parent_id)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"org_parent_id", parent_id));
		conditionList.add(new QueryCondition(ConditionTypeEnum.DESC_ORDER,"org_id"));
		List<Organization> orgList =this.getDao().getAll(conditionList);
		// 取编码的最后一项，例如1000.1002.001
		int num = Integer.valueOf(SysConstant.ROOT_ORG_CODE);// 初始编码
		if (!ValidatorUtil.isEmpty(orgList))
		{
			String[] strs = orgList.get(0).getOrg_id().split("\\.");
			num = Integer.valueOf(strs[strs.length - 1]) + 1;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(parent_id);
		sb.append(".");
		sb.append(num);
 		return sb.toString();
	}


	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#delete(int, java.util.List)
	 */
	@Override
	public void delete(int userID, List<String> idList)
	{
		//删除用户同时删除关联管理员
		List<QueryCondition> conditionList= new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.IN, "org_id", idList));
		this.getDao(SystemUser.class).delete(conditionList);
		
		super.delete(userID, idList);
	}
	
	
	
}
