/**   
* @Title: OrgManageService.java 
* @Package cn.fuego.smart.home.service 
* @Description: TODO
* @author Tang Jun   
* @date 2015-6-3 下午3:51:36 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service;

import java.util.List;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.smart.home.domain.Organization;

 /** 
 * @ClassName: OrgManageService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-6-3 下午3:51:36 
 *  
 */
public interface OrgManageService extends MispCommonService<Organization>
{
	public List<String> getAllParent(String org_id);

}
