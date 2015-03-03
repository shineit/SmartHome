/**   
* @Title: SensorPlanManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午10:30:56 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.domain.SensorPlan;
import cn.fuego.smart.home.service.SensorPlanManageService;

 /** 
 * @ClassName: SensorPlanManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午10:30:56 
 *  
 */
public class SensorPlanManageServiceImpl extends MispCommonServiceImpl<SensorPlan> implements SensorPlanManageService
{

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#GetPrimaryName()
	 */
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return SensorPlan.PRI_KEY;
	}

	 

}
