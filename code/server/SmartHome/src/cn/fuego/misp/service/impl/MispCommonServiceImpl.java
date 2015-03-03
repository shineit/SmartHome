/**   
 * @Title: MispCommonServiceImpl.java 
 * @Package cn.fuego.misp.service.impl 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-11-27 下午2:20:34 
 * @version V1.0   
 */
package cn.fuego.misp.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.Dao;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.dao.impl.AbstractDao;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.meta.ReflectionUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.MispCommonService;

/**
 * @ClassName: MispCommonServiceImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-27 下午2:20:34
 * 
 */
public abstract class  MispCommonServiceImpl<E> implements MispCommonService<E>
{
	private FuegoLog log = FuegoLog.getLog(getClass());
 
	private Class clazz;
	public MispCommonServiceImpl()
	{
		clazz = ReflectionUtil.getSuperClassGenricType(getClass());
	}
	public Dao<E> getDao()
	{
		return new AbstractDao<E>(clazz);
	}
	public Dao getDao(Class clazz)
	{
		return new AbstractDao(clazz);
	}

	
	//private Dao getDao()

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#validator(java.lang.Object)
	 */
	@Override
	public void validator(E obj)
	{
		log.info("the validator is empty ");
        
 	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#getDataSource()
	 */
	@Override
	public AbstractDataSource<E> getDataSource()
	{
		 
        AbstractDataSource<E> dataSource = new DataBaseSourceImpl<E>(ReflectionUtil.getSuperClassGenricType(getClass()));
        return dataSource;
	}
	
	@Override
	public AbstractDataSource<E> getDataSource(List<QueryCondition> conditionList)
	{
		 
        AbstractDataSource<E> dataSource = new DataBaseSourceImpl<E>(ReflectionUtil.getSuperClassGenricType(getClass()),conditionList);
        return dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#create(java.lang.Object)
	 */
	@Override
	public void create(E obj)
	{
		validator(obj);
		this.getDao().create(obj);
	}
	@Override
	public void create(int userID,E obj)
	{
		validator(obj);
		this.getDao().create(obj);
 
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#create(java.lang.Object)
	 */
	@Override
	public void create(List<E> objList)
	{
		 
		this.getDao().create(objList);
	}
	@Override
	public void create(int userID,List<E> objList)
	{
		 
		this.getDao().create(objList);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#modify(java.lang.Object)
	 */
	@Override
	public void modify(E obj)
	{
        validator(obj);
        this.getDao().update(obj);

	}
	@Override
	public void modify(int userID,E obj)
	{
        validator(obj);
        this.getDao().update(obj);

	}
	 public void Modify(List<String> idList,String fieldName,Object fieldValue)
     {
         if (ValidatorUtil.isEmpty(idList))
         {
             log.warn("the id list is empty");
             return;
         }
         List<E> objList = get(idList);
         if (ValidatorUtil.isEmpty(objList))
         {
            log.warn("can not find the object by id list " + idList.toString());
            return;
         }

         for(E e : objList)
         {
             ReflectionUtil.setObjetField(e,fieldName,fieldValue);
         }

         this.getDao().update(objList);
     }
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id)
	{
        if (ValidatorUtil.isEmpty(id))
        {
            log.warn("the id is empty");
            return;
        }
 
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, GetPrimaryName(), id);

        this.getDao().delete(condition);
	}
	@Override
	public void delete(int userID,String id)
	{
        if (ValidatorUtil.isEmpty(id))
        {
            log.warn("the id is empty");
            return;
        }
 
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, GetPrimaryName(), id);

        this.getDao().delete(condition);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#delete(java.util.List)
	 */
	@Override
	public void delete(List<String> idList)
	{
        if (ValidatorUtil.isEmpty(idList))
        {
            log.warn("the id is empty");
            return;
        }
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, GetPrimaryName(), idList);

        this.getDao().delete(condition);
	}
	@Override
	public void delete(int userID,List<String> idList)
	{
        if (ValidatorUtil.isEmpty(idList))
        {
            log.warn("the id is empty");
            return;
        }
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, GetPrimaryName(), idList);

        this.getDao().delete(condition);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#get(java.lang.String)
	 */
	@Override
	public E get(String id)
	{
        if (ValidatorUtil.isEmpty(id))
        {
            log.warn("the id is empty");
            return null;
        }
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, GetPrimaryName(), id);
        return this.getDao().getUniRecord(condition);
	}

	 
	public E get(int id)
	{
 
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, GetPrimaryName(), id);
        return this.getDao().getUniRecord(condition);
	}
	public E get(long id)
	{
 
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.EQUAL, GetPrimaryName(), id);
        return this.getDao().getUniRecord(condition);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.misp.service.MispCommonService#get(java.util.List)
	 */
	@Override
	public List<E> get(List<String> idList)
	{
        if (ValidatorUtil.isEmpty(idList))
        {
            log.warn("the id is empty");
            return null;
        }
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, GetPrimaryName(), idList);
        return this.getDao().getAll(condition);
	}
	 
	public List<E> getByID(List<Integer> idList)
	{
        if (ValidatorUtil.isEmpty(idList))
        {
            log.warn("the id is empty");
            return null;
        }
        QueryCondition condition = new QueryCondition(ConditionTypeEnum.IN, GetPrimaryName(), idList);
        return this.getDao().getAll(condition);
	}
 
	public  List<E> get(QueryCondition condition)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(null != condition)
		{
			conditionList.add(condition);
		}
        return this.getDao().getAll(conditionList);
	}
 
	
	@Override
	public  List get(Class clazz,QueryCondition condition)
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(null != condition)
		{
			conditionList.add(condition);
		}
        return this.getDao(clazz).getAll(conditionList);
	}
	@Override
	public  List get(Class clazz,List<QueryCondition> conditionList)
	{
         
        return this.getDao(clazz).getAll(conditionList);
	}
	public abstract String GetPrimaryName();
	
   

}
