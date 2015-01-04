/**   
* @Title: CommonService.java 
* @Package cn.fuego.misp.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-27 下午12:32:19 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

import java.util.List;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;

 /** 
 * @ClassName: CommonService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-27 下午12:32:19 
 *  
 */
public interface MispCommonService<E>
{
    void validator(E obj);
    AbstractDataSource<E> getDataSource();
    AbstractDataSource<E> getDataSource(List<QueryCondition> conditionList);
    void create(E obj);
    void create(int userID,E obj);
    void create(List<E> objList);
    void create(int userID,List<E> objList);
    
    
    void modify(E obj);
    void modify(int userID,E obj);
    
    void delete(String id);
    void delete(int userID,String id);
    void delete(List<String> idList);
    void delete(int userID,List<String> idList);
    
    E get(String id);
    E get(int id);
    E get(long id);
    List<E> get(List<String> idList);
    List get(Class clazz ,QueryCondition  condition);

    List get(Class clazz,List<QueryCondition> conditionList);
}
