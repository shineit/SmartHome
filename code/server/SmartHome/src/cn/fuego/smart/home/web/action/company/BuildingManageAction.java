/**   
* @Title: BuildingManageAction.java 
* @Package cn.fuego.smart.home.web.action.company 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:39:53 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.company;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.smart.home.domain.Building;
import cn.fuego.smart.home.service.BuildingManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.BuildingModel;

 /** 
 * @ClassName: BuildingManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午6:39:53 
 *  
 */
public class BuildingManageAction extends DWZTableAction<Building>
{
	private List<BuildingModel> buildingList = new ArrayList<BuildingModel>();
	private BuildingManageService service = ServiceContext.getInstance().getBuildingManageService();

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Building> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}

}
