/**   
* @Title: AlarmManageService.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Aether
* @date 2014-11-7 上午10:16:42 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.FireAlarmView;
import cn.fuego.smart.home.domain.HomeAlarmView;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;

/** 
 * @ClassName: AlarmManageService 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:16:42 
 *  
 */
public interface AlarmManageService extends MispCommonService<Alarm>
{
	List<HomeAlarmView>  getAlarmOfUser(int userID,int startNum,int pageSize, List<AttributeJson> attrList);
 	void manualClear(int userID,int id);
    void autoClear(int id);
	HomeAlarmView getHomeAlarmByID(String alarmID);
	
	void manualClearList(int userID, List<String> alarmIDList);
	
	//获取企业端告警信息列表通用方法，支持告警、状态、历史告警
	List<FireAlarmView> getFireAlarmByCompany(String companyID, int startNum,int pageSize, List<AttributeJson> filterList);

}
