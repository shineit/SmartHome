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
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.service.ConcentratorManageService;

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

	@Override
	public Concentrator getConcentByID(String concentID)
	{
		Concentrator concent= (Concentrator) DaoContext.getInstance().getConcentratorDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentID));
		return concent;
	}

	@Override
	public void modifyConcentInfo(Concentrator concent)
	{
		Concentrator oldConcent= (Concentrator) DaoContext.getInstance().getConcentratorDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID" ,String.valueOf(concent.getConcentratorID())));
		concent.setStatus(oldConcent.getStatus());
		concent.setAddr(oldConcent.getAddr());
		concent.setLocationNS(oldConcent.getLocationNS());
		concent.setLocationWE(oldConcent.getLocationWE());
		concent.setMark(oldConcent.getMark());
		DaoContext.getInstance().getConcentratorDao().update(concent);
	}

	@Override
	public void deleteConcentList(List<String> concentIDList)
	{
		QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, "concentratorID", concentIDList);	
		DaoContext.getInstance().getConcentratorDao().delete(condition);
		
	}

	@Override
	public Concentrator getDistributionInfo(List<QueryCondition> mapConidtionList)
	{
		Concentrator concent= (Concentrator) DaoContext.getInstance().getConcentratorDao().getAll(mapConidtionList);
		if(concent==null)
		{
			log.warn("get DistributionInfo from Concentrator failed");
			throw new MISPException(MISPErrorMessageConst.RESULT_NULL);
		}
		return concent;
	}




}
