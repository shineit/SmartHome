/**   
* @Title: UserService.java 
* @Package cn.fuego.remote.medical.expert.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 下午07:02:25 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

import java.util.List;

import cn.fuego.misp.web.model.menu.MenuTreeModel;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 下午07:02:25 
 *  
 */

public interface MISPUserService
{
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 */
	void Login(String userName,String password);
	
	/**
	 * 修改登录密码
	 * @param userName
	 * @param oldPwd
	 * @param newPwd
	 */
	void modifyPassword(String userName,String oldPwd,String newPwd);
	
	/**
	 * 根据用户ID 获取菜单树
	 * @param userID
	 * @return
	 */
	List<MenuTreeModel> getMenuTreeByUserID(String userID);
	

}
