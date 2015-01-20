package cn.fuego.smart.home.service;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.smart.home.constant.SensorKindEunm;
import cn.fuego.smart.home.ui.model.SpinnerDataModel;
import cn.fuego.smart.home.webservice.up.model.GetSensorListReq;
import cn.fuego.smart.home.webservice.up.model.GetSensorListRsp;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListReq;
import cn.fuego.smart.home.webservice.up.model.GetUserMarkListRsp;
import cn.fuego.smart.home.webservice.up.model.base.HomeSensorJson;
import cn.fuego.smart.home.webservice.up.model.base.UserMarkJson;
import cn.fuego.smart.home.webservice.up.rest.WebServiceContext;

public class SensorDataCache extends MispHttpHandler
{
	private List<HomeSensorJson> ctrSensorList = new ArrayList<HomeSensorJson>();
	private List<HomeSensorJson> measureSensorList = new ArrayList<HomeSensorJson>();
	private List<SpinnerDataModel> ctrSpinnerList = new ArrayList<SpinnerDataModel>();
	private int errorCode = 0;
	private static SensorDataCache instance;
	
	private HttpListener listener;
	private List<String> markList= new ArrayList<String>();
	private SensorDataCache()
	{
		//load();
		loadMarkList();
	}
	synchronized public static SensorDataCache getInstance()
	{
		if(null == instance)
		{
			instance = new SensorDataCache();
		}
		return instance;
	}
	public void load(HttpListener listener)
	{
		this.listener = listener;
		GetSensorListReq req = new GetSensorListReq();
		req.setToken(MemoryCache.getToken());
		req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
		
		WebServiceContext.getInstance().getSensorManageRest(this).getSensorList(req);
		 
	}

	public List<HomeSensorJson> getCtrSensorList()
	{
		return ctrSensorList;
	}

	public List<HomeSensorJson> getMeasureSensorList()
	{
		return measureSensorList;
	}
	
	public List<SpinnerDataModel> getCtrSpinnerList()
	{
		return ctrSpinnerList;
	}

	public List<HomeSensorJson> getCtrSensorListByConcent(int concentratorID)
	{
		List<HomeSensorJson>  sensorList = new ArrayList<HomeSensorJson>();
		for(HomeSensorJson json : this.ctrSensorList)
		{
			if(concentratorID == json.getConcentratorID())
			{
				sensorList.add(json);
			}
		}
	
	
		return sensorList;
	}
	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			errorCode = message.getErrorCode();
			GetSensorListRsp rsp =(GetSensorListRsp) message.getMessage().obj;
			ctrSensorList.clear();
			ctrSpinnerList.clear();
			measureSensorList.clear();
			for(HomeSensorJson json : rsp.getSensorList())
			{
				switch (SensorKindEunm.getEnumByInt(json.getSensorKind()))
				{
				case CTRL_SENSOR:
					ctrSensorList.add(json);
					SpinnerDataModel spinnerData= new SpinnerDataModel();
					//spinnerData.setValue(String.valueOf(json.getId()));
					spinnerData.setCtrSensorID(String.valueOf(json.getSensorID()));
					spinnerData.setCtrChannelID(String.valueOf(json.getChannelID()));
					spinnerData.setText(json.getSensorTypeName()+"位于"+json.getMark());
					ctrSpinnerList.add(spinnerData);
					break;
				case DISCRETE_SENSOR:
					
				case CONTIUOUS_SENSOR:
					measureSensorList.add(json);

					break;
				default:
					break;

				}

			}
			listener.handle(message);
		}
	}
	public int getErrorCode()
	{
		return errorCode;
	}

	public List<String> loadMarkList()
	{
		
	
    	GetUserMarkListReq req  = new GetUserMarkListReq();
    	req.setToken(MemoryCache.getToken());
    	req.setUserID(MemoryCache.getLoginInfo().getUser().getUserID());
    	WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler(){
    		@Override
    		public void handle(MispHttpMessage msg)
    		{
				if (msg.isSuccess())
				{
					GetUserMarkListRsp rsp = (GetUserMarkListRsp) msg.getMessage().obj;
					List<UserMarkJson> userMarkList = rsp.getMarkList();
					for(int i=0;i<userMarkList.size();i++)
					{
						markList.add(userMarkList.get(i).getMark());
						
					}
					markList.add("未分组");

				}
				else
				{
					super.sendMessage(msg);
				}
    		}
    	}).getUserMarkList(req);
		return markList;
		
	}
	public List<String> getMarkList()
	{
		return markList;
	}
	
 
	

}
