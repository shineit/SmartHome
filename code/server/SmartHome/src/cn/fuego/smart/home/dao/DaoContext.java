package cn.fuego.smart.home.dao;

import cn.fuego.common.dao.Dao;
import cn.fuego.common.dao.impl.AbstractDao;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.CheckItem;
import cn.fuego.smart.home.domain.CheckLog;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.Customer;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.domain.SensorType;
import cn.fuego.smart.home.domain.ServiceOrder;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.domain.UserMark;


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
 
 
	private Dao<HomeSensor> sensorDao = null;
	private Dao<News> newsDao = null;
	private Dao<ServiceOrder> serviceOrderDao = null;
	private Dao<Concentrator> concentratorDao = null;
	private Dao<UserMark> userMarkDao = null;
    
	private Dao<Alarm> alarmDao =null;
	private Dao<UserConcentrator> userConcentratorDao = null;
	private Dao<Customer> customerDao=null;
	private Dao<SensorType> sensorTypeDao = null;
	
	private Dao<Company> companyDao = null;
	
	private Dao<CheckItem> checkItemDao =null;
	private Dao<CheckLog>  checkLogDao =null;
	
	private Dao<FireSensor> fireSensorDao=null;

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
	public Dao getDao(Class clazz)
	{
		return new AbstractDao(clazz);
	}
	public Dao<News> getNewsDao()
	{
		if (null == newsDao)
		{
			newsDao = new AbstractDao<News>(News.class);
		}
		return newsDao;
	}
 
	public Dao<ServiceOrder> getServiceOrderDao()
	{
		if (null == serviceOrderDao)
		{
			serviceOrderDao = new AbstractDao<ServiceOrder>(ServiceOrder.class);
		}
		return serviceOrderDao;
	}
	public Dao<Concentrator> getConcentratorDao()
	{
		if (null == concentratorDao)
		{
			concentratorDao = new AbstractDao<Concentrator>(Concentrator.class);
		}
		return concentratorDao;
	}
	public Dao<HomeSensor> getSensorDao()
	{
		if (null == sensorDao)
		{
			sensorDao = new AbstractDao<HomeSensor>(HomeSensor.class);
		}
		return sensorDao;
	}
  
	public Dao<UserMark> getUserMarkDao()
	{
		if (null == userMarkDao)
		{
			userMarkDao = new AbstractDao<UserMark>(UserMark.class);
		}
		return userMarkDao;
	}
	
 
	public Dao<Alarm> getAlarmDao()
	{
		if (null == alarmDao)
		{
			alarmDao = new AbstractDao<Alarm>(Alarm.class);
		}
		return alarmDao;
	}

	
	public Dao<UserConcentrator> getUserConcentratorDao()
	{
		if (null == userConcentratorDao)
		{
			userConcentratorDao = new AbstractDao<UserConcentrator>(UserConcentrator.class);
		}
		return userConcentratorDao;
	}	
	public Dao<Customer>  getCustomerDao()
	{
		if (null == customerDao)
		{
			customerDao = new AbstractDao<Customer>(Customer.class);
		}
		return customerDao;
	}
	public Dao<SensorType>  getSensorTypeDao()
	{
		if (null == sensorTypeDao)
		{
			sensorTypeDao = new AbstractDao<SensorType>(SensorType.class);
		}
		return sensorTypeDao;
	}

	public Dao<Company>  getCompanyDao()
	{
		if (null == companyDao)
		{
			companyDao = new AbstractDao<Company>(Company.class);
		}
		return companyDao;
	}
	
	public Dao<CheckItem> getCheckItemDao()
	{
		if (null == checkItemDao)
		{
			checkItemDao = new AbstractDao<CheckItem>(CheckItem.class);
		}
		return checkItemDao;
	}


	public Dao<CheckLog> getCheckLogDao()
	{
		if (null == checkLogDao)
		{
			checkLogDao = new AbstractDao<CheckLog>(CheckLog.class);
		}
		return checkLogDao;
	}

	public Dao<FireSensor> getFireSensorDao()
	{
		if (null == fireSensorDao)
		{
			fireSensorDao = new AbstractDao<FireSensor>(FireSensor.class);
		}
		return fireSensorDao;
	}

	
}
