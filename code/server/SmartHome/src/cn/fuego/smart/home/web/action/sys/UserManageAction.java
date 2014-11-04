/**   
* @Title: UserManageAction.java 
* @Package cn.fuego.smart.home.web.action.sys 
* @Description: TODO
* @author Aether
* @date 2014-11-4 上午10:26:59 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.sys;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.constant.UserStatusEnum;
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
public class UserManageAction extends DWZTableAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(UserManageAction.class);
	private UserManageService userService = ServiceContext.getInstance().getUserManageService();
	private TableDataModel<SystemUser> userTable = new  TableDataModel<SystemUser>();
	private UserFilterModel filter = new UserFilterModel();
	private SystemUser sysUser;
    @Override
    public String execute()
    {
    	userTable.setPage(this.getPage());
    	userTable.setDataSource(userService.getUserDataSource(filter.getConidtionList()));
    	return SUCCESS;
    	
    }
	@Override
	public String create()
	{
		try
		{
			userService.saveUserInfo(sysUser);
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			
		} catch (Exception e)
		{
			log.error("create user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setMessage(e.getMessage());
		}
		return MISP_DONE_PAGE;
	}

	@Override
	public String delete()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteList()
	{
		userService.deleteUserList(Arrays.asList(this.getSelectedIDList()));
		return  MISP_DONE_PAGE;
	}

	@Override
	public String modify()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String show()
	{
         //默认新建用户的状态为已注册
		return EDIT_INFO;
	}
	public UserManageService getUserService()
	{
		return userService;
	}
	public void setUserService(UserManageService userService)
	{
		this.userService = userService;
	}
	public TableDataModel<SystemUser> getUserTable()
	{
		return userTable;
	}
	public void setUserTable(TableDataModel<SystemUser> userTable)
	{
		this.userTable = userTable;
	}
	public UserFilterModel getFilter()
	{
		return filter;
	}
	public void setFilter(UserFilterModel filter)
	{
		this.filter = filter;
	}
	public SystemUser getSysUser()
	{
		return sysUser;
	}
	public void setSysUser(SystemUser sysUser)
	{
		this.sysUser = sysUser;
	}
	
}
