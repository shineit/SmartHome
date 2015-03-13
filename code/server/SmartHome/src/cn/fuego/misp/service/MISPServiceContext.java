package cn.fuego.misp.service;

import java.util.HashMap;
import java.util.Map;

import cn.fuego.misp.service.impl.MISPOperLogServiceImpl;
import cn.fuego.misp.service.impl.MISPPrivilegeManageImpl;
import cn.fuego.misp.service.impl.id.CommonIDImpl;
import cn.fuego.smart.home.service.impl.UserManageServiceImpl;

public class MISPServiceContext
{
	private static MISPServiceContext instance;

	private MISPUserService userService = null;
	
 
	private MISPOperLogService operLogService = null;

	private MISPPrivilegeManage privilegeManage = null;
	
	private Map<String,IDCreateService> idCreateServiceMap = new HashMap<String,IDCreateService>();


	

	private MISPServiceContext()
	{

	}

	public synchronized IDCreateService getIDCreateService(String IDName) 
	{
		
		if(null == idCreateServiceMap.get(IDName))
		{
			idCreateServiceMap.put(IDName, new CommonIDImpl(IDName));
		}
		
		return idCreateServiceMap.get(IDName);
	}
	
	public static synchronized MISPServiceContext getInstance()
	{
		if (null == instance)
		{
			instance = new MISPServiceContext();
		}
		return instance;
	}

	public synchronized MISPUserService getUserService()
	{
		if (null == userService)
		{
			userService = new UserManageServiceImpl();
		}
		return userService;
	}
	

	public synchronized MISPOperLogService getMISPOperLogService()
	{
		if (null == operLogService)
		{
			operLogService = new MISPOperLogServiceImpl();
		}
		return operLogService;
	}
	
	public synchronized MISPPrivilegeManage getMISPPrivilegeManage()
	{
		if (null == privilegeManage)
		{
			privilegeManage = new MISPPrivilegeManageImpl();
		}
		return privilegeManage;
	}

}
