package cn.fuego.smart.home.cache;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.dao.SharedPreUtil;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.smart.home.ui.setting.model.ConfigInfo;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;
import cn.fuego.smart.home.webservice.up.model.base.UserJson;

public class AppCache
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	public static String PAY_NOTIFY_URL = MemoryCache.getWebContextUrl() + "/index.php/AlipayNotify/GetNotify";

	private UserJson user;
	private CustomerJson customer;
	private CompanyJson company;
	private static AppCache instance;
	private  int company_id = 1;
 
	
	public static final String USER_CACHE="user";
	public static final String CUSTOMER_CACHE="customer";
	public static final String TOKEN_CACHE="token";
	//个人配置信息
	public static final String CONFIG_INFO="configInfo";
	
	
	private boolean firstStarted = true;
	private boolean started = false;

	
	
 

	public boolean isFirstStarted()
	{
		String startStr = (String) SharedPreUtil.getInstance().get("isFirstStarted");
		if(!ValidatorUtil.isEmpty(startStr))
		{
			firstStarted = Boolean.valueOf(startStr);
		}
		
		return firstStarted;
	}

	public void setFirstStarted(boolean firstStarted)
	{
		this.firstStarted = firstStarted;
		SharedPreUtil.getInstance().put("isFirstStarted", String.valueOf(firstStarted));

	}

	public boolean isStarted()
	{
		return started;
	}

	public void setStarted(boolean started)
	{
		this.started = started;
	}

	public CompanyJson getCompany()
	{
		return company;
	}

	public int getCompany_id()
	{
		return company_id;
	}
 
 
 

	private AppCache()
	{
		 company_id = 1;
		 
		  
	}
	
	public synchronized static AppCache getInstance()
	{
		if(null == instance)
		{
			instance = new AppCache();
		}
		return instance;
		
	}
	
	public void clear()
	{
		MemoryCache.setToken(null);
		user = null;
		customer = null;
		SharedPreUtil.getInstance().delete(USER_CACHE);
		SharedPreUtil.getInstance().delete(CUSTOMER_CACHE);
		SharedPreUtil.getInstance().delete(TOKEN_CACHE);
				
	}

	public CustomerJson getCustomer()
	{
		return customer;
	}
	public void update(CustomerJson customer)
	{
		this.customer = customer;
		SharedPreUtil.getInstance().put(CUSTOMER_CACHE, customer);
		load();

	}
	public void update(String token,UserJson user,CustomerJson customer)
	{
 
		SharedPreUtil.getInstance().put(USER_CACHE, user);
		SharedPreUtil.getInstance().put(CUSTOMER_CACHE, customer);
		SharedPreUtil.getInstance().put(TOKEN_CACHE,token );
		load();

	}

	public void load()
	{
		this.user =  (UserJson) SharedPreUtil.getInstance().get(USER_CACHE);
		this.customer = (CustomerJson) SharedPreUtil.getInstance().get(CUSTOMER_CACHE);
		MemoryCache.setToken((String) SharedPreUtil.getInstance().get(TOKEN_CACHE));
	 

		
	}
 
	public UserJson getUser()
	{
		return user;
	}

	public ConfigInfo getConfig()	 
	{
		ConfigInfo info = (ConfigInfo) SharedPreUtil.getInstance().get(CONFIG_INFO);
		if(null==info)
		{
			ConfigInfo newConfig= new ConfigInfo();
			saveConfig(newConfig);
			return newConfig;
		}
		return info;
		
	}
	
	public void saveConfig(ConfigInfo info)	 
	{

		SharedPreUtil.getInstance().put(CONFIG_INFO, info);
	}
	 
}
