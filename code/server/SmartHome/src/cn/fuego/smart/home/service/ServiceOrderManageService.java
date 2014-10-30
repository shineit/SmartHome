/**   
* @Title: ServiceOrderManageService.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午3:04:40 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.smart.home.domain.ServiceOrder;

 /** 
 * @ClassName: ServiceOrderManageService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午3:04:40 
 *  
 */
public interface ServiceOrderManageService
{
	AbstractDataSource<ServiceOrder>  getNewsDataSource();
	
	void create(ServiceOrder order);
    void handle(int orderID,String handler,String handleResult);
}
