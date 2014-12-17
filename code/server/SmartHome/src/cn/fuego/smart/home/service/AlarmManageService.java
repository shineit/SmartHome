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
import cn.fuego.smart.home.domain.HomeAlarmView;

/** 
 * @ClassName: AlarmManageService 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:16:42 
 *  
 */
public interface AlarmManageService extends MispCommonService<Alarm>
{
	List<HomeAlarmView>  getAlarmOfUser(int userID);
 	void manualClear(int userID,int id);
    void autoClear(int id);
	HomeAlarmView getHomeAlarmByID(String alarmID);
	
 
	
}
