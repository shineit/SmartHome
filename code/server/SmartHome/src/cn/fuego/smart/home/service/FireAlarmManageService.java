/**   
* @Title: FireAlarmManageAction.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Aether
* @date 2015-3-11 下午5:20:23 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.domain.FireAlarmView;
import cn.fuego.smart.home.webservice.up.model.base.AttributeJson;

/** 
 * @ClassName: FireAlarmManageAction 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-11 下午5:20:23 
 *  
 */
public interface FireAlarmManageService extends MispCommonService<FireAlarmView>
{

	//获取企业端告警信息列表通用方法，支持告警、状态、历史告警
	List<FireAlarmView> getFireAlarmByCompany(String companyID, int startNum,int pageSize, List<AttributeJson> filterList);
}
