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

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.web.model.UserCompanyModel;


public interface CompanyManageService extends MispCommonService<Company>
{
 
	/**
	 * 
	 * @param userID
	 * @return
	 */
	List<Company> getCompanyList(int userID);
	/**
	 * 
	 * @param concentorID
	 * @return
	 */
	Company getCompanyByConcentorID(long concentorID);
	
	/**
	 * 获取公司关联的用户表
	 * @param userIDList
	 * @return
	 */
	AbstractDataSource<SystemUser> getPermissionDataSource(List<String> userIDList);
	
	/**
	 * 新增用户公司关联
	 * @param userID
	 * @param companyID
	 */
	void addPermission(UserCompanyModel userPermission);
	
	/**
	 * 删除用户公司关联
	 * @param userID
	 * @param companyID
	 */
	void deletePermissionByID(UserCompanyModel userPermission);

}
