/**   
* @Title: DataCollectionCache.java 
* @Package cn.fuego.dmsp.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-27 下午03:54:56 
* @version V1.0   
*/ 
package cn.fuego.smart.home.device.listenser;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: DataCollectionCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-27 下午03:54:56 
 *  
 */

public class DataCollectionCache
{
	private Map<String,String> allData = new HashMap<String,String>();

	public Map<String, String> getAllData()
	{
		return allData;
	}

	public void setAllData(Map<String, String> allData)
	{
		this.allData = allData;
	}

	@Override
	public String toString()
	{
		return "DataCollectionCache [allData=" + allData + "]";
	}
	
	

}
