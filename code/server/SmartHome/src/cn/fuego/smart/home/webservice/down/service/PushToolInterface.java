/**   
* @Title: PushToolInterface.java 
* @Package cn.fuego.smart.home.webservice.down.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-8 上午10:07:21 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.down.service;

import java.util.List;

import cn.fuego.smart.home.service.cache.FuegoPushInfo;
import cn.fuego.smart.home.webservice.down.model.PushMessageJson;

 /** 
 * @ClassName: PushToolInterface 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-8 上午10:07:21 
 *  
 */
public interface PushToolInterface
{
 	void pushNotification(FuegoPushInfo pushInfo, String title,String content,PushMessageJson msgObj);
	void pushAll(String title,String content,PushMessageJson msgObj);
	public void pushTags(List<String> tags ,String title,String content,PushMessageJson msgObj);

	void pushMessage(FuegoPushInfo pushInfo,PushMessageJson msgObj);
}
