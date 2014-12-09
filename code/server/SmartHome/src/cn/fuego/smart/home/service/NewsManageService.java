/**   
* @Title: NewsManageSerivce.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午11:21:55 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.web.model.NewsFilterModel;

 /** 
 * @ClassName: NewsManageSerivce 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午11:21:55 
 *  
 */
public interface NewsManageService extends MispCommonService<News>
{

 
 

	List<News> getIndexNews(String userName);
	
	
}
