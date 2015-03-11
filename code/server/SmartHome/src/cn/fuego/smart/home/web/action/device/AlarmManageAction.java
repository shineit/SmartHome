/**   
* @Title: AlarmManageAction.java 
* @Package cn.fuego.smart.home.web.action.device 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-7 下午4:07:27 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.device;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.Alarm;
import cn.fuego.smart.home.service.AlarmManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.action.info.KnowledgeManageAction;

 /** 
 * @ClassName: AlarmManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-7 下午4:07:27 
 *  
 */
public class AlarmManageAction extends DWZTableAction<Alarm>
{
	private Log log = LogFactory.getLog(KnowledgeManageAction.class);
	
	
	private static final long serialVersionUID = 1L;
	private AlarmManageService service = ServiceContext.getInstance().getAlarmManageService();
	
	public String loadFireAlarm()
	{
		
		return "fireAlarm";
	}
	
	public String loadHomeAlarm()
	{
		return "homeAlarm";
	}
	
	@Override
	public MispCommonService<Alarm> getService()
	{
		// TODO Auto-generated 
 		return service;
	}
	
	

}
