/**   
* @Title: DataBaseSourceImpl.java 
* @Package cn.fuego.common.service.datasource 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-26 上午12:13:01 
* @version V1.0   
*/ 
package cn.fuego.common.dao.datasource;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.impl.AbstractDao;

/** 
 * @ClassName: DataBaseSourceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-26 上午12:13:01 
 *  
 */

public class DataBaseSourceImpl<E> extends AbstractDao implements AbstractDataSource<E>
{

	private Class clazz;
	private List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
	
	public DataBaseSourceImpl(Class clazz)
	{
		this.clazz = clazz;
	}
	
	public DataBaseSourceImpl(Class clazz,List<QueryCondition> conditionList)
	{
		this.clazz = clazz;
		this.conditionList = conditionList;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.common.service.datasource.AbstractDataSource#getCurrentPageData(int, int)
	 */
	@Override
	public List<E> getCurrentPageData(int startNum, int pageSize)
	{
		// TODO Auto-generated method stub
		return (List<E>) this.getAll(conditionList, startNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.common.service.datasource.AbstractDataSource#getAllPageData()
	 */
	@Override
	public List<E> getAllPageData()
	{
		// TODO Auto-generated method stub
		return (List<E>) this.getAll(conditionList);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.common.service.datasource.AbstractDataSource#getDataCount()
	 */
	@Override
	public long getDataCount()
	{
		// TODO Auto-generated method stub
		return this.getCount(conditionList);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.common.dao.AbstractDao#getFeaturedClass()
	 */
	@Override
	public Class getFeaturedClass()
	{
		// TODO Auto-generated method stub
		return clazz;
	}

}
