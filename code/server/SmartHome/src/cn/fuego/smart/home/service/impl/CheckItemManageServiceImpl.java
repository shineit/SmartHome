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
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.CheckItem;
import cn.fuego.smart.home.service.CheckItemManageService;



/** 
* @ClassName: CheckItemManageServiceImpl 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午2:38:51 
*  
*/ 
public class CheckItemManageServiceImpl extends MispCommonServiceImpl<CheckItem> implements CheckItemManageService
{

	@Override
	public List<CheckItem> getCheckItemByID(String companyID)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(companyID))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "companyID", companyID));
		}
		List<CheckItem> itemList=  new ArrayList<CheckItem>();
		itemList= DaoContext.getInstance().getCheckItemDao().getAll(conditionList);
		return itemList;
	}
 
}
