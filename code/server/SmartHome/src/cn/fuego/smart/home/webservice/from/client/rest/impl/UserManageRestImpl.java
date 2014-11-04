/**   
* @Title: UserManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:25:25 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DateCreateUtil;
import cn.fuego.misp.domain.SystemMenu;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MISPUserServiceImpl;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.webservice.from.client.model.GetUserMarkListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetUserMarkListRsp;
import cn.fuego.smart.home.webservice.from.client.model.LoginReq;
import cn.fuego.smart.home.webservice.from.client.model.LoginRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetUserMarkReq;
import cn.fuego.smart.home.webservice.from.client.model.SetUserMarkRsp;
import cn.fuego.smart.home.webservice.from.client.model.base.MenuJson;
import cn.fuego.smart.home.webservice.from.client.model.base.UserJson;
import cn.fuego.smart.home.webservice.from.client.rest.UserManageRest;

 /** 
 * @ClassName: UserManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:25:25 
 *  
 */
public class UserManageRestImpl implements UserManageRest
{

	private Log log = LogFactory.getLog(MISPUserServiceImpl.class);

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.UserManageService#login(cn.fuego.smart.home.webservice.from.client.model.LoginReq)
	 */
	@Override
	public LoginRsp login(LoginReq req)
	{
		LoginRsp rsp = new LoginRsp();
		 
		if(null == req)
		{
			log.error("the logni request is null");
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_MSG_WRONG);
			
		}
		
		try
		{
			SystemUser user = MISPServiceContext.getInstance().getUserService().Login(req.getUserName(), req.getPassword());

			rsp.setToken(DateCreateUtil.getUUID());
		    List<SystemMenu> menuList = MISPServiceContext.getInstance().getUserService().getMenuListByUserID(user.getUserID());
		    
		    for(SystemMenu menu : menuList)
		    {	
		    	MenuJson menuJson = new MenuJson();
		    	menuJson.loadWithMenu(menu);
		    	rsp.getMenuList().add(menuJson);
		    }
		    
		    UserJson userJson = new UserJson();
		    rsp.setUser(userJson);
			
		}
		catch(MISPException e)
		{
			log.error("login failed",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.UserManageService#getUserMarkList(cn.fuego.smart.home.webservice.from.client.model.GetUserMarkListReq)
	 */
	@Override
	public GetUserMarkListRsp getUserMarkList(GetUserMarkListReq req)
	{
		GetUserMarkListRsp rsp = new GetUserMarkListRsp();
		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.UserManageService#getUserMarkList(cn.fuego.smart.home.webservice.from.client.model.SetUserMarkReq)
	 */
	@Override
	public SetUserMarkRsp addUserMark(SetUserMarkReq req)
	{
		SetUserMarkRsp rsp = new SetUserMarkRsp();
 
		return rsp;
	}

	public SetUserMarkRsp deleteUserMark(SetUserMarkReq req)
	{
		SetUserMarkRsp rsp = new SetUserMarkRsp();
		return rsp;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.rest.UserManageRest#logout(cn.fuego.smart.home.webservice.from.client.model.LoginReq)
	 */
	@Override
	public LoginRsp logout(LoginReq req)
	{
		LoginRsp rsp = new LoginRsp();
		return rsp;
	}

}
