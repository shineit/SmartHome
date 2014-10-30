package cn.fuego.smart.home.dao;

import cn.fuego.common.dao.Dao;
import cn.fuego.common.dao.impl.CommonDaoImpl;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.domain.ServiceOrder;


/**
 * 
* @ClassName: DaoContext 
* @Description: TODO
* @author Nan Bowen
* @date 2014-3-23 下午11:27:41 
* 
 */
public class DaoContext
{
	private static DaoContext instance;
 
 
	private Dao sensorDao = null;
	private Dao newsDao = null;
	private Dao serviceOrderDao = null;
	private Dao concentratorDao = null;


	private DaoContext()
	{

	}

	public static synchronized DaoContext getInstance()
	{
		if (null == instance)
		{
			instance = new DaoContext();
		}
		return instance;
	}
	
	public Dao getNewsDao()
	{
		if (null == newsDao)
		{
			newsDao = getDaoInstanceByClass(News.class);
		}
		return newsDao;
	}
 
	public Dao getServiceOrderDao()
	{
		if (null == serviceOrderDao)
		{
			serviceOrderDao = getDaoInstanceByClass(ServiceOrder.class);
		}
		return serviceOrderDao;
	}
	public Dao getConcentratorDao()
	{
		if (null == concentratorDao)
		{
			concentratorDao = getDaoInstanceByClass(Concentrator.class);
		}
		return concentratorDao;
	}
	public Dao getSensorDao()
	{
		if (null == sensorDao)
		{
			sensorDao = getDaoInstanceByClass(SystemUser.class);
		}
		return sensorDao;
	}
 
	 
	
	private Dao getDaoInstanceByClass(Class clazz)
	{
		Dao dao;
 
		dao = new CommonDaoImpl(clazz); 
		return dao;
	}
	 
}
