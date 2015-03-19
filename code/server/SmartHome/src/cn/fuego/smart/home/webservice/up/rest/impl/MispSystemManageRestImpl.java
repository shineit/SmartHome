/**   
* @Title: MispSystemManageRestImpl.java 
* @Package cn.fuego.smart.home.webservice.up.rest.impl 
* @Description: TODO
* @author Aether
* @date 2015-1-16 下午9:22:52 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.constant.MISPClientVersionEnum;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.ClientVersion;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetClientVersionReq;
import cn.fuego.smart.home.webservice.up.model.GetClientVersionRsp;
import cn.fuego.smart.home.webservice.up.model.base.ClientVersionJson;
import cn.fuego.smart.home.webservice.up.rest.MispSystemManageRest;

/** 
 * @ClassName: MispSystemManageRestImpl 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-16 下午9:22:52 
 *  
 */
public class MispSystemManageRestImpl implements MispSystemManageRest
{
	private Log log = LogFactory.getLog(MispSystemManageRestImpl.class);

	@Override
	public GetClientVersionRsp getAppVersion(GetClientVersionReq req)
	{
		GetClientVersionRsp rsp = new GetClientVersionRsp();
		try
		{
			QueryCondition condition=new QueryCondition(ConditionTypeEnum.EQUAL, "versionStatus", MISPClientVersionEnum.VERSION_NEW.getIntValue());
			ClientVersion clientVersion = (ClientVersion) DaoContext.getInstance().getDao(ClientVersion.class).getUniRecord(condition);
			ClientVersionJson json= ModelConvert.versionToJson(clientVersion);
		    rsp.setObj(json);
		}
		catch(MISPException e)
		{
			log.error("get clientVersion error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get clientVersion error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}

		return rsp;
	}

}
