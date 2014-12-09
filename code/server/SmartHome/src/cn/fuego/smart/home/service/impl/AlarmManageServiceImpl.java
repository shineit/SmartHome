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
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.service.AlarmManageService;
import cn.fuego.smart.home.webservice.down.service.WebServiceContext;

/** 
 * @ClassName: AlarmManageServiceImpl 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:13:43 
 *  
 */
public class AlarmManageServiceImpl extends MispCommonServiceImpl<Alarm> implements AlarmManageService
{

	 

	@Override
	public void deleteAlarmList(List<String> alarmIDList)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, Alarm.PRI_KEY, alarmIDList);	
		DaoContext.getInstance().getAlarmDao().delete(condition);
		
	}

	@Override
	public void create(List<Alarm> objList)
	{
		
		super.create(objList); 
		WebServiceContext.getInstance().getPushService().pushAlarm(objList);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#GetPrimaryName()
	 */
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return Alarm.PRI_KEY;
	}

	@Override
	public void clearAlarm(String id)
	{
		// TODO Auto-generated method stub
		
	}

}
