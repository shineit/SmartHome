/**   
* @Title: AlarmManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Aether
* @date 2014-11-7 上午10:13:43 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.service.AlarmManageService;

/** 
 * @ClassName: AlarmManageServiceImpl 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:13:43 
 *  
 */
public class AlarmManageServiceImpl implements AlarmManageService
{

	@Override
	public AbstractDataSource<Alarm> getAlarmDataSource(List<QueryCondition> conditionList)
	{
		AbstractDataSource<Alarm> datasource = new DataBaseSourceImpl<Alarm>(Alarm.class,conditionList);
		 
		return datasource;
	}

	@Override
	public void deleteAlarmList(List<String> alarmIDList)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, "id", alarmIDList);	
		DaoContext.getInstance().getAlarmDao().delete(condition);
		
	}

}
