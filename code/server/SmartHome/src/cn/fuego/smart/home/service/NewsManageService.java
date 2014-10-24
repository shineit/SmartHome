/**   
* @Title: NewsManageSerivce.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午11:21:55 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.smart.home.domain.News;

 /** 
 * @ClassName: NewsManageSerivce 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午11:21:55 
 *  
 */
public interface NewsManageService
{

	AbstractDataSource<News>  getNewsDataSource();
}
