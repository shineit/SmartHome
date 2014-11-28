/**   
* @Title: GenricTypeClass.java 
* @Package cn.fuego.misp.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-27 下午3:05:02 
* @version V1.0   
*/ 
package cn.fuego.common.dao;

 /** 
 * @ClassName: GenricTypeClass 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-27 下午3:05:02 
 *  
 */
public class GenricTypeClass<E>
{
	protected Class<E> persistentClass;
	protected Class<E> getFeaturedClass()
	{
		return persistentClass;
	}
 
}
