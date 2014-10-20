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
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.ViewDao;
import cn.fuego.common.dao.hibernate.util.HibernateUtil;
import cn.fuego.common.domain.PersistenceObject;
import cn.fuego.common.util.meta.ReflectionUtil;

/** 
 * @ClassName: AbstractViewDao 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-27 下午06:46:11 
 *  
 */

public abstract class AbstractViewDao implements ViewDao
{
	private Log log = LogFactory.getLog(AbstractDao.class);

	public abstract Class getFeaturedClass();
	public Collection getAll()
	{
		List objectList = null;
		Session session = null;
 		try
		{
			session = HibernateUtil.getSession();
			Criteria c = session.createCriteria(getFeaturedClass());

			objectList =c.list();
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
		
		log.info("the object calss is " + getFeaturedClass()+"the object list size is "+ objectList.size());

		return objectList;
 
	}
	
	public PersistenceObject getUniRecord(QueryCondition condition)
	{
		PersistenceObject record = null;
		Session session = null;
 		try
		{
			session = HibernateUtil.getSession();

			List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
			
			if(null != condition)
			{
				conditionList.add(condition);
			}
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			record = (PersistenceObject) c.uniqueResult();
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
		
		log.info("the object calss is " + getFeaturedClass()+"the object is "+ record);

		return record;
 
	}
	public Collection getAll(QueryCondition condition)
	{
		List objectList = null;
		Session session = null;
 		try
		{
			session = HibernateUtil.getSession();
			List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
			
			if(null != condition)
			{
				conditionList.add(condition);
			}
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			objectList =c.list();
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
		
		log.info("the object calss is " + getFeaturedClass()+"the object list size is "+ objectList.size());
		return objectList;

	}
	
	public PersistenceObject getUniRecord(List<QueryCondition>  conditionList)
	{
		PersistenceObject record = null;
		Session session = null;
 		try
		{
			session = HibernateUtil.getSession();
 
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			record = (PersistenceObject) c.uniqueResult();
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
		
		log.info("the object calss is " + getFeaturedClass()+"the object is "+ record);

		return record;
 
	}
	public Collection getAll(List<QueryCondition> conditionList)
	{
		List objectList = null;
		Session session = null;
 		try
		{
			session = HibernateUtil.getSession();
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			objectList =c.list();
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
		
		log.info("the object calss is " + getFeaturedClass()+"the object list size is "+ objectList.size());
		return objectList;

	}
	public Collection getAll(List<QueryCondition> conditionList,int startNum,int pageSize)
	{
		List objectList = null;
		Session session = null;
 		try
		{
			session = HibernateUtil.getSession();
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			c.setFirstResult(startNum);  
	        c.setMaxResults(pageSize); 
			objectList =c.list();
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
		
		log.info("the object calss is " + getFeaturedClass()+"the object list size is "+ objectList.size());
		return objectList;

	}
	public long getCount(List<QueryCondition> conditionList)
	{
		long count = 0;
		Session session = null;
 		try
		{
			session = HibernateUtil.getSession();
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			count = (Long)c.setProjection(Projections.rowCount()).uniqueResult(); 		
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
		
		log.info("the object calss is " + getFeaturedClass()+"the count is "+ count);
		return count;

	}
 
}
