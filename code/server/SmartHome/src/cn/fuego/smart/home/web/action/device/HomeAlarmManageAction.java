/**   
* @Title: HomeAlarmManageAction.java 
* @Package cn.fuego.smart.home.web.action.device 
* @Description: TODO
* @author Aether
* @date 2015-3-11 下午5:07:24 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.device;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.HomeAlarmView;
import cn.fuego.smart.home.service.HomeAlarmManageService;
import cn.fuego.smart.home.service.ServiceContext;

/** 
 * @ClassName: HomeAlarmManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-11 下午5:07:24 
 *  
 */
public class HomeAlarmManageAction extends DWZTableAction<HomeAlarmView>
{

	private HomeAlarmManageService service = ServiceContext.getInstance().getHomeAlarmService();
	
	@Override
	public MispCommonService<HomeAlarmView> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}

}
