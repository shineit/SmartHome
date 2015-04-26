/**   
* @Title: PlanManageAction.java 
* @Package cn.fuego.smart.home.web.action.company 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:40:07 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.company;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.constant.TableOperateTypeEnum;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.domain.Building;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.SensorPlan;
import cn.fuego.smart.home.service.PlanManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.BuildingModel;

 /** 
 * @ClassName: PlanManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午6:40:07 
 *  
 */
public class PlanManageAction extends DWZTableAction<SensorPlan>
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private static String companyID;
	private static Company company;
	private static List<BuildingModel> buildingList = new ArrayList<BuildingModel>();
	private PlanManageService service = ServiceContext.getInstance().getPlanManageService();
	
	private String planName,floor;
	
	private String oldPicName;

 	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<SensorPlan> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
	@Override
	public List<QueryCondition> getFilterCondition()
	{
		loadTreeData(companyID);
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(this.getSelectedID()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"buildingID",this.getSelectedID()));
 		}
		if(!ValidatorUtil.isEmpty(this.getPlanName()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"name",this.getPlanName()));
 		}
		if(!ValidatorUtil.isEmpty(this.getFloor()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"floor",this.getFloor()));
 		}
		return conditionList;
	}
	
	
	public  void loadTreeData(String companyID)
	{
		company = ServiceContext.getInstance().getCompanyManageService().get(companyID);
		buildingList.clear();
		List<Building> list = ServiceContext.getInstance().getBuildingManageService().get("companyID", company.getCompanyID());
		for(Building build : list)
		{
			BuildingModel model = new BuildingModel();
			model.setBuilding(build);
			model.setPlanList(ServiceContext.getInstance().getPlanManageService().get("buildingID", build.getBuildingID()));
			buildingList.add(model);
		}
		
  
	}
	
	
	@Override
	public String create()
	{
		
		//String fileName = saveUploadFile();
		String fileName = saveUploadFileCompress(620, 310);
		this.obj.setPicPath(fileName);
 

		return super.create();
	}
	
	
	@Override
	public String modify()
	{
		//String fileName = saveUploadFile();
		String fileName = saveUploadFileCompress(620, 310);
		if(!ValidatorUtil.isEmpty(fileName))
		{
			deleteUploadFileByName(oldPicName);
			this.obj.setPicPath(fileName);
		}
		else
		{
			this.obj.setPicPath(oldPicName);
		}
		return super.modify();
	}
	@Override
	public String show()
	{
		if(TableOperateTypeEnum.CREATE.getType().equals(getOperateType()))
        {
			if(!ValidatorUtil.isEmpty(this.getSelectedID()))
			{
				this.getObj().setBuildingID(Integer.valueOf(this.getSelectedID()));
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
			String planID = idList[i];
			if(sensorIsDeleted(planID))
			{
				super.deleteList();
			}
			else
			{
				log.error("delete plan  failed, the planID is"+planID);
				this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
				//this.getOperateMessage().setMessage("该公司还存在未删除楼层信息！");
				this.getOperateMessage().setErrorCode(ErrorMessageConst.SENSOR_LIST_NOT_DELETED);
				return MISP_DONE_PAGE;
			}
		}

		return MISP_DONE_PAGE;
	}
	

	private boolean sensorIsDeleted(String planID)
	{
		List<QueryCondition> conditionList= new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "planNodeID", planID));
		List<FireSensor> target = ServiceContext.getInstance().getFireSensorManageService().getDataSource(conditionList).getAllPageData();
		if(!ValidatorUtil.isEmpty(target))
		{
			return false;
		}
		return true;
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
	public String getPlanName()
	{
		return planName;
	}
	public void setPlanName(String planName)
	{
		this.planName = planName;
	}
	public String getFloor()
	{
		return floor;
	}
	public void setFloor(String floor)
	{
		this.floor = floor;
	}
	public String getOldPicName()
	{
		return oldPicName;
	}
	public void setOldPicName(String oldPicName)
	{
		this.oldPicName = oldPicName;
	}
 
	
}
