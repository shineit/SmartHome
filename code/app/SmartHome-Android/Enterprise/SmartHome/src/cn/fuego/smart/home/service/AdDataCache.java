/**   
* @Title: AdDataCache.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Aether
* @date 2015-3-21 上午10:08:34 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.webservice.up.model.base.AdvertisementJson;

/** 
 * @ClassName: AdDataCache 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-21 上午10:08:34 
 *  
 */
public class AdDataCache
{

	private  List<AdvertisementJson> dataList = new ArrayList<AdvertisementJson>();
	private static AdDataCache instance;
	private AdDataCache()
	{
		 
	}
	
	public synchronized static AdDataCache getInstance()
	{
		if(null == instance)
		{
			instance = new AdDataCache();
		}
		return instance;
		
	}
	public boolean isEmpty()
	{
		if(ValidatorUtil.isEmpty(dataList))
		{
			return true;
		}
		return false;
	}
	public void init(List<AdvertisementJson> newData)
	{
		dataList.clear();
		if(!ValidatorUtil.isEmpty(newData))
		{
			dataList.addAll(newData);
		}
	}

	public List<AdvertisementJson> getDataList()
	{
		return dataList;
	}
	
}
