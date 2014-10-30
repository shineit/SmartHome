/**   
* @Title: UserManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:25:25 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.from.client.rest.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MISPUserServiceImpl;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.webservice.from.client.model.GetUserMarkListReq;
import cn.fuego.smart.home.webservice.from.client.model.GetUserMarkListRsp;
import cn.fuego.smart.home.webservice.from.client.model.LoginReq;
import cn.fuego.smart.home.webservice.from.client.model.LoginRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetSensorRsp;
import cn.fuego.smart.home.webservice.from.client.model.SetUserMarkReq;
import cn.fuego.smart.home.webservice.from.client.model.SetUserMarkRsp;
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
			MISPServiceContext.getInstance().getUserService().Login(req.getUserName(), req.getPassword());
		}
		catch(Exception e)
		{
			log.error("login failed",e);
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
	public SetUserMarkRsp getUserMarkList(SetUserMarkReq req)
	{
		SetUserMarkRsp rsp = new SetUserMarkRsp();
		return rsp;
	}

}
