/**   
* @Title: PersistenceObject.java 
* @Package cn.fuego.misp.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 下午03:28:20 
* @version V1.0   
*/ 
package cn.fuego.common.domain;

import java.io.Serializable;

/** 
 * @ClassName: PersistenceObject 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 下午03:28:20 
 *  
 */

public interface PersistenceObject extends Serializable
{
	public int hashCode();
	public boolean equals(Object obj);

}
