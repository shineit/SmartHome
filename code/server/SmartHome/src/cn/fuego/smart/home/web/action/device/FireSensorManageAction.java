package cn.fuego.smart.home.web.action.device;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.service.FireSensorManageService;
import cn.fuego.smart.home.service.ServiceContext;

public class FireSensorManageAction extends DWZTableAction<FireSensor>
{
	private FireSensorManageService service = ServiceContext.getInstance().getFireSensorManageService();

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<FireSensor> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
 
	  

}
