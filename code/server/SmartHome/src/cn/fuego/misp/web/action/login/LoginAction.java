/**   
 * @Title: LoginAction.java 
 * @Package cn.fuego.misp.web.action.login 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-2-28 上午01:05:19 
 * @version V1.0   
 */
package cn.fuego.misp.web.action.login;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.service.spi.ServiceException;

import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.web.action.basic.MISPAction;
import cn.fuego.misp.web.constant.SessionAttrNameConst;
import cn.fuego.misp.web.model.menu.MenuTreeModel;
import cn.fuego.misp.web.model.user.UserModel;

import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName: LoginAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-2-28 上午01:05:19
 * @Edit Nan Bowen at 2014-03-23
 */

public class LoginAction extends MISPAction
{
	private Log log = LogFactory.getLog(LoginAction.class);
	private static final String LOGIN_FAILED = "LoginFailed";
	private List<MenuTreeModel> menuTreeItem = null;
	private UserModel user = null;
	private String message;

	public String execute()
	{
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();

		try
		{
			if (session.get(SessionAttrNameConst.LOGIN_USER) != null)
			{
				return SUCCESS;
			}

			// User Login
			MISPServiceContext.getInstance().getUserService().Login(user.getUserName(), user.getPassword());
			// Loading MenuTree
			menuTreeItem = MISPServiceContext.getInstance().getUserService().getMenuTreeByUserID(user.getUserName());
		}
		catch (ServiceException ex)
		{
			message = ex.getMessage();
			log.warn(ex.getMessage(), ex);
			return this.LOGIN_FAILED;
		}

		// if login success, we should put the user into session
		session.put(SessionAttrNameConst.LOGIN_USER, user);
		session.put(SessionAttrNameConst.MENU_TREE, menuTreeItem);
		/*
		 * This Code is Designed by Bowen. Which is means to config the basic page info. for instance, the name and the breadTrail we mast try to do and design better on this fuction.
		 */

		return SUCCESS;
	}
	public String logout()
	{
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.clear();
		return LOGIN_FAILED;
		
	}
	public UserModel getUser()
	{
		return user;
	}

	public String getMessage()
	{
		return message;
	}

	public void setUser(UserModel user)
	{
		this.user = user;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setMenuTreeItem(List<MenuTreeModel> menuTreeItem)
	{
		this.menuTreeItem = menuTreeItem;
	}

	public List<MenuTreeModel> getMenuTreeItem()
	{
		return menuTreeItem;
	}

}
