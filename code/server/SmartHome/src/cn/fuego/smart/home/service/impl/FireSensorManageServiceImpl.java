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
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.FireSensorManageService;


/** 
* @ClassName: FireSensorManageServiceImpl 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午2:36:20 
*  
*/ 
public class FireSensorManageServiceImpl extends MispCommonServiceImpl<FireSensor> implements FireSensorManageService
{

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
