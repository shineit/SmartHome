/**   
* @Title: ConcentManageRestImpl.java 
* @Package cn.fuego.smart.home.webservice.up.rest.impl 
* @Description: TODO
* @author Aether
* @date 2015-1-20 下午2:27:39 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.ConcentratorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.service.cache.AppLoginCache;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetConcentratorListReq;
import cn.fuego.smart.home.webservice.up.model.GetConcentratorListRsp;
import cn.fuego.smart.home.webservice.up.model.SetConcentratorReq;
import cn.fuego.smart.home.webservice.up.model.SetConcentratorRsp;
import cn.fuego.smart.home.webservice.up.model.base.ConcentratorJson;
import cn.fuego.smart.home.webservice.up.rest.ConcentManageRest;

/** 
 * @ClassName: ConcentManageRestImpl 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-20 下午2:27:39 
 *  
 */
public class ConcentManageRestImpl implements ConcentManageRest
{
	private Log log = LogFactory.getLog(ConcentManageRestImpl.class);

	private ConcentratorManageService concentService = ServiceContext.getInstance().getConcentratorManageService();
	@Override
	public GetConcentratorListRsp getConcentList(GetConcentratorListReq req)
	{
		GetConcentratorListRsp rsp = new GetConcentratorListRsp();
		try
		{

			List<Long> concentIDList = new ArrayList<Long>();
			List<UserConcentrator> userConcentList= concentService.getUserConcentListByID(req.getUserID());
			for(UserConcentrator uc:userConcentList)
			{
				concentIDList.add(uc.getConcentratorID());
				
			}

			List<Concentrator> concentList = concentService.getConcentListByID(concentIDList);
	 		
			for(Concentrator concentrator :concentList)
			{	
				ConcentratorJson json = ModelConvert.concentratorToJson(concentrator);
 
				rsp.getConcentList().add(json);
			}
			
		}
		catch(Exception e)
		{
			log.error("get concentList error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}

	@Override
	public SetConcentratorRsp modifyConcent(SetConcentratorReq req)
	{
		SetConcentratorRsp rsp = new SetConcentratorRsp();
		
		try
		{
			Concentrator concentrator = ModelConvert.jsonToConcentrator(req.getConcentrator());
			int userID = AppLoginCache.getLoginInfo(req.getToken()).getUser().getUserID();
			concentService.modify(userID,concentrator);//与web端修改保持一致
		} catch (Exception e)
		{
			log.error("modify Concent error",e);
			rsp.setErrorCode(ErrorMessageConst.OPERATE_FAILED);
		}
 		
		return rsp;
	}

}
