/**   
 * @Title: LoginAction.java 
 * @Package cn.fuego.misp.web.action.login 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-2-28 上午01:05:19 
 * @version V1.0   
 */
package cn.fuego.misp.web.action.login;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.web.action.basic.MISPAction;
import cn.fuego.misp.web.constant.SessionAttrNameConst;
import cn.fuego.misp.web.model.menu.MenuTreeModel;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.user.PasswordModel;
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
	private String message ="";
	private String code;
    private PasswordModel pwdModel;

    
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
			SystemUser systemUser = MISPServiceContext.getInstance().getUserService().Login(user.getUserName(), user.getPassword());
			
			user.setUserID(systemUser.getUserID());
			user.setRegDate(systemUser.getRegDate());
			user.setAccountType(systemUser.getRole());
			user.setOrg_id(systemUser.getOrg_id());
			// Loading MenuTree
			menuTreeItem = MISPServiceContext.getInstance().getUserService().getMenuTreeByUserID(user.getUserID());

			
		}
		catch (MISPException ex)
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
    public String validateCode()  
    {  

    	boolean validateResult=false;
    	String  strv = (String)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        
        try
		{
        	if(strv.equals(code))
        	{
        		validateResult=true;
        	}
        	HttpServletResponse response = ServletActionContext.getResponse();   
        	response.setContentType("text/html"); //火狐浏览器必须加上这句  
            response.setCharacterEncoding("UTF-8");
			response.getWriter().print(validateResult);
		} catch (IOException e)
		{
			log.error("validateCode failed",e);
		} 
    
         return null;
   

    }    
	public String logout()
	{
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.clear();
		return LOGIN_FAILED;
	}
	
	public String home()
	{
	  
		return "home";
	}
	
	public String modifyPwd()
	{   

		try
		{
			MISPServiceContext.getInstance().getUserService().modifyPassword(this.getLoginUser().getUserName(), this.getPwdModel().getOldPassword(), this.getPwdModel().getNewPassword());
			this.getOperateMessage().setMessage("密码修改成功");
			//this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			
			this.getOperateMessage().setCallbackType(MispMessageModel.REDIRECT);
			this.getOperateMessage().setForwardUrl(ServletActionContext.getRequest().getContextPath());
			ActionContext actionContext = ActionContext.getContext();
			Map<String, Object> session = actionContext.getSession();
			session.clear();
		} catch (Exception e)
		{
			log.error("modify password failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setMessage(e.getMessage());
	
		}

		return MISP_DONE_PAGE;		
		
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
	public PasswordModel getPwdModel()
	{
		return pwdModel;
	}
	public void setPwdModel(PasswordModel pwdModel)
	{
		this.pwdModel = pwdModel;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}

	
}
