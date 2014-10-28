/**   
* @Title: BasicDao.java 
* @Package cn.fuego.misp.dao.hibernate.util 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 下午03:03:18 
* @version V1.0   
*/ 
package cn.fuego.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.CreateException;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: BasicDao 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 下午03:03:18 
 *  
 */

public interface Dao extends ViewDao
{

	void create(PersistenceObject object);

	void update(PersistenceObject object);
 
	void delete(List<QueryCondition> conditionList);
	
	void delete(QueryCondition condition);

}
