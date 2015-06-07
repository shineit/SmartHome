/**   
* @Title: NewsManageSerivce.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午11:21:55 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.model.page.PageModel;
import cn.fuego.smart.home.domain.CheckLog;


/** 
* @ClassName: CheckLogManageService 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午2:33:56 
*  
*/ 
public interface CheckLogManageService extends MispCommonService<CheckLog>
{
 
	List<CheckLog> getCurrentLog(int companyID);

	//通用获取巡检日志的方法，
	//分页信息，状态
	List<CheckLog> getCheckLog(int companyID, PageModel page, int status);

	//根据公司编号获取异常巡检记录数
	long getLogNumByCompany(int companyID, int status,int result);
	

}
