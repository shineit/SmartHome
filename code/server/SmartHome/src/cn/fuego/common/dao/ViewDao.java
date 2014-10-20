/**   
* @Title: ViewDao.java 
* @Package cn.fuego.common.dao 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-27 下午06:45:05 
* @version V1.0   
*/ 
package cn.fuego.common.dao;

import java.util.Collection;
import java.util.List;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: ViewDao 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-27 下午06:45:05 
 *  
 */

public interface ViewDao
{
	Collection getAll();
	long getCount(List<QueryCondition> conditionList);
	Collection getAll(List<QueryCondition> conditionList);
	Collection getAll(List<QueryCondition> conditionList,int startNum,int pageSize);
	Collection getAll(QueryCondition condition);

	PersistenceObject getUniRecord(QueryCondition condition);
	PersistenceObject getUniRecord(List<QueryCondition> conditionList);


}
