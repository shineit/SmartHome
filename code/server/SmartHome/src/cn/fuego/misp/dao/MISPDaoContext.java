package cn.fuego.misp.dao;

import cn.fuego.common.dao.Dao;
import cn.fuego.common.dao.impl.AbstractDao;
import cn.fuego.misp.domain.OperLog;
import cn.fuego.misp.domain.Privilege;
import cn.fuego.misp.domain.SystemIDType;
import cn.fuego.misp.domain.SystemMenu;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.smart.home.domain.ClientVersion;


/**
 * 
* @ClassName: DaoContext 
* @Description: TODO
* @author Nan Bowen
* @date 2014-3-23 下午11:27:41 
* 
 */
public class MISPDaoContext
{
	private static MISPDaoContext instance;
 
	private Dao<SystemMenu> systemMenuDao = null;
 
  
	private Dao<SystemIDType> systemIDTypeDao = null;
 
	private Dao<SystemUser> systemUserDao = null;
	private Dao<OperLog> operLogDao = null;

	
	private Dao<Privilege> privilegeDao = null;

	private Dao<ClientVersion> clientVersionDao = null;
	private MISPDaoContext()
	{

	}

	public static synchronized MISPDaoContext getInstance()
	{
		if (null == instance)
		{
			instance = new MISPDaoContext();
		}
		return instance;
	}
 
	
	public synchronized Dao<SystemUser> getSystemUserDao() 
	{
		if (null == systemUserDao)
		{
			systemUserDao = new AbstractDao<SystemUser>(SystemUser.class);
		}
		return systemUserDao;
	}
	
	public synchronized Dao<SystemMenu> getSystemMenuDao() 
	{
		if (null == systemMenuDao)
		{
			systemMenuDao = new AbstractDao<SystemMenu>(SystemMenu.class);
		}
		return systemMenuDao;
	}
	
 
	
	public synchronized Dao<SystemIDType> getSystemIDTypeDao() 
	{
		if (null == systemIDTypeDao)
		{
			systemIDTypeDao = new AbstractDao<SystemIDType>(SystemIDType.class);
		}
		return systemIDTypeDao;
	}
	public synchronized Dao<OperLog> getOperLogDao() 
	{
		if (null == operLogDao)
		{
			operLogDao = new AbstractDao<OperLog>(OperLog.class);
		}
		return operLogDao;
	}
	
	public synchronized Dao<Privilege> getPrivilegeDao() 
	{
		if (null == privilegeDao)
		{
			privilegeDao = new AbstractDao<Privilege>(Privilege.class);
		}
		return privilegeDao;
	}
	
	public synchronized Dao<ClientVersion> getClientVersionDao() 
	{
		if (null == clientVersionDao)
		{
			clientVersionDao = new AbstractDao<ClientVersion>(ClientVersion.class);
		}
		return clientVersionDao;
	}	
 
	 
}
