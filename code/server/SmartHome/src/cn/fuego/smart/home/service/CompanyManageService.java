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
import cn.fuego.smart.home.domain.Company;


public interface CompanyManageService extends MispCommonService<Company>
{
 
	List<Company> getCompanyList(int userID);
}
