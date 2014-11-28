/**   
 * @Title: MispCommonServiceImpl.java 
 * @Package cn.fuego.misp.service.impl 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-11-27 下午2:20:34 
 * @version V1.0   
 */
package cn.fuego.misp.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.dao.DaoContext;

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
	private Dao<E> getDao()
	{
		return new AbstractDao<E>(clazz);
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

	 public void Modify(List<String> idList,String fieldName,String fieldValue)
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
	
	public abstract String GetPrimaryName();
	
   

}
