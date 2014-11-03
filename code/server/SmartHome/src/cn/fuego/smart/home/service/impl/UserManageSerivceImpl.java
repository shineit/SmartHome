/**   
* @Title: UserManageSerivceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午9:07:54 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.impl.MISPUserServiceImpl;

 /** 
 * @ClassName: UserManageSerivceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午9:07:54 
 *  
 */
public class UserManageSerivceImpl extends MISPUserServiceImpl
{

	public SystemUser Login(String userName, String password)
	{
		SystemUser user = super.Login(userName, password);
		
		
		return user;
	}

}
