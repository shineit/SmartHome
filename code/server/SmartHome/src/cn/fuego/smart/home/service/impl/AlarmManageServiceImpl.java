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
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AlarmIsFeedBackEnum;
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.device.send.DeviceManager;
import cn.fuego.smart.home.device.send.DeviceManagerFactory;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.AlarmType;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.HomeAlarmView;
import cn.fuego.smart.home.service.AlarmManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AlarmTypeCache;
import cn.fuego.smart.home.webservice.down.service.WebServiceContext;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;

/** 
 * @ClassName: AlarmManageServiceImpl 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:13:43 
 *  
 */
public class AlarmManageServiceImpl extends MispCommonServiceImpl<Alarm> implements AlarmManageService
{

	private FuegoLog log =  FuegoLog.getLog(getClass());

	 
	@Override
	public List<HomeAlarmView> getAlarmOfUser(int userID, int startNum,	int pageSize, List<AttributeJson> attrList)
	{
 		List<Long> concentorIDList = DataPrivilegeManage.getConcentorOfUser(userID);
 		 
 		List<HomeAlarmView> alarmList = new ArrayList<HomeAlarmView>();

 		if(!ValidatorUtil.isEmpty(concentorIDList))
 		{
 			List<QueryCondition> condtionList = new ArrayList<QueryCondition>();
 	 		//condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"objType",AlarmObjTypeEnmu.CONCENTRATOR_ALARM.getIntValue()));
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.IN,"concentratorID",concentorIDList));
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"clearStatus",AlarmClearEnum.NONE_CLEAR.getIntValue()));
/* 	 		if(!ValidatorUtil.isEmpty(attrList))
 	 		{
 	 			for(AttributeJson attr:attrList)
 	 	 		{
 	 	 			if(attr.getAttrName().equals(AttributeConst.ALARM_TYPE))
 	 	 			{
 	 	 				if(attr.getAttrValue().equals(AlarmTypeEnum.FIRE_ALARM.getStrValue()))
 	 	 				{
 	 	 					condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"alarmType",AlarmTypeEnum.FIRE_ALARM.getIntValue()));
 	 	 				}
 	 	 				else
 	 	 				{
 	 	 					condtionList.add(new QueryCondition(ConditionTypeEnum.NOT_EQUAL,"alarmType",AlarmTypeEnum.FIRE_ALARM.getIntValue()));
 	 	 				}
 	 	 			}
 	 	 		}
 	 		}*/
 	 		
 	 		
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.DESC_ORDER,"alarmTime"));
 	 		alarmList.addAll(this.getDao(HomeAlarmView.class).getAll(condtionList, startNum, pageSize));
 		}
 		
 		return alarmList;
	}
 
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
 	 		condtionList.add(new QueryCondition(ConditionTypeEnum.DESC_ORDER,"alarmTime"));
 	 		alarmList.addAll(this.getDao(HomeAlarmView.class).getAll(condtionList, startNum, pageSize));
 		}
 		
 		return alarmList;
 		
	}

	@Override
	public void create(Alarm obj)
	{
		if(null == obj)
		{
			log.warn("the alarm is null");
			return;
		}
		List<Alarm> objList = new ArrayList<Alarm>();
		objList.add(obj);
		create(objList);
	}
	
	@Override
	public void create(List<Alarm> objList)
	{
		
		List<Alarm> realAlarmList = new ArrayList<Alarm>();
		for(Alarm alarm : objList )
		{

			AlarmType alarmType = AlarmTypeCache.getInstance().getAlarmType(alarm.getAlarmType());
			
			if(null == alarmType)
			{
				log.error("can not find the alarm type,so discard the alarm. the alarm is "+alarm);
				continue;
			}
			
			List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
			
			
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",alarm.getConcentratorID()));
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"objID",alarm.getObjID()));
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"objID1",alarm.getObjID1()));
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"objID2",alarm.getObjID2()));
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"clearStatus",AlarmClearEnum.NONE_CLEAR.getIntValue()));
           

			if(AlarmIsFeedBackEnum.NORMAL.getIntValue() == alarmType.getIsFeedback())
			{
	 			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"alarmType",alarm.getAlarmType()));

				 List<Alarm> oldAlarm = super.getDao().getAll(conditionList);
				if(AlarmTypeEnum.RESET.getIntValue() == alarmType.getTypeID())
				{
					log.info("the alarm is reset");
					
					alarm.setClearStatus(AlarmClearEnum.AUTO_CLEAR.getIntValue());
					super.create(alarm); 
					realAlarmList.add(alarm);

					clearAlarm(alarm.getConcentratorID());
				}
				else
				{
					if(ValidatorUtil.isEmpty(oldAlarm))
					{
						super.create(alarm); 
						realAlarmList.add(alarm);
	 				}
					else
					{

						log.warn("the alarm is exist, maybe the alarm is repeat package,so discard is," + alarm);
					}
				}

			}
			else
			{
	 			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"alarmType",alarmType.getFeedbackID()));

				 List<Alarm> oldAlarm = super.getDao().getAll(conditionList);
			 
	 			 if(ValidatorUtil.isEmpty(oldAlarm))
				 {
					 log.warn("the alarm is not exist,maybe have been cleared, the feedback alarm is repeat package,so discard is," + alarm);

				 }
				 else
				 {
						oldAlarm.get(0).setClearStatus(AlarmClearEnum.AUTO_CLEAR.getIntValue());
						super.modify(oldAlarm.get(0));
						alarm.setClearStatus(AlarmClearEnum.AUTO_CLEAR.getIntValue());
						super.create(alarm); 
						realAlarmList.add(alarm);
				 }
				 

			}



		}
		
		//super.create(objList); 
		log.info("alarm need to check push or not " + realAlarmList);
		WebServiceContext.getInstance().getPushService().pushAlarm(realAlarmList);
	}
 
	@Override
	public void clearAlarm(long concentratorID)
	{
		log.info("now clear alarm of " + concentratorID);
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID));
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"clearStatus",AlarmClearEnum.NONE_CLEAR.getIntValue()));
  
		this.ModifyByCondition(conditionList, "clearStatus", AlarmClearEnum.AUTO_CLEAR.getIntValue());
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

	@Override
	public void manualClearList(int userID, List<String> alarmIDList)
	{
		List<Long> concentorIDList=new ArrayList<Long>();
		List<String> idList = new ArrayList<String>();
		if(!ValidatorUtil.isEmpty(alarmIDList))
		{
			idList.addAll(alarmIDList);
		}
		else
		{
			concentorIDList = DataPrivilegeManage.getConcentorOfUser(userID);
			//idList= getIDListByType(userID,AlarmTypeEnum.FIRE_ALARM.getIntValue(),concentorIDList);
		}

		Modify(idList,"clearStatus", AlarmClearEnum.MANUAL_CLEAR.getIntValue());
		Modify(idList,"clearUser", userID);
		for(Long concentorID:concentorIDList)
		{
			Concentrator concentrator = ServiceContext.getInstance().getConcentratorManageService().get(String.valueOf(concentorID));
			DeviceManager device = DeviceManagerFactory.getInstance().getDeviceManger(concentrator);
			device.reset(concentrator);
		}

		
	}

	private List<String> getIDListByType(int userID, int type,List<Long> concentorIDList)
	{
		List<String> idList = new ArrayList<String>();
		
		List<QueryCondition> condtionList = new ArrayList<QueryCondition>();
		condtionList.add(new QueryCondition(ConditionTypeEnum.IN, "concentratorID", concentorIDList));
		condtionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "alarmType", type));
		List<Alarm> alarmList= this.getDataSource(condtionList).getAllPageData();
		for(Alarm alarm:alarmList)
		{
			idList.add(String.valueOf(alarm.getId()));
		}
		return idList;
	}




 
}
