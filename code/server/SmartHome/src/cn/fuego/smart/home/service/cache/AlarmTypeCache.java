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

import cn.fuego.common.log.FuegoLog;
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
	private FuegoLog log = FuegoLog.getLog(getClass());
	private static AlarmTypeCache instance;
	
	private List<AlarmType> dataList;


	private AlarmTypeCache()
	{
		reload();
	}
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
		log.info("the alarm type is " + dataList);
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
	public List<String> getTypeNameList()
	{
		List<String> typeNameList = new ArrayList<String>();
		for(AlarmType type : dataList)
		{
			typeNameList.add(type.getTypeName());
		}
		return typeNameList;
	}
	
}
