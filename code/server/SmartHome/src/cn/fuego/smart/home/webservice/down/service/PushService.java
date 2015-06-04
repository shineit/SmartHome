/**   
* @Title: AlarmPushService.java 
* @Package cn.fuego.smart.home.webservice.to.client.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:19:25 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.service;

import java.util.List;

import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.News;

 /** 
 * @ClassName: AlarmPushService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:19:25 
 *  
 */
public interface PushService
{
	void pushAlarm(List<Alarm> alarmList);
	public void pushNews(List<News> newsList,int userID);
 

}
