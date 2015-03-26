/**   
* @Title: SensorManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:24:29 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.PrivilegeAccessObjTypeEnum;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.web.model.page.PageModel;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
import cn.fuego.smart.home.domain.FireAlarmView;
import cn.fuego.smart.home.domain.HomeAlarmView;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.ConcentratorManageService;
import cn.fuego.smart.home.service.SensorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorReq;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorRsp;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetFireAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorListReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.up.model.SetSensorReq;
import cn.fuego.smart.home.webservice.up.model.SetSensorRsp;
import cn.fuego.smart.home.webservice.up.model.base.FireAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeAlarmJson;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.rest.SensorManageRest;

 /** 
 * @ClassName: SensorManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-20 上午11:24:29 
 *  
 */
public class SensorManageRestImpl implements SensorManageRest
{
	
	private Log log = LogFactory.getLog(SensorManageRestImpl.class);

	private SensorManageService sensorService = ServiceContext.getInstance().getSensorManageService();
	private ConcentratorManageService concentService = ServiceContext.getInstance().getConcentratorManageService();

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.SensorManageService#getWild()
	 */
	@Override
	public String getHello()
	{
		// TODO Auto-generated method stub
		return "helllo rest easy aaa";
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.SensorManageService#getSensorList(cn.fuego.smart.home.webservice.from.client.model.GetSensorListReq)
	 */
	@Override
	public GetSensorListRsp getSensorList(GetSensorListReq req)
	{
		// TODO Auto-generated method stub
		GetSensorListRsp rsp = new GetSensorListRsp();
		
		try
		{
			List<Long> concentIDList = new ArrayList<Long>();
			List<UserConcentrator> userConcentList= concentService.getUserConcentListByID(req.getUserID());
			for(UserConcentrator uc:userConcentList)
			{
				concentIDList.add(uc.getConcentratorID());
				
			}

			if(!ValidatorUtil.isEmpty(concentIDList))
			{
				List<HomeSensor> sensorList = sensorService.getSensorListByID(concentIDList);
				if(!ValidatorUtil.isEmpty(sensorList))
				{
					for(HomeSensor sensor :sensorList)
					{	
						HomeSensorJson json = ModelConvert.homeSensorToJson(sensor);
		 
						rsp.getSensorList().add(json);
					}
				}
				else
				{
					rsp.setErrorCode(ErrorMessageConst.SENSOR_NOT_EXISTED);
				}

			}
			else
			{
				rsp.setErrorCode(ErrorMessageConst.CONCENTRATOR_NOT_EXISTED);
			}

			
		}
		catch(Exception e)
		{
			log.error("get sensor list error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.service.SensorManageService#setSensor(cn.fuego.smart.home.webservice.from.client.model.SetSensorReq)
	 */
	@Override
	public SetSensorRsp setSensor(SetSensorReq req)
	{
		SetSensorRsp rsp = new SetSensorRsp();
		try
		{
			HomeSensor sensor = ModelConvert.jsonToHomeSensor(req.getSensor());
	 		sensorService.setSensor(SensorSetCmdEnum.getEnumByInt(req.getCommand()), sensor, req.getUserID());
 
		}
		catch(MISPException e)
		{
			log.error("set sensor error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("set sensor error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}

		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.rest.SensorManageRest#setSensor(cn.fuego.smart.home.webservice.from.client.model.GetHistoryAlarmListReq)
	 */
	@Override
	public GetHistoryAlarmListRsp getAlarmList(GetHistoryAlarmListReq req)
	{
		GetHistoryAlarmListRsp rsp = new GetHistoryAlarmListRsp();
		PageModel page = new PageModel();
		
		if(null != req.getPage())
		{
			page.setPageSize(req.getPage().getPageSize());
			page.setCurrentPage(req.getPage().getCurrentPage());
		}

		List<HomeAlarmView> alarmList = ServiceContext.getInstance().getAlarmManageService().getAlarmOfUser(req.getUserID(),page.getStartNum(),page.getPageSize(),req.getFilterList());
		for(HomeAlarmView alarmview : alarmList)
		{
			HomeAlarmJson alarmJson = ModelConvert.HomeAlarmToJson(alarmview);
			rsp.getAlarmList().add(alarmJson);
		}
		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.rest.SensorManageRest#enable(cn.fuego.smart.home.webservice.from.client.model.BatchSetSensorReq)
	 */
	@Override
	public BatchOperateSensorRsp enable(BatchOperateSensorReq req)
	{
		BatchOperateSensorRsp rsp = new BatchOperateSensorRsp();
		try
		{
			this.sensorService.enable(req.getSensorList(),req.getUserID());
 
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

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.rest.SensorManageRest#disable(cn.fuego.smart.home.webservice.from.client.model.BatchSetSensorReq)
	 */
	@Override
	public BatchOperateSensorRsp disable(BatchOperateSensorReq req)
	{
		BatchOperateSensorRsp rsp = new BatchOperateSensorRsp();
		try
		{
			this.sensorService.disable(req.getSensorList(),req.getUserID());
 
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
	public GetSensorByIDRsp getSensor(GetSensorByIDReq req)
	{
		GetSensorByIDRsp rsp = new GetSensorByIDRsp();
		
		try
		{
			HomeSensor sensor = this.sensorService.get(req.getSensorID());
		    HomeSensorJson json = ModelConvert.homeSensorToJson(sensor);
		    rsp.setSensor(json);
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
	public GetAlarmByIDRsp getAlarm(GetAlarmByIDReq req)
	{
		
		GetAlarmByIDRsp rsp = new GetAlarmByIDRsp();
		try
		{
	
		    HomeAlarmView homeAlarm = ServiceContext.getInstance().getAlarmManageService().getHomeAlarmByID(String.valueOf(req.getAlarmID()));
		    HomeAlarmJson json = ModelConvert.HomeAlarmToJson(homeAlarm);
		    rsp.setHomeAlarm(json);

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
	public ClearAlarmByIDRsp clearAlarm(ClearAlarmByIDReq req)
	{
		ClearAlarmByIDRsp rsp = new ClearAlarmByIDRsp();
		
		try
		{
 
		    ServiceContext.getInstance().getAlarmManageService().manualClear(req.getUserID(), Integer.valueOf(req.getAlarmID()));
 
		}
		catch(MISPException e)
		{
			log.error("clear alarm error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("clear alarm error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}

	@Override
	public ClearAlarmListRsp clearAlarm(ClearAlarmListReq req)
	{
		ClearAlarmListRsp rsp = new ClearAlarmListRsp();
		try
		{
 
		    ServiceContext.getInstance().getAlarmManageService().manualClearList(req.getUserID(), req.getAlarmIDList());
 
		}
		catch(MISPException e)
		{
			log.error("clear alarm error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("clear alarm error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}		
		return rsp;
	}

	@Override
	public GetFireAlarmByIDRsp getFireAlarm(GetFireAlarmByIDReq req)
	{
		GetFireAlarmByIDRsp rsp = new GetFireAlarmByIDRsp();
		try
		{
			PageModel page = new PageModel();
			
			if(null != req.getPage())
			{
				page.setPageSize(req.getPage().getPageSize());
				page.setCurrentPage(req.getPage().getCurrentPage());
			}
			List<FireAlarmView> fireAlarmList= new ArrayList<FireAlarmView>();
			//如果请求无companyID则根据userID查询
			if(ValidatorUtil.isEmpty(req.getCompanyID()))
			{
				Set<String> companyIDList=MISPServiceContext.getInstance().getMISPPrivilegeManage().getObjectIDListByUser(PrivilegeAccessObjTypeEnum.COMPANY.getObjectType(), String.valueOf(req.getUserID()));
				if(!ValidatorUtil.isEmpty(companyIDList))
				{
					for(String id:companyIDList)
					{
						List<FireAlarmView> tempList= ServiceContext.getInstance().getFireAlarmService().getFireAlarmByCompany(id,page.getStartNum(),page.getPageSize(),req.getFilterList());
						fireAlarmList.addAll(tempList);
					}
					
				}

			}
			else
			{
				fireAlarmList= ServiceContext.getInstance().getFireAlarmService().getFireAlarmByCompany(req.getCompanyID(),page.getStartNum(),page.getPageSize(),req.getFilterList());
			}
			
			
			for(FireAlarmView alarmview : fireAlarmList)
			{
				FireAlarmJson alarmJson = ModelConvert.FireAlarmToJson(alarmview);
				rsp.getFireAlarmList().add(alarmJson);
			}
		}
		catch(MISPException e)
		{
			log.error("get FireAlarm error",e);
			rsp.setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("get FireAlarm error",e);
			rsp.setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}	
		return rsp;
	}

}
