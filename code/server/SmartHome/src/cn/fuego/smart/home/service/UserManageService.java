package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.misp.domain.SystemUser;


public interface UserManageService
{
	AbstractDataSource<SystemUser>  getUserDataSource(List<QueryCondition> conditionList);

	void saveUserInfo(SystemUser sysUser);

	void deleteUserList(List<String> userIDList);
	
	
}
