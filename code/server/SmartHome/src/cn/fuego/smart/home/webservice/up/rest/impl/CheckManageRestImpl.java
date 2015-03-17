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

import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.CheckItem;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetCheckItemByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.CheckItemJson;
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



}
