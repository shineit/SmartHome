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
import cn.fuego.smart.home.domain.CheckItem;
import cn.fuego.smart.home.service.CheckItemManageService;
import cn.fuego.smart.home.service.ServiceContext;
/** 
* @ClassName: CheckItemManageAction 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午3:14:08 
*  
*/ 
public class CheckItemManageAction extends DWZTableAction<CheckItem>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CheckItemManageService service  = ServiceContext.getInstance().getCheckItemService();

	
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<CheckItem> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}


}
