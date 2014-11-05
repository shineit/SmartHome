/**   
* @Title: ServiceOrderManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午3:05:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.misp.service.IDCreateService;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.smart.home.constant.ServiceOrderStatusEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.service.ConcentratorManageService;
import cn.fuego.smart.home.service.ServiceOrderManageService;

 /** 
 * @ClassName: ServiceOrderManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午3:05:35 
 *  
 */
public class ConcentratorManageServiceImpl implements ConcentratorManageService
{

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.ServiceOrderManageService#getNewsDataSource()
	 */
	private Log log = LogFactory.getLog(ConcentratorManageServiceImpl.class);

	@Override
	public AbstractDataSource<Concentrator> getConcentDataSource(List<QueryCondition> conidtionList)
	{
		AbstractDataSource<Concentrator> datasource = new DataBaseSourceImpl<Concentrator>(Concentrator.class,conidtionList);
		
		return datasource;
	}	

	@Override
	public void online(Concentrator concentrator)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void offline(Concentrator concentrator)
	{
		// TODO Auto-generated method stub
		
	}







}
