/**   
* @Title: AbstractViewDao.java 
* @Package cn.fuego.common.dao 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-27 下午06:46:11 
* @version V1.0   
*/ 
package cn.fuego.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;

import cn.fuego.common.dao.GenricTypeClass;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.ViewDao;
import cn.fuego.common.dao.hibernate.util.HibernateUtil;
import cn.fuego.common.log.FuegoLog;

/** 
 * @ClassName: AbstractViewDao 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-27 下午06:46:11 
 *  
 */

public class AbstractViewDao<E> extends GenricTypeClass<E> implements ViewDao<E>
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	public AbstractViewDao(Class clazz)
	{
		this.persistentClass= clazz;
	}
 
	
	public String getUniPriKey()
	{
 		//String priKey = HibernateUtil.getPersistentClass(this.persistentClass).getTable().getPrimaryKey().getColumn(0).getName();
 		ClassMetadata meta = HibernateUtil.getSessionFactory().getClassMetadata(persistentClass);  
 		//实体名称  
 		//String entityName = meta.getEntityName();  
 		//主键名称  
 		String pkName = meta.getIdentifierPropertyName();
		return pkName;
	}
	
 	public List<E> getAll()
	{
		
		QueryCondition conditon = null;
		List<E> objectList = this.getAll(conditon);

		return objectList;
 
	}
 
 
	public List<E> getAll(QueryCondition condition)
	{
		List<E> objectList = null;
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		
		if(null != condition)
		{
			conditionList.add(condition);
		}
		 
		objectList = this.getAll(conditionList);
		
		return objectList;

	}
	
	public List<E> getAll(List<QueryCondition> conditionList)
	{
		log.info("the condition list is " + conditionList);
		List<E> objectList = null;
		Session session = null;
		Transaction tx = null;
 		try
		{
		
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			objectList =c.list();
			tx.commit();
		} catch (RuntimeException re)
		{
			log.error("getAll error",re);

			throw re;
		} finally
		{
 			if (session != null)
			{
				session.close();
			}
		}
		
		log.info("the object calss is " + getFeaturedClass()+", the object list size is "+ objectList.size());
		return objectList;

	}
	
	public E getUniRecord(QueryCondition condition)
	{
 		E record = null;
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		
		if(null != condition)
		{
			conditionList.add(condition);
		}
		 
		record = this.getUniRecord(conditionList);
		return record;
 
	}
	
	public E getUniRecord(List<QueryCondition>  conditionList)
	{
		log.info("the condition list is " + conditionList);

		E record = null;
		Session session = null;
		Transaction tx = null;
 		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
 
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			record = (E) c.uniqueResult();
			tx.commit();
		} catch (RuntimeException re)
		{
			log.error("get UniRecord error",re);

			throw re;
		} finally
		{
 			if (session != null)
			{
				session.close();
			}
		}
		
		log.info("the object calss is " + getFeaturedClass()+", the object is "+ record);

		return record;
 
	}
	
	public List<E> getAll(List<QueryCondition> conditionList,int startNum,int pageSize)
	{
		log.info("the condition list is " + conditionList);
		log.info("the start number is " + startNum);
		log.info("the page size is " + pageSize);

		List<E> objectList = null;
		Session session = null;
		Transaction tx = null;
 		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			c.setFirstResult(startNum);  
	        c.setMaxResults(pageSize); 
			objectList =c.list();
			tx.commit();
		} catch (RuntimeException re)
		{
			log.error("getAll error",re);

			throw re;
		} finally
		{
 			if (session != null)
			{
				session.close();
			}
		}
		
		log.info("the object calss is " + getFeaturedClass()+", the object list size is "+ objectList.size());
		return objectList;

	}
	public long getCount(List<QueryCondition> conditionList)
	{
		log.info("the condition list is " + conditionList);

		long count = 0;
		Session session = null;
		Transaction tx = null;
 		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session,false);
			count = (Long)c.setProjection(Projections.rowCount()).uniqueResult(); 
			tx.commit();
		} catch (RuntimeException re)
		{
			log.error("getAll error",re);

			throw re;
		} finally
		{
 			if (session != null)
			{
				session.close();
			}
		}
		
		log.info("the object calss is " + getFeaturedClass()+", the count is "+ count);
		return count;

	}
 
}
