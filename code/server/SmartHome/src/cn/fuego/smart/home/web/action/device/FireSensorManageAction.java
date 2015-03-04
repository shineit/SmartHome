package cn.fuego.smart.home.web.action.device;

import java.util.List;

import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.service.FireSensorManageService;
import cn.fuego.smart.home.service.ServiceContext;

public class FireSensorManageAction extends DWZTableAction<FireSensor>
{
	private FireSensorManageService service = ServiceContext.getInstance().getFireSensorManageService();

	private String locationJson;
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
 
	public String getLocationJson()
	{
		return locationJson;
	}
	public void setLocationJson(String locationJson)
	{
		this.locationJson = locationJson;
	}
	
	
 
	  

}
