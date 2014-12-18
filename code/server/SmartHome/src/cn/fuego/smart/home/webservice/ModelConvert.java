/**   
* @Title: ModelConvert.java 
* @Package cn.fuego.smart.home.webservice 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-13 下午12:26:51 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice;

import java.util.Date;

import cn.fuego.common.util.format.DateUtil;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.FireAlarmView;
import cn.fuego.smart.home.domain.HomeAlarmView;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.domain.UserMark;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;

 /** 
 * @ClassName: ModelConvert 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-13 下午12:26:51 
 *  
 */
public class ModelConvert
{
	
	public static AlarmJson AlarmToJson(Alarm alarm)
	{
		AlarmJson json = new AlarmJson();
		json.setId(alarm.getId());
		json.setObjType(alarm.getObjType());
		json.setObjID(alarm.getObjID());
 		json.setAlarmType(alarm.getAlarmType());
		json.setDataValue(alarm.getDataValue());
		json.setAlarmTime(DateUtil.getDateTime(alarm.getAlarmTime())); 
		json.setClearUser(alarm.getClearUser());
		json.setClearStatus(alarm.getClearStatus());
		json.setClearTime(DateUtil.getDateTime(alarm.getClearTime()));
		return json;
		
	}
	public static HomeAlarmJson  HomeAlarmToJson(HomeAlarmView homeAlarm)
	{
		HomeAlarmJson json = new HomeAlarmJson();
		json.setId(homeAlarm.getId());
		json.setConcentratorID(homeAlarm.getConcentratorID());
		json.setObjType(homeAlarm.getObjType());
		json.setObjID(homeAlarm.getObjID());
		json.setAlarmType(homeAlarm.getAlarmType());
		json.setAlarmTime(DateUtil.getDateTime(homeAlarm.getAlarmTime()));
		json.setClearStatus(homeAlarm.getClearStatus());
		json.setConcentDesp(homeAlarm.getConcentDesp());
		json.setSensorDesp(homeAlarm.getSensorDesp());
		json.setSensorType(homeAlarm.getSensorType());
		json.setSensorTypeName(homeAlarm.getSensorTypeName());
		return json;
		
	}
	public static FireAlarmJson  FireAlarmToJson(FireAlarmView fireAlarm)
	{
		FireAlarmJson json = new FireAlarmJson();
		json.setId(fireAlarm.getId());
		json.setConcentratorID(fireAlarm.getConcentratorID());
		json.setObjType(fireAlarm.getObjType());
		json.setObjID(fireAlarm.getObjID());
		json.setAlarmType(fireAlarm.getAlarmType());
		json.setAlarmTime(DateUtil.getDateTime(fireAlarm.getAlarmTime()));
		json.setClearStatus(fireAlarm.getClearStatus());
		json.setConcentDesp(fireAlarm.getConcentDesp());
		json.setMachineID(fireAlarm.getMachineID());
		json.setLoopID(fireAlarm.getLoopID());
		json.setCodeID(fireAlarm.getCodeID());
		return json;
		
	}	
	public static ServiceOrder jsonToServiceOrder(ServiceOrderJson json)
	{
		if(null == json)
		{
			return null;
		}
		ServiceOrder order = new ServiceOrder();
		order.setOrderID(json.getOrderID());
		order.setOrderName(json.getOrderName());
		order.setOrderType(json.getOrderType());
		order.setContent(json.getContent());
		order.setCreator(json.getCreator());
		order.setCreateTime(new Date(json.getCreateTime()));
		order.setContactName(json.getContactName());
		order.setContactAddr(json.getContactAddr());
		order.setPhoneNum(json.getPhoneNum());
		order.setOrderStatus(json.getOrderStatus());
		order.setHandler(json.getHandler());
		order.setHandleResult(json.getHandleResult());
		order.setHandleTime(new Date(json.getHandleTime()));
		
		return order;
	}
	
	
	public static HomeSensorJson homeSensorToJson(HomeSensor sensor)
	{
		HomeSensorJson json = new HomeSensorJson();
		json.setId(sensor.getId());
		json.setConcentratorID(sensor.getConcentratorID());
		json.setSensorID(sensor.getSensorID());
		json.setChannelID(sensor.getChannelID());
		json.setSensorKind(sensor.getSensorKind());
		json.setSensorType(sensor.getSensorType());
		json.setSensorTypeName(sensor.getSensorTypeName());
		json.setStatus(sensor.getStatus());
		json.setWarnValue(sensor.getWarnValue());
		json.setErrorValue(sensor.getErrorValue());
		json.setGroupID(sensor.getGroupID());
		json.setCtrGroupID(sensor.getCtrGroupID());
		json.setDescriptions(sensor.getDescription());
		json.setMark(sensor.getMark());
		return json;
 
	}
	
	public static HomeSensor jsonToHomeSensor(HomeSensorJson json)
	{
		HomeSensor homeSensor = new HomeSensor();
		homeSensor.setId(json.getId());
		homeSensor.setConcentratorID(json.getConcentratorID());
		homeSensor.setSensorID(json.getSensorID());
		homeSensor.setChannelID(json.getChannelID());
		homeSensor.setSensorKind(json.getSensorKind());
		homeSensor.setSensorType(json.getSensorType());
		homeSensor.setSensorTypeName(json.getSensorTypeName());
		homeSensor.setStatus(json.getStatus());
		homeSensor.setWarnValue(json.getWarnValue());
		homeSensor.setErrorValue(json.getErrorValue());
		homeSensor.setGroupID(json.getGroupID());
		homeSensor.setCtrGroupID(json.getCtrGroupID());
		homeSensor.setDescription(json.getDescriptions());
		homeSensor.setMark(json.getMark());
		return homeSensor;
 
	}
	
	public static NewsJson  newsToJson(News news)
	{
		NewsJson json = new NewsJson();
		json.setNewsID(news.getNewsID());
		json.setTitle(news.getTitle());
		json.setAuthor(news.getAuthor());
		json.setContent(news.getContent());
		json.setDate(DateUtil.getDateTime(news.getDate()));
		return json;
 
	}
	public static UserMarkJson  markToJson(UserMark mark)
	{
		UserMarkJson json = new UserMarkJson();
        json.setMark(mark.getMark());
        json.setUserID(mark.getUserID());
     
		return json;
 
	}	
    
    public static UserMark jsonToMark(UserMarkJson markJson)
    {
    	UserMark userMark = new UserMark();
    	userMark.setUserID(markJson.getUserID());
    	userMark.setMark(markJson.getMark());
    	return userMark;
    }
}