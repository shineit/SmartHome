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

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.domain.Alarm;

/** 
 * @ClassName: AlarmManageService 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:16:42 
 *  
 */
public interface AlarmManageService extends MispCommonService<Alarm>
{
 	void deleteAlarmList(List<String> alarmIDList);
 	void clearAlarm(String id);
 
	
}
