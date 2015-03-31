/**   
* @Title: AlarmTypeCache.java 
* @Package cn.fuego.smart.home.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午5:43:05 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.cache;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.SensorType;

 /** 
 * @ClassName: AlarmTypeCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午5:43:05 
 *  
 */
public class SensorTypeCache
{
	private static SensorTypeCache instance;
	
	private List<SensorType> dataList;


	private SensorTypeCache()
	{
		reload();
	}
	public static synchronized SensorTypeCache getInstance()
	{
		if (null == instance)
		{
			instance = new SensorTypeCache();
		}
		return instance;
	}

	public void reload()
	{
		dataList = DaoContext.getInstance().getDao(SensorType.class).getAll();
	}
	
	public SensorType getSensorType(int typeID)
	{
		for(SensorType type : dataList)
		{
			if(type.getTypeID() == typeID)
			{
				return type;
			}
		}
		return null;
	}
	public List<String> getTypeNameList()
	{
		List<String> typeNameList = new ArrayList<String>();
		for(SensorType type : dataList)
		{
			typeNameList.add(type.getTypeName());
		}
		return typeNameList;
	}
	
}
