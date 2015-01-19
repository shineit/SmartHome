/**   
* @Title: UserManageAction.java 
* @Package cn.fuego.smart.home.web.action.sys 
* @Description: TODO
* @author Aether
* @date 2014-11-4 上午10:26:59 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.sys;

import java.util.List;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.UserManageService;
import cn.fuego.smart.home.web.model.UserFilterModel;

/** 
 * @ClassName: UserManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-4 上午10:26:59 
 *  
 */
public class UserManageAction extends DWZTableAction<SystemUser>
{


	private FuegoLog log = FuegoLog.getLog(getClass());
	
	private UserManageService userService = ServiceContext.getInstance().getUserManageService();
 	private UserFilterModel filter = new UserFilterModel();
 	
    @Override
    public List<QueryCondition> getFilterCondition()
    {
       return this.filter.getConidtionList();	
    }
 
	public MispCommonService<SystemUser> getService()
	{
		return userService;
	}
 
	public UserFilterModel getFilter()
	{
		return filter;
	}
	public void setFilter(UserFilterModel filter)
	{
		this.filter = filter;
	}
    // 权限配置查看添加用户专用，勿删   
	public String addUser()
	{
    	this.execute();
		return "addUser";
	}
}
