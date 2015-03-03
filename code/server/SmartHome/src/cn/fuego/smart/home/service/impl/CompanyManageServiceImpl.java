/**   
* @Title: CompanyManageServiceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:45:39 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import cn.fuego.misp.service.impl.MispCommonServiceImpl;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.service.CompanyManageService;

 /** 
 * @ClassName: CompanyManageServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午6:45:39 
 *  
 */
public class CompanyManageServiceImpl extends MispCommonServiceImpl<Company>  implements  CompanyManageService 
{

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.impl.MispCommonServiceImpl#GetPrimaryName()
	 */
	@Override
	public String GetPrimaryName()
	{
		// TODO Auto-generated method stub
		return Company.PRI_KEY;
	}
 
}
