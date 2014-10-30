package cn.fuego.smart.home.service;

import cn.fuego.smart.home.service.impl.NewsManageServiceImpl;
import cn.fuego.smart.home.service.impl.SensorManageServiceImpl;
import cn.fuego.smart.home.service.impl.ServiceOrderManageServiceImpl;

public class ServiceContext
{
	private static ServiceContext instance;

	private NewsManageService newsManageService  = null;
	private SensorManageService sensorManageService  = null;
	private ServiceOrderManageService serviceOrderManageService  = null;

	private ConcentratorManageService concentratorManageService = null;
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
			newsManageService = new NewsManageServiceImpl();
		}
		return newsManageService;
	}
 
	public synchronized SensorManageService getSensorManageService()
	{
		if (null == sensorManageService)
		{
			sensorManageService =  new SensorManageServiceImpl();
		}
		return sensorManageService;
	}
	public synchronized ServiceOrderManageService getServiceOrderManageService()
	{
		if (null == serviceOrderManageService)
		{
			serviceOrderManageService =  new ServiceOrderManageServiceImpl();
		}
		return serviceOrderManageService;
	}
	
	public synchronized ConcentratorManageService getConcentratorManageService()
	{
		if (null == concentratorManageService)
		{
			concentratorManageService =  null;//new ServiceOrderManageServiceImpl();
		}
		return concentratorManageService;
	}

}
