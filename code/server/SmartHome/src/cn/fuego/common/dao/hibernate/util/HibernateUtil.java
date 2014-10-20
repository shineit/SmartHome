package cn.fuego.common.dao.hibernate.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.meta.ReflectionUtil;

public final class HibernateUtil
{
	private static Log log = LogFactory.getLog(HibernateUtil.class);

	private static SessionFactory sessionFactory;
	private static ThreadLocal session = new ThreadLocal();

	private HibernateUtil()
	{
	}

	static
	{
		try
		{
			Configuration cfg = new Configuration();
			cfg.configure();
			sessionFactory = cfg.buildSessionFactory();
		}	
		catch(Throwable e)
		{
			 
			log.error("hibernate initial failed",e);

		}

		
	}

	public static Session getThreadLocalSession()
	{
		Session s = (Session) session.get();
		if (s == null)
		{
			s = getSession();
			session.set(s);
		}
		return s;
	}

	public static void closeSession()
	{
		Session s = (Session) session.get();
		if (s != null)
		{
			s.close();
			session.set(null);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public static Session getSession()
	{
		return sessionFactory.openSession();
	}

	public static void add(Object entity)
	{
		Session s = null;
		Transaction tx = null;
		try
		{
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.save(entity);
			tx.commit();
		} finally
		{
			if (s != null)
				s.close();
		}
	}

	public static void update(Object entity)
	{
		Session s = null;
		Transaction tx = null;
		try
		{
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.merge(entity);
			tx.commit();
		} finally
		{
			if (s != null)
				s.close();
		}
	}

	public static void delete(Object entity)
	{
		Session s = null;
		Transaction tx = null;
		try
		{
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.delete(entity);
			tx.commit();
		} finally
		{
			if (s != null)
				s.close();
		}
	}

	public static Object get(Class clazz, Serializable id)
	{
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();
			Object obj = s.get(clazz, id);
			return obj;
		} finally
		{
			if (s != null)
				s.close();
		}
	}
	
	public static  Criteria getCriteriaByCondition(Class clazz , List<QueryCondition> conditionList,Session s)
	{
		Criteria c  = s.createCriteria(clazz);
		if(null != conditionList)
		{
			for(QueryCondition condition:conditionList)
			{
				Object valueObject = null;
				if(null != condition.getFirstValue())
				{
					valueObject = ReflectionUtil.convertToFieldObject(clazz, condition.getAttrName(), condition.getFirstValue());
				}
				switch(condition.getOperate())
				{
				case INCLUDLE:
					c.add(Restrictions.like(condition.getAttrName(),"%"+condition.getFirstValue()+"%"));
					break;
				case EXCLUDLE:
					c.add(Restrictions.like(condition.getAttrName(),"%"+condition.getFirstValue()+"%"));
					break;
				case EQUAL:	
					c.add(Restrictions.eq(condition.getAttrName(),valueObject));
					break;
				case NOT_EQUAL:	
					c.add(Restrictions.ne(condition.getAttrName(),valueObject));
					break;	
				case BIGER:	
					c.add(Restrictions.gt(condition.getAttrName(),valueObject));
					break;	
				case BIGER_EQ:	
					c.add(Restrictions.ge(condition.getAttrName(),valueObject));
					break;	
				case LOWER:	
					c.add(Restrictions.lt(condition.getAttrName(),valueObject));
					break;	
				case LOWER_EQ:	
					c.add(Restrictions.le(condition.getAttrName(),valueObject));
					break;	
				case BETWEEN:	
					Object secondValueObject = ReflectionUtil.convertToFieldObject(clazz, condition.getAttrName(), condition.getSecondValue());
					c.add(Restrictions.between(condition.getAttrName(),valueObject,secondValueObject));
					break;	
				case IN:
					List<Object> listObject = new ArrayList<Object>();
					for(String e : condition.getListValue())
					{
						listObject.add(ReflectionUtil.convertToFieldObject(clazz, condition.getAttrName(), e));
					}
					c.add(Restrictions.in(condition.getAttrName(),listObject));
					 
					break;
				case DESC_ORDER:	
					 c.addOrder(Order.desc(condition.getAttrName()));
					break;	
				case ASC_ORDER:	
					c.addOrder(Order.asc(condition.getAttrName()));
					break;	
				default:
				    break;
				  
				}
 			}
		}

		return c;
	}
	
	public static Blob getBlobByFile(File file)
	{
		Blob blob = null;
		Session s = null;
		try
		{
			FileInputStream fin= new FileInputStream(file);
			s = HibernateUtil.getSession();
			blob =Hibernate.getLobCreator(s).createBlob(fin, fin.available()); 
			 
		} catch (Exception e)
		{
			log.error("convert file to blob failed",e);
		}
		finally
		{
			if (s != null)
			{
				s.close();
			}
				
		}
		
		return blob;
	}
}
