/**   
* @Title: PlanManageAction.java 
* @Package cn.fuego.smart.home.web.action.company 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:40:07 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.company;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.SensorPlan;

 /** 
 * @ClassName: PlanManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午6:40:07 
 *  
 */
public class PlanManageAction extends DWZTableAction<SensorPlan>
{

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<SensorPlan> getService()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
