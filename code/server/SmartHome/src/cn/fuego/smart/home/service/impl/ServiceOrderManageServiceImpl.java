/**   
* @Title: ServiceOrderManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午3:05:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.service.ServiceOrderManageService;

 /** 
 * @ClassName: ServiceOrderManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午3:05:35 
 *  
 */
public class ServiceOrderManageServiceImpl implements ServiceOrderManageService
{

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#getNewsDataSource()
	 */
	@Override
	public AbstractDataSource<ServiceOrder> getNewsDataSource()
	{
		AbstractDataSource<ServiceOrder> datasource = new DataBaseSourceImpl<ServiceOrder>(ServiceOrder.class);
		
		return datasource;
	}

}
