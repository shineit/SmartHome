/**   
* @Title: NewsManageSerivce.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午11:21:55 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.domain.FireSensor;

 /** 
 * @ClassName: NewsManageSerivce 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午11:21:55 
 *  
 */
public interface FireSensorManageService extends MispCommonService<FireSensor>
{

	public FireSensor getFireSensor(long concentratorID, int machineID,int loopID, int codeID);

	
	public void modifyOnConcentID(String oldConcentID, long newConcentID);
 
	
}
