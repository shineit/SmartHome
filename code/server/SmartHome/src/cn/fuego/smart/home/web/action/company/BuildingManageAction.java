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

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.constant.TableOperateTypeEnum;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Building;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.SensorPlan;
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
	
	private Company company;
	private BuildingManageService service = ServiceContext.getInstance().getBuildingManageService();
	private List<BuildingModel> buildingList = new ArrayList<BuildingModel>();

	
 

	@Override
	public List<QueryCondition> getFilterCondition()
	{
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(this.getSelectedID()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"companyID",this.getSelectedID()));
 		}
 
		return conditionList;
	}
	
	public  String loadTreeData()
	{
		company = ServiceContext.getInstance().getCompanyManageService().get(this.getSelectedID());
	 
		List<Building> list = service.get("companyID", company.getCompanyID());
		for(Building build : list)
		{
			BuildingModel model = new BuildingModel();
			model.setBuilding(build);
			model.setPlanList(ServiceContext.getInstance().getPlanManageService().get("buildingID", build.getBuildingID()));
			this.buildingList.add(model);
		}
		
		super.execute();
		
		return "all";
	}



	@Override
	public String show()
	{
		if(TableOperateTypeEnum.CREATE.getType().equals(getOperateType()))
        {
			if(!ValidatorUtil.isEmpty(this.getSelectedID()))
			{
				this.getObj().setCompanyID(Integer.valueOf(this.getSelectedID()));
			}
		}
		else
		{
			if(!ValidatorUtil.isEmpty(this.getSelectedID()))
			{
				this.setObj(getService().get(this.getSelectedID()));
			}
		}
		return this.getNextPage();
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Building> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}



	public Company getCompany()
	{
		return company;
	}



	public void setCompany(Company company)
	{
		this.company = company;
	}



	public List<BuildingModel> getBuildingList()
	{
		return buildingList;
	}



	public void setBuildingList(List<BuildingModel> buildingList)
	{
		this.buildingList = buildingList;
	}
	
	
	

}
