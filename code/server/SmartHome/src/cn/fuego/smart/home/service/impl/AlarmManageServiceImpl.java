/**   
* @Title: AlarmManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Aether
* @date 2014-11-7 上午10:13:43 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.HomeAlarmView;
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

	 
 
	public List<HomeAlarmView> getAlarmOfUser(int userID,int startNum,int pageSize)
	{
 		List<Long> concentorIDList = DataPrivilegeManage.getConcentorOfUser(userID);
 
 		List<HomeAlarmView> alarmList = new ArrayList<HomeAlarmView>();
 	 
 		if(!ValidatorUtil.isEmpty(concentorIDList))
 		{
 			List<QueryCondition> condtionList = new ArrayList<QueryCondition>();
 	 		//condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"objType",AlarmObjTypeEnmu.CONCENTRATOR_ALARM.getIntValue()));
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.IN,"concentratorID",concentorIDList));
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"clearStatus",AlarmClearEnum.NONE_CLEAR.getIntValue()));
 	 		alarmList.addAll(this.getDao(HomeAlarmView.class).getAll(condtionList, startNum, pageSize));
 		}
 		
 		return alarmList;
 		
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
	public void autoClear(int id)
	{
		Alarm alarm = this.get(String.valueOf(id));
		alarm.setClearStatus(AlarmClearEnum.AUTO_CLEAR.getIntValue());
		alarm.setClearUser(AlarmClearEnum.AUTO_CLEAR.getStrValue());
		alarm.setClearTime(DateUtil.getCurrentDate());
		this.modify(alarm);
	}
	
	@Override
	public void manualClear(int userID, int id)
	{
		Alarm alarm = this.get(String.valueOf(id));
		alarm.setClearStatus(AlarmClearEnum.MANUAL_CLEAR.getIntValue());
		alarm.setClearTime(DateUtil.getCurrentDate());
		alarm.setClearUser(String.valueOf(userID));
		this.modify(alarm);
		
	}

	@Override
	public HomeAlarmView getHomeAlarmByID(String alarmID)
	{
		HomeAlarmView homeAlarm =(HomeAlarmView) this.getDao(HomeAlarmView.class).getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"id",alarmID));
		return homeAlarm;
	}

 
}
