/**   
* @Title: AbstractDataSource.java 
* @Package cn.fuego.misp.service.datasource 
* @Description: TODO
* @author Tang Jun   
* @date 2014-3-26 上午12:13:50 
* @version V1.0   
*/ 
package cn.fuego.common.dao.datasource;

import java.util.List;

/** 
 * @ClassName: AbstractDataSource 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-3-26 上午12:13:50 
 *  
 */

public interface AbstractDataSource<E>
{
	public List<E> getCurrentPageData(int startNum,int pageSize);
	
	public List<E> getAllPageData();
	
	public long getDataCount();

}
