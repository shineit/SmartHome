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
import cn.fuego.smart.home.constant.AlarmTypeEnum;
import cn.fuego.smart.home.constant.PushMessagTypeEnum;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AppLoginCache;
import cn.fuego.smart.home.service.cache.FuegoPushInfo;
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
			 
			 QueryCondition conditon = new QueryCondition(ConditionTypeEnum.EQUAL, UserConcentrator.attr_concentratorID,String.valueOf(alarm.getConcenratorID()));
			 
			 List<UserConcentrator> userConList = ServiceContext.getInstance().getConcentratorManageService().get(UserConcentrator.class, conditon);
			 if(!ValidatorUtil.isEmpty(userConList))
			 {
				 for(UserConcentrator userCon : userConList)
				 {
					 FuegoPushInfo pushInfo = AppLoginCache.getPushInfo(userCon.getUserID());
					 
					 if(null != pushInfo)
					 {
						 PushMessageJson json = new PushMessageJson();
						 
						 String title = PushMessagTypeEnum.ALRAM_MSG.getStrValue();
						 String content = AlarmTypeEnum.getEnumByInt(alarm.getAlarmType()).getStrValue();
					 
 						 json.setObjType(PushMessagTypeEnum.ALRAM_MSG.getIntValue());
						 json.setObj(alarm.getId());
		 
 						 PushToolFactory.getInstance().getPushTool().pushNotification(pushInfo,title,content,json);
					 }
					 else
					 {
						 log.info("no need to push,the user have not been logined, user id is " + userCon.getUserID());
					 }
				 
				 }
			 }
			 else
			 {
				 log.warn("can not get manage user,we will not push anythig for the alarm"  + alarm);
 			 }
			 
		 }
		
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
			 
			 String title = PushMessagTypeEnum.NEWS_MSG.getStrValue();
			 String content = "";
			 json.setObjType(PushMessagTypeEnum.NEWS_MSG.getIntValue());
			 json.setObj(e.getNewsID());
			 PushToolFactory.getInstance().getPushTool().pushAll(title, content, json);
		 
		 }
	}

}
