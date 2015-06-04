/**   
* @Title: AlarmPushServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.down.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 下午11:28:04 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.PrivilegeAccessObjTypeEnum;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.smart.home.constant.AlarmPushTypeEnum;
import cn.fuego.smart.home.constant.DeviceKindEunm;
import cn.fuego.smart.home.constant.PushMessagTypeEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.AlarmType;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.domain.Organization;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AlarmTypeCache;
import cn.fuego.smart.home.service.cache.FuegoPushInfo;
import cn.fuego.smart.home.webservice.down.model.AlarmPushInfoJson;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;
import cn.fuego.smart.home.webservice.down.service.PushService;
import cn.fuego.smart.home.webservice.down.service.PushToolFactory;

 /** 
 * @ClassName: AlarmPushServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-3 下午11:28:04 
 *  
 */
public class PushServiceImpl implements PushService
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.down.service.AlarmPushService#pushAlarm(java.util.List)
	 */
	@Override
	public void pushAlarm(List<Alarm> alarmList)
	{
		 for(Alarm alarm : alarmList)
		 {
			 AlarmType type = AlarmTypeCache.getInstance().getAlarmType(alarm.getAlarmType());
			 if(null == type)
			 {
				 log.warn("can not find the alarm type,the type id "+alarm.getAlarmType());
				 continue;
			 }
			 if(AlarmPushTypeEnum.NO_PUSH.getIntValue() == type.getPushType())
			 {
				 log.warn("the type no need to push,the "+type);
				 continue;
			 }

			

			 //查询集中器关联用户，需要推送的用户
			 QueryCondition conditon = new QueryCondition(ConditionTypeEnum.EQUAL, UserConcentrator.attr_concentratorID,String.valueOf(alarm.getConcentratorID()));
			 
			 List<UserConcentrator> userConList = ServiceContext.getInstance().getConcentratorManageService().get(UserConcentrator.class, conditon);
			 Set<Integer> userIDSet = new HashSet<Integer>();
			 if(!ValidatorUtil.isEmpty(userConList))
			 {
				 for(UserConcentrator userCon : userConList)
				 {
					 userIDSet.add(userCon.getUserID());
				 }
			 }
			 else
			 {
				 log.warn("can not find relate user by concentrator id " + alarm.getConcentratorID());
			 }
			 //获取传感器中配置的用户
//			 FireSensor sensor= getFireSensor(alarm.getConcentratorID(), alarm.getObjID(), alarm.getObjID1(), alarm.getObjID2());
//			 if(!ValidatorUtil.isEmpty(sensor.getUserName()))
//			 {
//				 SystemUser user = (SystemUser) ServiceContext.getInstance().getUserManageService().get("userName", sensor.getUserName());
//				 if(null != user)
//				 {
//					 userIDSet.add(user.getUserID());
//				 }
//			 }
			 
			 
			 if(!ValidatorUtil.isEmpty(userIDSet))
			 {
				for(Integer userCon : userIDSet)
				{
						
					 FuegoPushInfo pushInfo = PushToolFactory.getInstance().getPushInfo(userCon);
					 
					 if(DeviceKindEunm.FIRE_CONCENTRATOR == ApplicationProtocol.getObjKindByID(alarm.getConcentratorID()))
					 {
						 log.info("the concentor is fire,so push fire alarm");
						 pushFireAlarm(pushInfo,userCon,type,alarm);
						 
						 
					 }	 
					 else
					 {
						 PushMessageJson json = new PushMessageJson();
						 String title=null;
						 title = PushMessagTypeEnum.ALRAM_MSG.getStrValue()+type.getTypeName();
						 json.setObjType(PushMessagTypeEnum.ALRAM_MSG.getIntValue());
							 
						 String content = "";//type.getTypeName();	 
 						 
						 json.setObj(alarm.getId());
						 
		 
 						 PushToolFactory.getInstance().getPushTool().pushNotification(pushInfo,title,content,json);
					 }
				 }
			 }
			 else
			 {
				 log.warn("can not get manage user,we will not push anythig for the alarm"  + alarm);
			 }
  
		 }
		
	}
	
	private void pushFireAlarm(FuegoPushInfo pushInfo,int userID,AlarmType type,Alarm alarm)
	{
		 Company company = ServiceContext.getInstance().getCompanyManageService().getCompanyByConcentorID(alarm.getConcentratorID());
		 if(null == company)
		 {
			 log.warn("the concentor have not been set to any company so no need to push"+alarm.getConcentratorID());
			 return;
		 }
		 if(MISPServiceContext.getInstance().getMISPPrivilegeManage().hasPrivilege(String.valueOf(userID), PrivilegeAccessObjTypeEnum.COMPANY.getObjectType(),String.valueOf(company.getCompanyID())))
		 {
			 log.warn("the user have no right for the company.the user id is "+userID + "the conmpany id is " + company.getCompanyID());
			 return;
		 }
		 

		 PushMessageJson json = new PushMessageJson();
		 String title=null;
		 title = PushMessagTypeEnum.ALRAM_MSG.getStrValue()+type.getTypeName();
		 json.setObjType(PushMessagTypeEnum.ALRAM_MSG.getIntValue());
		 
		 String content = "";//type.getTypeName();	 
		 AlarmPushInfoJson alarmPushInfo = new AlarmPushInfoJson();
		 alarmPushInfo.setCompanyID(company.getCompanyID());
		 alarmPushInfo.setPushType(type.getPushType());
		 alarmPushInfo.setAlarmKind(type.getKind());
		 json.setObj(alarmPushInfo);
 		 PushToolFactory.getInstance().getPushTool().pushNotification(pushInfo,title,content,json);
 		 
//		 FireSensor sensor= getFireSensor(alarm.getConcentratorID(), alarm.getObjID(), alarm.getObjID1(), alarm.getObjID2());
//		 if(!ValidatorUtil.isEmpty(sensor.getUserName()))
//		 {
//			 pushDutyUser(sensor.getUserName(),pushInfo,title,content,json);
//		 }

	}
	
