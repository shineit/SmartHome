/**   
* @Title: UserManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:25:25 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.format.DataCreateUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.domain.SystemMenu;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MISPUserServiceImpl;
import cn.fuego.smart.home.constant.ClientTypeEnum;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.Customer;
import cn.fuego.smart.home.domain.UserMark;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AppLoginCache;
import cn.fuego.smart.home.service.cache.AppLoginInfo;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetCaTokenByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCaTokenByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetCompanyListReq;
import cn.fuego.smart.home.webservice.up.model.GetCompanyListRsp;
import cn.fuego.smart.home.webservice.up.model.GetCustomerByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCustomerByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListReq;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListRsp;
import cn.fuego.smart.home.webservice.up.model.LoginReq;
import cn.fuego.smart.home.webservice.up.model.LoginRsp;
import cn.fuego.smart.home.webservice.up.model.ModifyPwdReq;
import cn.fuego.smart.home.webservice.up.model.ModifyPwdRsp;
import cn.fuego.smart.home.webservice.up.model.SetCustomerReq;
import cn.fuego.smart.home.webservice.up.model.SetCustomerRsp;
import cn.fuego.smart.home.webservice.up.model.SetUserMarkReq;
import cn.fuego.smart.home.webservice.up.model.SetUserMarkRsp;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;
import cn.fuego.smart.home.webservice.up.model.base.MenuJson;
import cn.fuego.smart.home.webservice.up.model.base.UserJson;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;
import cn.fuego.smart.home.webservice.up.rest.UserManageRest;

import com.hikvision.PublicController;
import com.hikvision.TokenResultModel;

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
			Customer customer=new Customer();
			if(user!=null)
			{
				customer = ServiceContext.getInstance().getUserManageService().getCustomer(user.getUserID());
			}
			
			rsp.setToken(DataCreateUtil.getUUID());
		    List<SystemMenu> menuList = MISPServiceContext.getInstance().getUserService().getMenuListByUserID(user.getUserID());
		    
		    AppLoginInfo loginInfo = new AppLoginInfo();
		    loginInfo.getDeviceInfo().setClientType(ClientTypeEnum.getEnumByInt(req.getClientType()));
		    loginInfo.getDeviceInfo().setClientVersion(req.getClientVersion());
		    loginInfo.getDeviceInfo().setDevToken(req.getDevToken());
		   
		    loginInfo.getPushInfo().setDeviceType(loginInfo.getDeviceInfo().getClientType());
		    loginInfo.getPushInfo().setAppID(req.getPush_appID());
		    loginInfo.getPushInfo().setDeviceID(req.getPush_channelID());
		    loginInfo.getPushInfo().setUserID(req.getPush_userID());
		    loginInfo.setUser(user);
		    
		    AppLoginCache.login(rsp.getToken(), loginInfo);
		    
		    for(SystemMenu menu : menuList)
		    {	
		    	MenuJson menuJson = ModelConvert.menuToJson(menu);
		    	rsp.getMenuList().add(menuJson);
		    }

		    UserJson userJson = ModelConvert.userToJson(user);
		    rsp.setUser(userJson);
		    if(customer!=null)
		    {
			    CustomerJson customerJson=ModelConvert.customerToJson(customer);
			    rsp.setCustomer(customerJson);
		    }

			
		}
		catch(MISPException e)
		{
			log.error("login failed",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
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
	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.UserManageService#getUserMarkList(cn.fuego.smart.home.webservice.from.client.model.GetUserMarkListReq)
	 */
	@Override
	public GetUserMarkListRsp getUserMarkList(GetUserMarkListReq req)
	{
		GetUserMarkListRsp rsp = new GetUserMarkListRsp();
		List<UserMark> userMarkList  = ServiceContext.getInstance().getUserManageService().getUserMark(req.getUserID());
		
		for(UserMark mark : userMarkList)
		{
			UserMarkJson json = ModelConvert.markToJson(mark);
			rsp.getMarkList().add(json);
			
		}
		
		
		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.UserManageService#getUserMarkList(cn.fuego.smart.home.webservice.from.client.model.SetUserMarkReq)
	 */
	@Override
	public SetUserMarkRsp addUserMark(SetUserMarkReq req)
	{
		SetUserMarkRsp rsp = new SetUserMarkRsp();
		try
		{
			UserMarkJson userMarkJson = req.getUserMark();
			ServiceContext.getInstance().getUserManageService().createUserMark(ModelConvert.jsonToMark(userMarkJson));
		}
		catch(MISPException e)
		{
			log.error("create mark failed",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.getResult().setErrorCode(ErrorMessageConst.OPERATE_FAILED);
			log.error("create mark failed",e);
		}
 
		return rsp;
	}

	public SetUserMarkRsp deleteUserMark(SetUserMarkReq req)
	{
		SetUserMarkRsp rsp = new SetUserMarkRsp();
		try
		{
			UserMarkJson userMarkJson = req.getUserMark();
			ServiceContext.getInstance().getUserManageService().deleteUserMark(ModelConvert.jsonToMark(userMarkJson));
		}
		catch(MISPException e)
		{
			log.error("create mark failed",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.getResult().setErrorCode(ErrorMessageConst.OPERATE_FAILED);
			log.error("create mark failed",e);
		}
 
		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.rest.UserManageRest#modifyPassword(cn.fuego.smart.home.webservice.from.client.model.LoginReq)
	 */
	@Override
	public ModifyPwdRsp modifyPassword(ModifyPwdReq req)
	{
		ModifyPwdRsp rsp = new ModifyPwdRsp();
		try
		{
 			MISPServiceContext.getInstance().getUserService().modifyPassword(req.getUserName(),req.getOldPwd(),req.getPwdNew());
		}
		catch(MISPException e)
		{
			log.error("create mark failed",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.getResult().setErrorCode(ErrorMessageConst.OPERATE_FAILED);
			log.error("create mark failed",e);
		}
 
		return rsp;
	}

	@Override
	public GetCaTokenByIDRsp getCaToken(GetCaTokenByIDReq req)
	{
		GetCaTokenByIDRsp rsp= new GetCaTokenByIDRsp();
		try
		{
			PublicController cameraCtr= new PublicController();
			TokenResultModel result=cameraCtr.getAccessToken(String.valueOf(req.getUserID()), req.getPhone());
			if(null != result)
			{
				if("200".equals(result.getCode()))
				{
					rsp.getResult().setErrorCode(MISPErrorMessageConst.SUCCESS);
					if(null != result.getData())
					{
						rsp.getCaToken().setAccessToken(result.getData().getAccessToken());
						log.info("sap token is :"+result.getData().getAccessToken());
					}
					else
					{
						rsp.getResult().setErrorCode(ErrorMessageConst.CAMERA_LINK_ERROR);
					}
 
				}
				else if("10011".equals(result.getCode()))
				{
					rsp.getResult().setErrorCode(ErrorMessageConst.CAMERA_ACCOUNT_NOT_BUNDLE);//私有云账号未绑定
				}
				else
				{
					rsp.getResult().setErrorCode(ErrorMessageConst.CAMERA_LINK_ERROR);//连接异常
				}
			}
 		}
		catch(MISPException e)
		{
			log.error("create mark failed",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.getResult().setErrorCode(ErrorMessageConst.OPERATE_FAILED);
			log.error("create mark failed",e);
		}
 
		return rsp;

	}

	@Override
	public GetCustomerByIDRsp getCustomer(GetCustomerByIDReq req)
	{
		GetCustomerByIDRsp rsp = new GetCustomerByIDRsp();
		
		try
		{
			Customer customer= ServiceContext.getInstance().getUserManageService().getCustomer(req.getUserID());
			CustomerJson customerJson = ModelConvert.customerToJson(customer);
		    rsp.setCustomer(customerJson);
		}
		catch(MISPException e)
		{
			log.error("get alarm error",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get alarm error",e);
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}

	@Override
	public SetCustomerRsp modifyCustomer(SetCustomerReq req)
	{
		SetCustomerRsp rsp = new SetCustomerRsp();
		try
		{
			CustomerJson customerJson = req.getCustomer();
			ServiceContext.getInstance().getUserManageService().modifyCustomer(ModelConvert.jsonToCustomer(customerJson));
		}
		catch(MISPException e)
		{
			log.error("create mark failed",e);
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.getResult().setErrorCode(ErrorMessageConst.OPERATE_FAILED);
			log.error("create mark failed",e);
		}
 
		return rsp;
	}

	@Override
	public GetCompanyListRsp getCompanyList(GetCompanyListReq req)
	{
		GetCompanyListRsp rsp = new GetCompanyListRsp();
		List<Company> companyList = ServiceContext.getInstance().getCompanyManageService().getCompanyList(req.getUserID());

		for(Company company : companyList)
		{
			CompanyJson json = ModelConvert.CompanyToJson(company);
			rsp.getCompanyList().add(json);	
		}
		
		return rsp;
	}


}
