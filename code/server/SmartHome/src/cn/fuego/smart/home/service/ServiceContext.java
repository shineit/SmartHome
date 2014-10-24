package cn.fuego.smart.home.service;

import cn.fuego.misp.service.MISPUserService;
import cn.fuego.misp.service.impl.MISPOperLogServiceImpl;
import cn.fuego.misp.service.impl.MISPUserServiceImpl;

public class ServiceContext
{
	private static ServiceContext instance;

	private NewsManageService newsManageService  = null;
	 
	private ServiceContext()
	{

	}

	public static synchronized ServiceContext getInstance()
	{
		if (null == instance)
		{
			instance = new ServiceContext();
		}
		return instance;
	}
	public synchronized NewsManageService getNewsManageService()
	{
		if (null == newsManageService)
		{
			newsManageService = null;//new NewsManageServiceImpl();
		}
		return newsManageService;
	}
 

}
