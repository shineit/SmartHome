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
import cn.fuego.misp.domain.SystemMenu;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.smart.home.domain.Advertisement;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.CheckItem;
import cn.fuego.smart.home.domain.CheckLog;
import cn.fuego.smart.home.domain.ClientVersion;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.Customer;
import cn.fuego.smart.home.domain.FireAlarmView;
import cn.fuego.smart.home.domain.HomeAlarmView;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.domain.Knowledge;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.domain.Product;
import cn.fuego.smart.home.domain.SensorPlan;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.domain.UserMark;
import cn.fuego.smart.home.webservice.up.model.base.AdvertisementJson;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.CheckItemJson;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;
import cn.fuego.smart.home.webservice.up.model.base.ClientVersionJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.ConcentratorJson;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.model.base.KnowledgeJson;
import cn.fuego.smart.home.webservice.up.model.base.MenuJson;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.model.base.ProductJson;
import cn.fuego.smart.home.webservice.up.model.base.SensorPlanJson;
import cn.fuego.smart.home.webservice.up.model.base.ServiceOrderJson;
import cn.fuego.smart.home.webservice.up.model.base.UserJson;
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
		json.setAlarmTypeName(fireAlarm.getAlarmTypeName());
		json.setAlarmTime(DateUtil.getDateTime(fireAlarm.getAlarmTime()));
		json.setClearStatus(fireAlarm.getClearStatus());
		json.setClearTime(DateUtil.getDateTime(fireAlarm.getClearTime()));
		json.setStatus(fireAlarm.getStatus());
		json.setConcentDesp(fireAlarm.getConcentDesp());
		
		json.setMachineID(fireAlarm.getMachineID());
		json.setLoopID(fireAlarm.getLoopID());
		json.setCodeID(fireAlarm.getCodeID());
		json.setLocationDesp(fireAlarm.getLocationDesp());
		json.setLocationX(fireAlarm.getLocationX());
		json.setLocationY(fireAlarm.getLocationY());
		json.setPlanID(fireAlarm.getPlanID());
		json.setSensorTypeName(fireAlarm.getSensorTypeName());
		json.setAlarmKind(fireAlarm.getKind());
		
		json.setContacts(fireAlarm.getContacts());
		json.setContactPhone(fireAlarm.getContactPhone());
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
	public static ServiceOrderJson ServiceOrderToJson(ServiceOrder order)
	{
		ServiceOrderJson json= new ServiceOrderJson();
		json.setOrderID(order.getOrderID());
		json.setOrderName(order.getOrderName());
		json.setOrderType(order.getOrderType());
		json.setContent(order.getContent());
		json.setCreator(order.getCreator());
		json.setCreateTime(DateUtil.getDateTime(order.getCreateTime()));
		json.setContactName(order.getContactName());
		json.setPhoneNum(order.getPhoneNum());
		json.setContactAddr(order.getContactAddr());
		json.setOrderStatus(order.getOrderStatus());
		json.setHandler(order.getHandler());
		json.setHandleResult(order.getHandleResult());
		json.setHandleTime(DateUtil.getDateTime(order.getHandleTime()));
		
		return json;
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
		
		//新增字段，用于解决联动问题
		json.setCtrSensorID(sensor.getCtrSensorID());
		json.setCtrChannelID(sensor.getCtrChannelID());
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
		//新增字段，用于设置传感器联动控制器
		homeSensor.setCtrSensorID(json.getCtrSensorID());
		homeSensor.setCtrChannelID(json.getCtrChannelID());
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
    
	public static MenuJson menuToJson(SystemMenu menu)
	{
		MenuJson json = new MenuJson();
		json.setId(menu.getId());
		json.setName(menu.getName());
		return json;
	}
	public static SystemMenu jsonToMenu(MenuJson menuJson)
	{
		SystemMenu menu = new SystemMenu();
		menu.setId(menuJson.getId());
		menu.setName(menuJson.getName());
		return menu;
	}
	
	public static UserJson userToJson(SystemUser user)
	{
		UserJson json = new UserJson();
		json.setUserID(user.getUserID());
		json.setUserName(user.getUserName());
		json.setRole(user.getRole());
		return json;
	}
	public static SystemUser jsonToUser(UserJson userJson)
	{
		SystemUser user = new SystemUser();
		user.setUserID(userJson.getUserID());
		user.setUserName(userJson.getUserName());
		user.setRole(userJson.getRole());
		return user;
	}	
	public static CustomerJson customerToJson(Customer customer)
	{
		CustomerJson json = new CustomerJson();
		json.setUserID(customer.getUserID());
		json.setCustomerName(customer.getCustomerName());
		json.setPhone(customer.getPhone());
		json.setEmail(customer.getEmail());
		json.setAddr(customer.getAddr());
		json.setStatus(customer.getStatus());
		return json;
	}
	public static Customer jsonToCustomer(CustomerJson customerJson)
	{
		Customer customer = new Customer();	
		customer.setUserID(customerJson.getUserID());
		customer.setCustomerName(customerJson.getCustomerName());
		customer.setPhone(customerJson.getPhone());
		customer.setEmail(customerJson.getEmail());
		customer.setAddr(customerJson.getAddr());
		customer.setStatus(customerJson.getStatus());
		return customer;
	}
	public static ClientVersionJson versionToJson(ClientVersion clientVersion)
	{
		ClientVersionJson json = new ClientVersionJson();
		json.setVersionID(clientVersion.getVersionID());
		json.setVersionName(clientVersion.getVersionName());
		json.setVersionCode(clientVersion.getVersionCode());
		json.setVersionStatus(clientVersion.getVersionStatus());
		json.setApkName(clientVersion.getApkName());
		json.setApkURL(clientVersion.getApkURL());
		json.setAppName(clientVersion.getAppName());
		json.setClientType(clientVersion.getClientType());
		json.setCompanyID(clientVersion.getCompanyID());
		json.setQrCode(clientVersion.getQrCode());	
		return json;
	}
	public static ConcentratorJson concentratorToJson(Concentrator concentrator)
	{
		ConcentratorJson json = new ConcentratorJson();
		json.setConcentratorID(concentrator.getConcentratorID());
		json.setStatus(concentrator.getStatus());
		json.setName(concentrator.getName());
		json.setDescription(concentrator.getDescription());
		json.setAddr(concentrator.getAddr());
		json.setIpAddr(concentrator.getIpAddr());
		json.setLocationNS(concentrator.getLocationNS());
		json.setLocationWE(concentrator.getLocationWE());
		
		return json;
	}
	public static Concentrator jsonToConcentrator(ConcentratorJson concentratorJson)
	{
		Concentrator concent = new Concentrator();
		//与APP页面信息保持一致，否则数据可能会被赋空
		concent.setConcentratorID(concentratorJson.getConcentratorID());
		concent.setName(concentratorJson.getName());
		concent.setDescription(concentratorJson.getDescription());
		return concent;
	}
	
	public static CompanyJson  companyToJson(Company company)
	{
		CompanyJson json = new CompanyJson();
		json.setApplyName(company.getApplyName());
		json.setBuildingArea(company.getBuildingArea());
		json.setCompanyAddr(company.getCompanyAddr());
		json.setCompanyID(company.getCompanyID());
		json.setCompanyName(company.getCompanyName());
		json.setCompanyPhone(company.getCompanyPhone());
		json.setCompanyType(company.getCompanyType());
		json.setDutyPhone(company.getDutyPhone());
		json.setFireDuty(company.getFireDuty());
		json.setFireManager(company.getFireManager());
		json.setFireRisk(company.getFireRisk());
		json.setLegalOfficer(company.getLegalOfficer());
		json.setManagerPhone(company.getManagerPhone());
		json.setOfficerPhone(company.getOfficerPhone());
		
		return json;
		
	}
	/**
	 * 巡检项目传送json
	 * @param checkItem
	 * @return
	 */
	public static CheckItemJson  checkItemToJson(CheckItem checkItem)
	{
		CheckItemJson json = new CheckItemJson();
		json.setItemID(checkItem.getItemID());
		json.setItemName(checkItem.getItemName());
		json.setItemSys(checkItem.getItemSys());
		
		return json;
		
	}
	/**
	 * 巡检日志传送json
	 * @param checkLog
	 * @return
	 */
	public static CheckLogJson  checkLogToJson(CheckLog checkLog)
	{
		CheckLogJson json = new CheckLogJson();
		json.setLogID(checkLog.getLogID());
		json.setCompanyID(checkLog.getCompanyID());
		json.setCompanyName(checkLog.getCompanyName());
		json.setCheckItem(checkLog.getCheckItem());
		json.setCheckSys(checkLog.getCheckSys());
		json.setCheckResult(checkLog.getCheckResult());
		json.setAbnormalDesp(checkLog.getAbnormalDesp());
		json.setAbnormalPic(checkLog.getAbnormalPic());
		json.setChecker(checkLog.getChecker());
		json.setCheckTime(DateUtil.getDateTime(checkLog.getCheckTime()));
		json.setHandler(checkLog.getHandler());
		json.setHandleResult(checkLog.getHandleResult());
		json.setHandleTime(DateUtil.getDateTime(checkLog.getHandleTime()));
		json.setStatus(checkLog.getStatus());
		
		return json;
		
	}
	/**
	 * 巡检日志提交
	 * @param checkLogJson
	 * @return
	 */
	public static CheckLog jsonToCheckLog(CheckLogJson checkLogJson)
	{
		CheckLog checkLog = new CheckLog();
		checkLog.setCompanyID(checkLogJson.getCompanyID());
		checkLog.setCompanyName(checkLogJson.getCompanyName());
		checkLog.setCheckItem(checkLogJson.getCheckItem());
		checkLog.setCheckResult(checkLogJson.getCheckResult());
		checkLog.setCheckSys(checkLogJson.getCheckSys());
		checkLog.setAbnormalDesp(checkLogJson.getAbnormalDesp());
		checkLog.setAbnormalPic(checkLogJson.getAbnormalPic());
		checkLog.setChecker(checkLogJson.getChecker());
		//生成的时间为1970
		//checkLog.setCheckTime(new Date(checkLogJson.getCheckTime()));
		checkLog.setHandler(checkLogJson.getHandler());
		checkLog.setHandleResult(checkLogJson.getHandleResult());
		checkLog.setHandleTime(new Date(checkLogJson.getHandleTime()));
		checkLog.setStatus(checkLogJson.getStatus());
		
		return checkLog;
	}
	
	/**
	 * 
	 * @param checkLogJson
	 * @return
	 */
	public static ProductJson productToJson(Product product)
	{
		ProductJson json = new ProductJson();
		json.setProductID(product.getProductID()); 
		json.setName(product.getName());
		json.setDesp(product.getDesp());
		json.setPrice(product.getPrice());
 		json.setPicLabel(product.getPicLabel());
		json.setPicDetail1(product.getPicDetail1());
		json.setPicDetail2(product.getPicDetail2());
		json.setPicDetail3(product.getPicDetail3());
		json.setType(product.getType());
		
		return json;
	}
	
	/**
	 * 
	 * @param checkLogJson
	 * @return
	 */
	public static KnowledgeJson knowledgeToJson(Knowledge knowledge)
	{
		KnowledgeJson json = new KnowledgeJson();
 
		json.setKnowledgeID(knowledge.getKnowledgeID());
		json.setTitle(knowledge.getTitle());
		json.setContent(knowledge.getContent());
		json.setKnowledgeType(knowledge.getKnowledgeType());
		json.setKnowledgeKind(knowledge.getKnowledgeKind());
		return json;
	}
	/**
	 * @param plan
	 * @return
	 */
	public static SensorPlanJson planToJson(SensorPlan plan)
	{
		SensorPlanJson json = new SensorPlanJson();
		 
		json.setPlanID(plan.getPlanID());
		json.setName(plan.getName());
		json.setBuildingID(plan.getBuildingID());
		json.setDesp(plan.getDesp());
		json.setFloor(plan.getFloor());
		json.setPicPath(plan.getPicPath());
		return json;
	}
	/**
	 * @param ad
	 * @return
	 */
	public static AdvertisementJson adToJson(Advertisement ad)
	{
		AdvertisementJson json = new AdvertisementJson();
		json.setAdID(ad.getAdID());
		json.setAdImg(ad.getAdImg());
		json.setAdInfo(ad.getAdInfo());
		json.setAdName(ad.getAdName());
		json.setAdURL(ad.getAdURL());
		return json;
	}
}