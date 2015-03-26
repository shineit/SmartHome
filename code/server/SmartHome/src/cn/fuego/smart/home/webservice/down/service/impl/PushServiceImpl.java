/**   
* @Title: AlarmPushServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.down.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-3 下午11:28:04 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.service.impl;

import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.PrivilegeAccessObjTypeEnum;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.constant.AlarmPushTypeEnum;
import cn.fuego.smart.home.constant.DeviceKindEunm;
import cn.fuego.smart.home.constant.PushMessagTypeEnum;
import cn.fuego.smart.home.device.ApplicationProtocol;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.AlarmType;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.News;
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
				 break;
			 }
			 if(AlarmPushTypeEnum.NO_PUSH.getIntValue() == type.getPushType())
			 {
				 log.warn("the type no need to push,the "+type);
				 break;
			 }
			 QueryCondition conditon = new QueryCondition(ConditionTypeEnum.EQUAL, UserConcentrator.attr_concentratorID,String.valueOf(alarm.getConcentratorID()));
			 
			 List<UserConcentrator> userConList = ServiceContext.getInstance().getConcentratorManageService().get(UserConcentrator.class, conditon);
			
			 if(!ValidatorUtil.isEmpty(userConList))
			 {
				for(UserConcentrator userCon : userConList)
				{
						
					 FuegoPushInfo pushInfo = PushToolFactory.getInstance().getPushInfo(userCon.getUserID());
					 
					 if(DeviceKindEunm.FIRE_CONCENTRATOR == ApplicationProtocol.getObjKindByID(userCon.getConcentratorID()))
					 {
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
	
	private void pushFireAlarm(FuegoPushInfo pushInfo,UserConcentrator userCon,AlarmType type,Alarm alarm)
	{
		 Company company = ServiceContext.getInstance().getCompanyManageService().getCompanyByConcentorID(userCon.getConcentratorID());
		 if(null == company)
		 {
			 log.warn("the concentor have not been set to any company so no need to push"+userCon.getConcentratorID());
			 return;
		 }
		 if(MISPServiceContext.getInstance().getMISPPrivilegeManage().hasPrivilege(String.valueOf(userCon.getUserID()), PrivilegeAccessObjTypeEnum.COMPANY.getObjectType(),String.valueOf(company.getCompanyID())))
		 {
			 log.warn("the user have no right for the company.the user id is "+userCon.getUserID() + "the conmpany id is " + company.getCompanyID());
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

	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.down.service.PushService#pushNews(java.util.List)
	 */
	@Override
	public void pushNews(List<News> newsList)
	{
		 
		 for(News e : newsList)
		 {
 			 
			 PushMessageJson json = new PushMessageJson();
			 
			 String title = PushMessagTypeEnum.NEWS_MSG.getStrValue()+e.getTitle();
			 String content = "";
			 json.setObjType(PushMessagTypeEnum.NEWS_MSG.getIntValue());
			 json.setObj(e.getNewsID());
			 PushToolFactory.getInstance().getPushTool().pushAll(title, content, json);
		 
		 }
	}

}
