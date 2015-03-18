/**   
* @Title: NewsManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午2:46:32 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.CheckLogStatusEnum;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.domain.CheckLog;
import cn.fuego.smart.home.service.CheckLogManageService;



/** 
* @ClassName: CheckLogManageServiceImpl 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午2:37:19 
*  
*/ 
public class CheckLogManageServiceImpl extends MispCommonServiceImpl<CheckLog> implements CheckLogManageService
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	@Override
	public List<CheckLog> getCurrentLog(int companyID)
	{

 
		List<QueryCondition> condtionList = new ArrayList<QueryCondition>();
 		
		//是否需要做判空处理
		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "companyID", companyID));
		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "checkResult", CheckResultEnum.ABNORMAL.getIntValue()));
		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "status", CheckLogStatusEnum.LATEST.getIntValue()));

	 
		return this.getDao().getAll(condtionList);
	}

	@Override
	public void create(int userID, List<CheckLog> objList)
	{
		if(ValidatorUtil.isEmpty(objList))
		{
			log.warn("the check log list is empty");
			return;
		}
		
		int companyID = objList.get(0).getCompanyID();
		List<QueryCondition> condtionList = new ArrayList<QueryCondition>();

		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "companyID", companyID));
		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "status",CheckLogStatusEnum.LATEST.getIntValue()));
		
		this.ModifyByCondition(condtionList, "status", CheckLogStatusEnum.PREVIOUS.getIntValue());
		
		super.create(userID, objList);
	}

	 
	
 
}
