/**   
* @Title: MISPOperLogService.java 
* @Package cn.fuego.misp.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-2 下午04:19:31 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

import java.util.List;

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.misp.domain.OperLog;
import cn.fuego.misp.web.model.log.LogFilterModel;

/** 
 * @ClassName: MISPOperLogService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-2 下午04:19:31 
 *  
 */

public interface MISPOperLogService
{
	void recordLog(String user,String opeateName,String object,String result,String desp);
	void recordLog(String user,String opeateName,String object,String result);

    AbstractDataSource<OperLog> getOperLogList(LogFilterModel filter);
	void deleteLog(List<String> logIDList);
	void deleteLog(String logID);
}
