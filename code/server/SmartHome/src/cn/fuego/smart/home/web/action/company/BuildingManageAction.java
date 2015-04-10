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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.constant.TableOperateTypeEnum;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.smart.home.constant.ErrorMessageConst;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Log log = LogFactory.getLog(this.getClass());
	private static String companyID;
	private static Company company;
	private static List<BuildingModel> buildingList = new ArrayList<BuildingModel>();

	private BuildingManageService service = ServiceContext.getInstance().getBuildingManageService();

	private String buildingName;

	@Override
	public List<QueryCondition> getFilterCondition()
	{
		loadTreeData(companyID);
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(companyID))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"companyID",companyID));
 		}
 
		if(!ValidatorUtil.isEmpty(buildingName))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"name",buildingName));
		}
		return conditionList;
	}
	
	public  void loadTreeData(String companyID)
	{
		company = ServiceContext.getInstance().getCompanyManageService().get(companyID);
		buildingList.clear();
		List<Building> list = service.get("companyID", company.getCompanyID());
		for(Building build : list)
		{
			BuildingModel model = new BuildingModel();
			model.setBuilding(build);
			model.setPlanList(ServiceContext.getInstance().getPlanManageService().get("buildingID", build.getBuildingID()));
			buildingList.add(model);
		}
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

	
	@Override
	public String deleteList()
	{
		String[] idList = this.getSelectedIDList();
		for(int i=0;i<idList.length;i++)
		{
			String buildingID = idList[i];
			if(planIsDeleted(buildingID))
			{
				super.deleteList();
			}
			else
			{
				log.error("delete building  failed, the buildingID is"+buildingID);
				this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
				//this.getOperateMessage().setMessage("该公司还存在未删除楼层信息！");
				this.getOperateMessage().setErrorCode(ErrorMessageConst.PLAN_NOT_DELETED);
				return MISP_DONE_PAGE;
			}
		}

		return MISP_DONE_PAGE;
	}
	

	private boolean planIsDeleted(String buildingID)
	{
		List<QueryCondition> conditionList= new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "buildingID", buildingID));
		List<SensorPlan> target = ServiceContext.getInstance().getPlanManageService().getDataSource(conditionList).getAllPageData();
		if(!ValidatorUtil.isEmpty(target))
		{
			return false;
		}
		return true;
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

	public  String getCompanyID()
	{
		return companyID;
	}

	public  void setCompanyID(String companyID)
	{
		this.companyID = companyID;
	}

	public String getBuildingName()
	{
		return buildingName;
	}

	public void setBuildingName(String buildingName)
	{
		this.buildingName = buildingName;
	}

	

}
