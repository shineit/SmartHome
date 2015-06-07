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

	//获取企业端告警信息列表通用方法，支持告警、状态、查看历史告警
	List<FireAlarmView> getFireAlarmByCompany(String companyID, int startNum,int pageSize,
			List<AttributeJson> filterList, int status);

	//根据公司编号获取当前告警数目
	long getAlarmNumByCompany(int companyID, int status, int alarmKind);
    //根据用户编号获取各种告警数目
	long getAlarmNumByUser(int userID, int status, int alarmKind);

}
