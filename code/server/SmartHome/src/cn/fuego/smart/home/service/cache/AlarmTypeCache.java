/**   
* @Title: AlarmTypeCache.java 
* @Package cn.fuego.smart.home.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午5:43:05 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.cache;

import java.util.List;

import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.AlarmType;

 /** 
 * @ClassName: AlarmTypeCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午5:43:05 
 *  
 */
public class AlarmTypeCache
{
	private static AlarmTypeCache instance;
	
	private List<AlarmType> dataList;


	public static synchronized AlarmTypeCache getInstance()
	{
		if (null == instance)
		{
			instance = new AlarmTypeCache();
		}
		return instance;
	}

	public void reload()
	{
		dataList = DaoContext.getInstance().getDao(AlarmType.class).getAll();
	}
	
	public AlarmType getAlarmType(int typeID)
	{
		for(AlarmType type : dataList)
		{
			if(type.getTypeID() == typeID)
			{
				return type;
			}
		}
		return null;
	}
}
