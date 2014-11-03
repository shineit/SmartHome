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
import cn.fuego.smart.home.web.model.OrderFilterModel;

 /** 
 * @ClassName: ServiceOrderManageService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午3:04:40 
 *  
 */
public interface ServiceOrderManageService
{
	AbstractDataSource<ServiceOrder>  getOrderDataSource(OrderFilterModel orderFilter);
	
	void create(ServiceOrder order);
    void handle(String orderID,String handler,String handleResult);

	ServiceOrder getOrderById(String orderID);
	
	
}
