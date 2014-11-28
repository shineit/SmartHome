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

public interface ViewDao<E>
{
	List<E> getAll();
	long getCount(List<QueryCondition> conditionList);
	List<E> getAll(List<QueryCondition> conditionList);
	List<E> getAll(List<QueryCondition> conditionList,int startNum,int pageSize);
	List<E> getAll(QueryCondition condition);

	E getUniRecord(QueryCondition condition);
	E getUniRecord(List<QueryCondition> conditionList);


}
