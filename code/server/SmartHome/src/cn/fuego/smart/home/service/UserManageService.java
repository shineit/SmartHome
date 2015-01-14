package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPUserService;
import cn.fuego.smart.home.domain.Customer;
import cn.fuego.smart.home.domain.UserMark;


public interface UserManageService extends MISPUserService
{
 
	
	List<UserMark> getUserMark(int userID);
	
	void deleteUserMark(UserMark userMark);
	void createUserMark(UserMark userMark);
	
	Customer getCustomer(int userID);
	void modifyCustomer(Customer customer);
}
