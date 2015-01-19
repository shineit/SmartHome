/**   
* @Title: MISPClientVersionServiceImpl.java 
* @Package cn.fuego.misp.service.impl 
* @Description: TODO
* @author Aether
* @date 2015-1-16 下午9:34:44 
* @version V1.0   
*/ 
package cn.fuego.misp.service.impl;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.constant.MISPClientVersionEnum;
import cn.fuego.misp.dao.MISPDaoContext;
import cn.fuego.misp.service.MISPClientVersionService;
import cn.fuego.smart.home.domain.ClientVersion;

/** 
 * @ClassName: MISPClientVersionServiceImpl 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-16 下午9:34:44 
 *  
 */
public class MISPClientVersionServiceImpl extends MispCommonServiceImpl<ClientVersion> implements MISPClientVersionService
{

	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientVersion getClientVersion()
	{
		ClientVersion clientVersion = MISPDaoContext.getInstance().getClientVersionDao().getUniRecord(new QueryCondition(ConditionTypeEnum.EQUAL, "versionStatus", MISPClientVersionEnum.VERSION_NEW.getIntValue()));
		return clientVersion;
	}

}
