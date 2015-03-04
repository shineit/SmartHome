package cn.fuego.smart.home.web.action.device;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.action.login.LoginAction;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.service.FireSensorManageService;
import cn.fuego.smart.home.service.ServiceContext;

public class FireSensorManageAction extends DWZTableAction<FireSensor>
{
	private Log log = LogFactory.getLog(FireSensorManageAction.class);

	private FireSensorManageService service = ServiceContext.getInstance().getFireSensorManageService();

	private String locationJson;
	private String sensorJson;
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<FireSensor> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
	
	
	
	
	
	
	@Override
	public String execute()
	{
		List<FireSensor> sensorList = service.get("planNodeID",this.getSelectedID());
		
		locationJson = JsonConvert.ObjectToJson(sensorList);

		return super.execute();
	}
	
	
	
	@Override
	public List<QueryCondition> getFilterCondition()
	{
		// TODO Auto-generated method stub
		return super.getFilterCondition();
	}






	public String modifyLocation()
	{
		boolean result = true;;
        try
		{
        	FireSensor sensor = (FireSensor) JsonConvert.jsonToObject(sensorJson, FireSensor.class);
 
        	service.modify(sensor);

		} catch (Exception e)
		{
			result = false;
			log.error("modify loaction failed",e);
		} 
    
    	HttpServletResponse response = ServletActionContext.getResponse();   
    	response.setContentType("text/html"); //火狐浏览器必须加上这句  
        response.setCharacterEncoding("UTF-8");
		try
		{
			response.getWriter().print(result);
		} catch (IOException e)
		{
			log.error("rsp failed");
		}
         return null;
	}
 
	public String getLocationJson()
	{
		return locationJson;
	}
	public void setLocationJson(String locationJson)
	{
		this.locationJson = locationJson;
	}






	public String getSensorJson()
	{
		return sensorJson;
	}






	public void setSensorJson(String sensorJson)
	{
		this.sensorJson = sensorJson;
	}
	
	
 
	  

}
