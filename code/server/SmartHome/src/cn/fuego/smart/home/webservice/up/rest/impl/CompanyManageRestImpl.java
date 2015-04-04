/**   
* @Title: NewsManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:22:37 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.SensorPlan;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorPlanByIDRsp;
import cn.fuego.smart.home.webservice.up.model.base.CompanyJson;
import cn.fuego.smart.home.webservice.up.model.base.SensorPlanJson;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCompanyByIDReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCompanyByIDRsp;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCompanyListReq;
import cn.fuego.smart.home.webservice.up.model.enterprise.GetCompanyListRsp;
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
	@Override
	public GetCompanyListRsp getCompanyList(GetCompanyListReq req)
	{
		GetCompanyListRsp rsp = new GetCompanyListRsp();
		List<Company> companyList = ServiceContext.getInstance().getCompanyManageService().getCompanyList(req.getUserID());

		for(Company company : companyList)
		{
			CompanyJson json = ModelConvert.companyToJson(company);
			rsp.getCompanyList().add(json);	
		}
		
		return rsp;
	}

	@Override
	public GetCompanyByIDRsp getCompanyByID(GetCompanyByIDReq req)
	{
		GetCompanyByIDRsp rsp = new GetCompanyByIDRsp();
		
		try
		{
			List<QueryCondition> conditionList= new ArrayList<QueryCondition>();
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "companyID", req.getCompanyID()));
			Company company =DaoContext.getInstance().getCompanyDao().getUniRecord(conditionList);
			if(null!=company)
			{
				CompanyJson json = ModelConvert.companyToJson(company);
				rsp.setCompany(json);
			}
			
		}		
		catch(MISPException e)
		{
			log.error("get Company By ID error,the ID is:"+req.getCompanyID(),e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get Company By ID error,the ID is:"+req.getCompanyID(),e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}
		return rsp;
	}


}
