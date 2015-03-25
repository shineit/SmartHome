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
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.domain.FireSensor;
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

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.FireSensorManageService#getFireSensor(long, int, int, int)
	 */
	@Override
	public FireSensor getFireSensor(long concentratorID, int machineID,
			int loopID, int codeID)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();

	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"machineID",machineID));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"loopID",loopID));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"codeID",codeID));

		FireSensor sensor = this.getDao().getUniRecord(conditionList);
		return sensor;
	}

 
}