//	private void pushDutyUser(String userID, FuegoPushInfo pushInfo,	String title, String content, PushMessageJson json)
//	{
//		Customer target = DaoContext.getInstance().getCustomerDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"userID",userID));
//		if(null!=target)
//		{
//			pushInfo.setUserID(userID);
//	 		PushToolFactory.getInstance().getPushTool().pushNotification(pushInfo,title,content,json);
//		}
//		
//	}




	private FireSensor getFireSensor(long concentratorID, long machineID,int loopID, int codeID)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();

	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"machineID",machineID));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"loopID",loopID));
	    conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"codeID",codeID));

		FireSensor sensor = DaoContext.getInstance().getFireSensorDao().getUniRecord(conditionList);
		return sensor;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.down.service.PushService#pushNews(java.util.List)
	 */
	@Override
	public void pushNews(List<News> newsList,int userID)
	{
		 SystemUser user = MISPServiceContext.getInstance().getUserService().get(userID);
		 if(null == user)
		 {
			 log.info("can not find the user");
			 return;
		 }
		 
		 QueryCondition condition = new QueryCondition(ConditionTypeEnum.LIKE_LEFT, "org_id",user.getOrg_id());
		 
		 List<Organization> orgList = MISPDaoContext.getInstance().getDao(Organization.class).getAll(condition);
		 
		 List<String> tags = new ArrayList<String>();
		 if(!ValidatorUtil.isEmpty(orgList))
		 {
			 for(Organization org : orgList)
			 {
				 tags.add(org.getOrg_id());
			 }
		 }
		 else
		 {
			 log.info("no tags need to push");
			 return;
		 }
		 
		 for(News e : newsList)
		 {
 			 
			 PushMessageJson json = new PushMessageJson();
			 
			 String title = PushMessagTypeEnum.NEWS_MSG.getStrValue()+e.getTitle();
			 String content = "";
			 json.setObjType(PushMessagTypeEnum.NEWS_MSG.getIntValue());
			 json.setObj(e.getNewsID());
			 PushToolFactory.getInstance().getPushTool().pushTags(tags,title, content, json);
		 
		 }
	}

}
