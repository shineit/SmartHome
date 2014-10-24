/**   
* @Title: SensorManageService.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午10:58:38 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.smart.home.domain.Sensor;

 /** 
 * @ClassName: SensorManageService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午10:58:38 
 *  
 */
public interface SensorManageService
{
	AbstractDataSource<Sensor>  getSensorDataSource();

}
