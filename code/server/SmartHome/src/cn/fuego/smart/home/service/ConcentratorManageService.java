/**   
* @Title: ConcentratorManageService.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-31 上午12:21:07 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import cn.fuego.smart.home.domain.Concentrator;

 /** 
 * @ClassName: ConcentratorManageService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-31 上午12:21:07 
 *  
 */
public interface ConcentratorManageService
{
	void online(Concentrator concentrator);
	void offline(Concentrator concentrator);

}
