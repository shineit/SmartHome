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

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.domain.CheckItem;


/** 
* @ClassName: CheckItemManageService 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午2:35:21 
*  
*/ 
public interface CheckItemManageService extends MispCommonService<CheckItem>
{

	List<CheckItem> getCheckItemByID(String companyID);
 
	
}
