package cn.fuego.smart.home.cache;

import cn.fuego.common.log.FuegoLog;

public class AppCache
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private static AppCache instance;
	
	private String versionName;
	private int versionCode;

 
	public String getVersionNname()
	{
		return versionName;
	}

	public int getVersionCode()
	{
		return versionCode;
	}
	
	

	public void setVersionNname(String versionNname)
	{
		this.versionName = versionNname;
	}

	public void setVersionCode(int versionCode)
	{
		this.versionCode = versionCode;
	}
	
	public synchronized static AppCache getInstance()
	{
		if(null == instance)
		{
			instance = new AppCache();
		}
		return instance;
		
	}

	 
}
