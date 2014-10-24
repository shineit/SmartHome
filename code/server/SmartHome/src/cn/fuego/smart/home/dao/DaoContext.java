package cn.fuego.smart.home.dao;

import cn.fuego.common.dao.Dao;
import cn.fuego.common.dao.impl.CommonDaoImpl;
import cn.fuego.misp.domain.OperLog;
import cn.fuego.misp.domain.SystemIDType;
import cn.fuego.misp.domain.SystemMenu;
import cn.fuego.misp.domain.SystemUser;


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
