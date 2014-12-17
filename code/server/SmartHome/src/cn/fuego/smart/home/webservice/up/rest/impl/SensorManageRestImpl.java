/**   
* @Title: SensorManageServiceImpl.java 
* @Package cn.fuego.smart.home.webservice.from.client.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-20 上午11:24:29 
* @version V1.0   
*/ 
package cn.fuego.smart.home.webservice.up.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.service.MISPException;
import cn.fuego.smart.home.constant.AlarmObjTypeEnmu;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.constant.SensorSetCmdEnum;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.domain.HomeAlarmView;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.service.SensorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.webservice.ModelConvert;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorReq;
import cn.fuego.smart.home.webservice.up.model.BatchOperateSensorRsp;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.ClearAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetAlarmByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListReq;
import cn.fuego.smart.home.webservice.up.model.GetHistoryAlarmListRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorByIDRsp;
import cn.fuego.smart.home.webservice.up.model.GetSensorListReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.up.model.SetSensorReq;
import cn.fuego.smart.home.webservice.up.model.SetSensorRsp;
import cn.fuego.smart.home.webservice.up.model.base.AlarmJson;
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
			List<HomeSensor> sensorList = sensorService.getDataSource().getAllPageData();
	 		
			for(HomeSensor sensor :sensorList)
			{	
				HomeSensorJson json = ModelConvert.homeSensorToJson(sensor);
 
				rsp.getSensorList().add(json);
			}
			
		}
		catch(Exception e)
		{
			log.error("get sensor list error",e);
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
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
		
		HomeSensor sensor = ModelConvert.jsonToHomeSensor(req.getSensor());
 		sensorService.setSensor(SensorSetCmdEnum.getEnumByInt(req.getCommand()), sensor);
		return rsp;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.webservice.from.client.rest.SensorManageRest#setSensor(cn.fuego.smart.home.webservice.from.client.model.GetHistoryAlarmListReq)
	 */
	@Override
	public GetHistoryAlarmListRsp getAlarmList(GetHistoryAlarmListReq req)
	{
		GetHistoryAlarmListRsp rsp = new GetHistoryAlarmListRsp();
		List<HomeAlarmView> alarmList = ServiceContext.getInstance().getAlarmManageService().getAlarmOfUser(req.getUserID());
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
			this.sensorService.disable(req.getSensorList());
 
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
			rsp.getResult().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			log.error("clear alarm error",e);
			rsp.getResult().setErrorCode(ErrorMessageConst.ERROR_QUREY_FAILED);
		}


 		
		return rsp;
	}

}
