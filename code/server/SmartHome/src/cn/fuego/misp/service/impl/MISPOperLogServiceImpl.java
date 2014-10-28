/**   
* @Title: MISPOperLogServiceImpl.java 
* @Package cn.fuego.misp.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-2 下午04:43:34 
* @version V1.0   
*/ 
package cn.fuego.misp.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.OperLog;
import cn.fuego.misp.service.MISPOperLogService;
import cn.fuego.misp.web.model.log.LogFilterModel;

/** 
 * @ClassName: MISPOperLogServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-2 下午04:43:34 
 *  
 */

public class MISPOperLogServiceImpl implements MISPOperLogService
{

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPOperLogService#recordLog(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void recordLog(String user, String opeateName, String object, String result, String desp)
	{
		OperLog operLog = new OperLog();
		operLog.setUser(user);
		operLog.setName(opeateName);
		operLog.setObject(object);
		operLog.setResult(result);
		operLog.setDesp(desp);
		operLog.setOperTime(DateUtil.getCurrentDate());
		MISPDaoContext.getInstance().getOperLogDao().create(operLog);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPOperLogService#recordLog(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void recordLog(String user, String opeateName, String object, String result)
	{

		this.recordLog(user, opeateName, object, result,null);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPOperLogService#getOperLogList()
	 */
	@Override
	public AbstractDataSource<OperLog> getOperLogList(LogFilterModel filter)
	{

		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(null != filter)
			
		{
			if(!ValidatorUtil.isEmpty(filter.getId()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"id",filter.getId()));			
			}


			if(!ValidatorUtil.isEmpty(filter.getUser()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"user",filter.getUser()));
				
			}
			if(!ValidatorUtil.isEmpty(filter.getName()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"name",filter.getName()));
				
			}			
			if(!ValidatorUtil.isEmpty(filter.getStartTime()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"operTime",filter.getStartTime()));
			}
			if(!ValidatorUtil.isEmpty(filter.getEndTime()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER_EQ,"operTime",filter.getEndTime()));
			}			
		}

		AbstractDataSource<OperLog> dataSource = new DataBaseSourceImpl<OperLog>(OperLog.class,conditionList);
	 
		return dataSource;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPOperLogService#deleteLog(java.util.List)
	 */
	@Override
	public void deleteLog(List<String> logIDList)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, "id", logIDList);
		MISPDaoContext.getInstance().getOperLogDao().delete(condition);

	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.MISPOperLogService#deleteLog(java.lang.String)
	 */
	@Override
	public void deleteLog(String logID)
	{
		List<String> logIDList = new ArrayList<String>();
		logIDList.add(logID);
		deleteLog(logIDList);

	}

}
