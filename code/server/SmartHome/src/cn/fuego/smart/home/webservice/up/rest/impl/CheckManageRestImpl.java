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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.CheckLogStatusEnum;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.CheckItem;
import cn.fuego.smart.home.domain.CheckLog;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AppLoginCache;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.CreateCheckLogReq;
import cn.fuego.smart.home.webservice.up.model.CreateCheckLogRsp;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetCheckLogByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckLogByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.CheckItemJson;
import cn.fuego.smart.home.webservice.up.model.base.CheckLogJson;
import cn.fuego.smart.home.webservice.up.rest.CheckManageRest;


/** 
* @ClassName: CheckManageRestImpl 
* @Description: TODO
* @author Aether
* @date 2015-3-17 上午10:13:09 
*  
*/ 
public class CheckManageRestImpl implements CheckManageRest
{

	private Log log = LogFactory.getLog(CheckManageRestImpl.class);
	
	@Override
	public GetCheckItemByIDRsp getItemByID(GetCheckItemByIDReq req)
	{
		GetCheckItemByIDRsp rsp = new GetCheckItemByIDRsp();
		
		try
		{
			List<CheckItem> itemList= ServiceContext.getInstance().getCheckItemService().getCheckItemByID(req.getCompanyID());
			
			for(CheckItem item : itemList)
			{
				CheckItemJson json = ModelConvert.checkItemToJson(item);
				
				rsp.getCheckItemList().add(json);
				
			}
		} 
		catch(MISPException e)
		{
			log.error("Get CheckItem error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("Get CheckItem error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}

		return rsp;
	}

	@Override
	public CreateCheckLogRsp createCheckLog(CreateCheckLogReq req)
	{
		CreateCheckLogRsp rsp = new CreateCheckLogRsp();
		
		try
		{
			List<CheckLog> logList = new ArrayList<CheckLog>();
			for(CheckLogJson json:req.getCheckLogList())
			{
				CheckLog log= ModelConvert.jsonToCheckLog(json);
				//标识最新日志
				log.setStatus(CheckLogStatusEnum.LATEST.getIntValue());
				logList.add(log);
			}
 
			ServiceContext.getInstance().getCheckLogService().create(AppLoginCache.getUserID(req.getToken()), logList);
			
		}		
		catch(MISPException e)
		{
			log.error("Create CheckLog error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("Create CheckLog error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}
	
		return rsp;
	}

	@Override
	public GetCheckLogByIDRsp getCheckLogByID(GetCheckLogByIDReq req)
	{
		GetCheckLogByIDRsp rsp = new GetCheckLogByIDRsp();
		try
		{ 
 
 				List<CheckLog> logList= ServiceContext.getInstance().getCheckLogService().getCurrentLog(req.getCompanyID());
			
				for(CheckLog log : logList)
				{
					CheckLogJson json = ModelConvert.checkLogToJson(log);
					rsp.getCheckLogList().add(json);
				}
				
			 
		} 		
		catch(MISPException e)
		{
			log.error("get CheckLog error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get CheckLog error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}
	
		
		return rsp;
	}



}
