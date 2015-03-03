/**   
* @Title: CompanyManageAction.java 
* @Package cn.fuego.smart.home.web.action.company 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:39:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.company;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.service.CompanyManageService;
import cn.fuego.smart.home.service.ServiceContext;


 /** 
 * @ClassName: CompanyManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午6:39:35 
 *  
 */
public class CompanyManageAction extends DWZTableAction<Company>
{
	private CompanyManageService concentService = ServiceContext.getInstance().getCompanyManageService();

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Company> getService()
	{
		// TODO Auto-generated method stub
		return concentService;
	}

}
