/**   
* @Title: NewsManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:22:37 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.News;
import cn.fuego.smart.home.domain.SensorPlan;
import cn.fuego.smart.home.service.PlanManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetNewsByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.NewsJson;
import cn.fuego.smart.home.webservice.up.model.base.SensorPlanJson;
import cn.fuego.smart.home.webservice.up.rest.CompanyManageRest;

 /** 
 * @ClassName: NewsManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:22:37 
 *  
 */
public class CompanyManageRestImpl implements CompanyManageRest
{
	private Log log = LogFactory.getLog(CompanyManageRestImpl.class);

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.up.rest.CompanyManageRest#getPlanByID(cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDReq)
	 */
	@Override
	public GetSensorPlanByIDRsp getPlanByID(GetSensorPlanByIDReq req)
	{
		GetSensorPlanByIDRsp rsp = new GetSensorPlanByIDRsp();
		
		try
		{
			SensorPlan plan = ServiceContext.getInstance().getPlanManageService().get(req.getPlanID());
			SensorPlanJson json = ModelConvert.planToJson(plan);
		    rsp.setPlan(json);
		}
		catch(MISPException e)
		{
			log.error("get alarm error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get alarm error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}
 
		return rsp;
	}
 

}
