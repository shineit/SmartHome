/**   
* @Title: UserManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:25:25 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DataCreateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.constant.PrivilegeAccessObjTypeEnum;
import cn.fuego.misp.domain.SystemMenu;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.impl.MISPUserServiceImpl;
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AlarmKindEnum;
import cn.fuego.smart.home.constant.BageKindEnum;
import cn.fuego.smart.home.constant.CheckLogStatusEnum;
import cn.fuego.smart.home.constant.CheckResultEnum;
import cn.fuego.smart.home.constant.ClientTypeEnum;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.CheckLog;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.Customer;
import cn.fuego.smart.home.domain.UserMark;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AppLoginCache;
import cn.fuego.smart.home.service.cache.AppLoginInfo;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetCaTokenByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCaTokenByIDRsp;
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
import cn.fuego.smart.home.webservice.up.model.base.BageNumJson;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.CustomerJson;
import cn.fuego.smart.home.webservice.up.model.base.MenuJson;
import cn.fuego.smart.home.webservice.up.model.base.UserJson;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckLogNumByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCheckLogNumByIDRsp;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmNumByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireAlarmNumByIDRsp;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireStatusNumByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetFireStatusNumByIDRsp;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetInitDataReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetInitDataRsp;
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
			rsp.setErrorCode(ErrorMessageConst.ERROR_MSG_WRONG);
			
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
			rsp.setErrorCode(e.getErrorCode());
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
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.setErrorCode(ErrorMessageConst.OPERATE_FAILED);
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
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.setErrorCode(ErrorMessageConst.OPERATE_FAILED);
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
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.setErrorCode(ErrorMessageConst.OPERATE_FAILED);
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
					rsp.setErrorCode(MISPErrorMessageConst.SUCCESS);
					if(null != result.getData())
					{
						rsp.getCaToken().setAccessToken(result.getData().getAccessToken());
						log.info("sap token is :"+result.getData().getAccessToken());
					}
					else
					{
						rsp.setErrorCode(ErrorMessageConst.CAMERA_LINK_ERROR);
					}
 
				}
				else if("10011".equals(result.getCode()))
				{
					rsp.setErrorCode(ErrorMessageConst.CAMERA_ACCOUNT_NOT_BUNDLE);//私有云账号未绑定
				}
				else
				{
					rsp.setErrorCode(ErrorMessageConst.CAMERA_LINK_ERROR);//连接异常
				}
			}
 		}
		catch(MISPException e)
		{
			log.error("create mark failed",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.setErrorCode(ErrorMessageConst.OPERATE_FAILED);
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
			log.error("get Customer error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get Customer error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
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
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
		    rsp.setErrorCode(ErrorMessageConst.OPERATE_FAILED);
			log.error("create mark failed",e);
		}
 
		return rsp;
	}

	@Override
	public GetFireAlarmNumByIDRsp getAlarmNum(GetFireAlarmNumByIDReq req)
	{
		GetFireAlarmNumByIDRsp rsp = new GetFireAlarmNumByIDRsp();

		List<BageNumJson> numList = getAlarmNum(req.getUserID(),AlarmKindEnum.ALARM.getIntValue());
		rsp.getNumList().addAll(numList);
		return rsp;
	}

	@Override
	public GetFireStatusNumByIDRsp getStatusNum(GetFireStatusNumByIDReq req)
	{
		GetFireStatusNumByIDRsp rsp = new GetFireStatusNumByIDRsp();
		List<BageNumJson> numList = getAlarmNum(req.getUserID(),AlarmKindEnum.STATUS.getIntValue());
		rsp.getNumList().addAll(numList);
		return rsp;
	}

	private List<BageNumJson> getAlarmNum(int userID, int kind)
	{
		List<BageNumJson> numList = new ArrayList<BageNumJson>();
		Set<String> companyIDList=MISPServiceContext.getInstance().getMISPPrivilegeManage().getObjectIDListByUser(PrivilegeAccessObjTypeEnum.COMPANY.getObjectType(), String.valueOf(userID));
		for(String companyID:companyIDList)
		{
			BageNumJson json = new BageNumJson();
			json.setCompanyID(Integer.valueOf(companyID));
			Company company= ServiceContext.getInstance().getCompanyManageService().get(companyID);
			List<QueryCondition> conditionList= new ArrayList<QueryCondition>();
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "concentratorID", company.getConcentratorID()));
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "kind", kind));
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "clearStatus", AlarmClearEnum.NONE_CLEAR.getIntValue()));
			long count =ServiceContext.getInstance().getFireAlarmService().getDataSource(conditionList).getDataCount();
			json.setNum(count);
			numList.add(json);
		}
		return numList;
	}

	@Override
	public GetCheckLogNumByIDRsp getCheckNum(GetCheckLogNumByIDReq req)
	{
		GetCheckLogNumByIDRsp rsp = new GetCheckLogNumByIDRsp();
		List<BageNumJson> numList = new ArrayList<BageNumJson>();

		Set<String> companyIDList=MISPServiceContext.getInstance().getMISPPrivilegeManage()
				.getObjectIDListByUser(PrivilegeAccessObjTypeEnum.COMPANY.getObjectType(), String.valueOf(req.getUserID()));
		for(String companyID:companyIDList)
		{
			BageNumJson json = new BageNumJson();
			json.setCompanyID(Integer.valueOf(companyID));			
			List<CheckLog>  logList=ServiceContext.getInstance().getCheckLogService().getCurrentLog(Integer.valueOf(companyID));
			json.setNum(logList.size());
			numList.add(json);
		}
		rsp.getNumList().addAll(numList);
		return rsp;
	}

	@Override
	public GetInitDataRsp getInitData(GetInitDataReq req)
	{
		GetInitDataRsp rsp = new GetInitDataRsp();
		try
		{
			//获取用户信息
			Customer customer= ServiceContext.getInstance().getUserManageService().getCustomer(req.getUserID());
			CustomerJson customerJson = ModelConvert.customerToJson(customer);
			rsp.setCustomer(customerJson);
			//获取公司列表信息
			List<Company> companyList = ServiceContext.getInstance().getCompanyManageService().getCompanyList(req.getUserID());
			//如果用户包含该公司的状况如下
			if(!ValidatorUtil.isEmpty(companyList))
			{
				for(Company company : companyList)
				{
					CompanyJson json = ModelConvert.companyToJson(company);
					rsp.getCompanyList().add(json);	
					
					BageNumJson bage1 = new BageNumJson();
					bage1.setCompanyID(company.getCompanyID());
					bage1.setBageKind(BageKindEnum.ALARM.getIntValue());
					bage1.setNum(getBageNumByCompany(company.getCompanyID(),BageKindEnum.ALARM.getIntValue()));
					rsp.getNumList().add(bage1);
					
					BageNumJson bage2 = new BageNumJson();
					bage2.setCompanyID(company.getCompanyID());
					bage2.setBageKind(BageKindEnum.STATUS.getIntValue());
					bage2.setNum(getBageNumByCompany(company.getCompanyID(), BageKindEnum.STATUS.getIntValue()));
					rsp.getNumList().add(bage2);
					
					BageNumJson bage3 = new BageNumJson();
					bage3.setCompanyID(company.getCompanyID());
					bage3.setBageKind(BageKindEnum.CHECK_LOG.getIntValue());
					bage3.setNum(getBageNumByCompany(company.getCompanyID(), BageKindEnum.CHECK_LOG.getIntValue()));
					rsp.getNumList().add(bage3);
					
				}
			}
			else
			{
				BageNumJson bage1= new BageNumJson();
				bage1.setBageKind(BageKindEnum.ALARM.getIntValue());
				bage1.setNum(getBageNumByUser(req.getUserID(),BageKindEnum.ALARM.getIntValue()));
				rsp.getNumList().add(bage1);
				
				BageNumJson bage2= new BageNumJson();
				bage2.setBageKind(BageKindEnum.STATUS.getIntValue());
				bage2.setNum(getBageNumByUser(req.getUserID(),BageKindEnum.STATUS.getIntValue()));
				rsp.getNumList().add(bage2);				

			}
	
			
		}
		catch(MISPException e)
		{
			log.error("app get InitData failed",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{		    
			log.error("app get InitData",e);
			rsp.setErrorCode(ErrorMessageConst.OPERATE_FAILED);
		}
		return rsp;
	}


	/**
     * 通用方法，根据公司编号查找各类提醒数字
     * @param companyID
     * @param bageKind
     * @return
     */
	private long getBageNumByCompany(int companyID, int bageKind)
	{
		long num =0;
		if(companyID!=0)
		{
			switch(BageKindEnum.getEnumByInt(bageKind))
			{
			case ALARM:
				num = ServiceContext.getInstance().getFireAlarmService()
				.getAlarmNumByCompany(companyID,AlarmClearEnum.NONE_CLEAR.getIntValue(),AlarmKindEnum.ALARM.getIntValue());
				break;
			case STATUS:
				num = ServiceContext.getInstance().getFireAlarmService()
				.getAlarmNumByCompany(companyID,AlarmClearEnum.NONE_CLEAR.getIntValue(),AlarmKindEnum.STATUS.getIntValue());
				break;
			case CHECK_LOG:
				num = ServiceContext.getInstance().getCheckLogService()
				.getLogNumByCompany(companyID, CheckLogStatusEnum.LATEST.getIntValue(), CheckResultEnum.ABNORMAL.getIntValue());
				break;
			default:
				break;
			}
		}

		return num;
	}
	/**
	 * 根据用户编号查找各类提醒
	 * @param userID
	 * @param bageKind
	 * @return
	 */
    private long getBageNumByUser(int userID, int bageKind)
	{
    	long num =0;
    	if(userID!=0)
    	{
    		switch(BageKindEnum.getEnumByInt(bageKind))
			{
			case ALARM:
				num = ServiceContext.getInstance().getFireAlarmService().getAlarmNumByUser(userID,AlarmClearEnum.NONE_CLEAR.getIntValue(),AlarmKindEnum.ALARM.getIntValue());
			case STATUS:
				num = ServiceContext.getInstance().getFireAlarmService().getAlarmNumByUser(userID,AlarmClearEnum.NONE_CLEAR.getIntValue(),AlarmKindEnum.STATUS.getIntValue());
				break;
			default:
				break;
			}
    	}
		return num;
	}


}
