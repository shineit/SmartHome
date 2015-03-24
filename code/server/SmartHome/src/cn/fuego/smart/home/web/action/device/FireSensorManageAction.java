package cn.fuego.smart.home.web.action.device;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.constant.TableOperateTypeEnum;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.smart.home.domain.Building;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.SensorPlan;
import cn.fuego.smart.home.service.FireSensorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.BuildingModel;

public class FireSensorManageAction extends DWZTableAction<FireSensor>
{
	private Log log = LogFactory.getLog(FireSensorManageAction.class);

	private FireSensorManageService service = ServiceContext.getInstance().getFireSensorManageService();

	private static String companyID;
	private static Company company;
	
	
	private static List<BuildingModel> buildingList = new ArrayList<BuildingModel>();
	
	private SensorPlan sensorPlan;
	
	private String locationJson;
	private String sensorJson;
	//用于页面搜索关键字
	private String 	machineID,loopID,codeID;
	//用户关联之后的集中器编号列表
	private List<String> concentIDList=new ArrayList<String>();
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<FireSensor> getService()
	{
		// TODO Auto-generated method stub
		return service;
	}
	
 


	@Override
	public String execute()
	{
		loadTreeData(companyID);

		this.sensorPlan = ServiceContext.getInstance().getPlanManageService().get(this.getSelectedID());
		List<FireSensor> sensorList = service.get("planNodeID",this.getSelectedID());
		locationJson = JsonConvert.ObjectToJson(sensorList);


		return super.execute();
	}
	
	
	
	private List<String> loadConcentIDList(String companyID)
	{
		List<String> idList=new ArrayList<String>();
		try
		{
			Set<String> objIDlist=MISPServiceContext.getInstance().getMISPPrivilegeManage().getUserIDListByCommpany(companyID);
			if(!ValidatorUtil.isEmpty(objIDlist))
			{
				List<String> userIDList = new ArrayList<String>();
				userIDList.addAll(objIDlist);
				idList=service.getConcentIDListByUser(userIDList);
			}

		} 		
		catch (MISPException e)
		{
			
			log.error("load ConcentIDList failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			
			log.error("load ConcentIDList failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return idList;
	}




	@Override
	public List<QueryCondition> getFilterCondition()
	{
 		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(!ValidatorUtil.isEmpty(this.getSelectedID()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"planNodeID",this.getSelectedID()));
 		}
		if(!ValidatorUtil.isEmpty(this.getMachineID()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"machineID",this.getMachineID()));
 		} 
		if(!ValidatorUtil.isEmpty(this.getLoopID()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"loopID",this.getLoopID()));
 		} 
		if(!ValidatorUtil.isEmpty(this.getCodeID()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"codeID",this.getCodeID()));
 		} 
		return conditionList;
	}


	public  void loadTreeData(String companyID)
	{
		company = ServiceContext.getInstance().getCompanyManageService().get(companyID);
		buildingList.clear();
		List<Building> list =  ServiceContext.getInstance().getBuildingManageService().get("companyID", company.getCompanyID());
		for(Building build : list)
		{
			BuildingModel model = new BuildingModel();
			model.setBuilding(build);
			model.setPlanList(ServiceContext.getInstance().getPlanManageService().get("buildingID", build.getBuildingID()));
			buildingList.add(model);
		}
 	}



	public String modifyLocation()
	{
		boolean result = true;;
        try
		{
        	FireSensor sensor = (FireSensor) JsonConvert.jsonToObject(sensorJson, FireSensor.class);
 
        	service.modify(sensor);

		} catch (Exception e)
		{
			result = false;
			log.error("modify loaction failed",e);
		} 
    
    	HttpServletResponse response = ServletActionContext.getResponse();   
    	response.setContentType("text/html"); //火狐浏览器必须加上这句  
        response.setCharacterEncoding("UTF-8");
		try
		{
			response.getWriter().print(result);
		} catch (IOException e)
		{
			log.error("rsp failed");
		}
         return null;
	}
 
	public String getLocationJson()
	{
		return locationJson;
	}
	public void setLocationJson(String locationJson)
	{
		this.locationJson = locationJson;
	}

	@Override
	public String show()
	{
		//初始化加载用户关联后的集中器列表
		concentIDList=loadConcentIDList(companyID);
		if(TableOperateTypeEnum.CREATE.getType().equals(getOperateType()))
        {
			if(!ValidatorUtil.isEmpty(this.getSelectedID()))
			{
				this.getObj().setPlanNodeID(Integer.valueOf(this.getSelectedID()));
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




	public String getSensorJson()
	{
		return sensorJson;
	}






	public void setSensorJson(String sensorJson)
	{
		this.sensorJson = sensorJson;
	}




	public String getCompanyID()
	{
		return companyID;
	}




	public void setCompanyID(String companyID)
	{
		this.companyID = companyID;
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




	public SensorPlan getSensorPlan()
	{
		return sensorPlan;
	}




	public void setSensorPlan(SensorPlan sensorPlan)
	{
		this.sensorPlan = sensorPlan;
	}




	public String getMachineID()
	{
		return machineID;
	}




	public void setMachineID(String machineID)
	{
		this.machineID = machineID;
	}




	public String getLoopID()
	{
		return loopID;
	}




	public void setLoopID(String loopID)
	{
		this.loopID = loopID;
	}




	public String getCodeID()
	{
		return codeID;
	}




	public void setCodeID(String codeID)
	{
		this.codeID = codeID;
	}




	public List<String> getConcentIDList()
	{
		return concentIDList;
	}




	public void setConcentIDList(List<String> concentIDList)
	{
		this.concentIDList = concentIDList;
	}
	
	
 
	  

}
