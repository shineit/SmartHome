package cn.fuego.smart.home.web.action.device;

import java.io.File;
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
import cn.fuego.common.util.file.FileUtil;
import cn.fuego.common.util.file.excel.ExcelColumnMeta;
import cn.fuego.common.util.file.excel.ExcelMeta;
import cn.fuego.common.util.file.excel.ExcelTool;
import cn.fuego.common.util.file.excel.ExcelToolFactory;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.common.util.validate.ValidatorRules;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.constant.MispConstant;
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
	
	private static SensorPlan sensorPlan;
	
	private String locationJson;
	private String sensorJson;
	//用于页面搜索关键字
	private String 	machineID,loopID,codeID;

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
	
	public String uploadSensor()
	{
		try
		{
			ExcelTool tool = ExcelToolFactory.getInstance().getExcelTool();
			
			String filePath = MispConstant.getUploadPath() +File.separator+ this.saveUploadFile();
			List<FireSensor> sensorList = tool.readExcel(filePath, getSensorFireExcelMeta());
			FileUtil.deleteFile(filePath);
			for(FireSensor sensor : sensorList)
			{
				sensor.setConcentratorID(company.getConcentratorID());
				sensor.setPlanNodeID(sensorPlan.getPlanID());
 			}
			
			service.create(this.getLoginUser().getUserID(), sensorList);
			
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);

		}
		catch(MISPException e)
		{
			log.error("import sensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch (Exception e)
		{
			log.error("import sensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		
		return MISP_DONE_PAGE;
		
	}
	
	private ExcelMeta getSensorFireExcelMeta()
	{
		ExcelMeta meta = new ExcelMeta(FireSensor.class,2);
		
		 
		ExcelColumnMeta column0 = new ExcelColumnMeta();
		column0.setColumnName("机号");
		column0.setColumn(0);
		column0.setDataField("machineID");
		column0.getRuleMap().put(ValidatorRules.isEmpty(), "机号不能为空");
		meta.getColumnMap().put(column0.getColumn(), column0);
		
		ExcelColumnMeta column1 = new ExcelColumnMeta();
		column1.setColumnName("回路号");
		column1.setColumn(1);
		column1.setDataField("loopID");
		column1.getRuleMap().put(ValidatorRules.isEmpty(), "回路号不能为空");
		meta.getColumnMap().put(column1.getColumn(), column1);

		ExcelColumnMeta column2 = new ExcelColumnMeta();
		column2.setColumnName("点号");
		column2.setColumn(2);
		column2.setDataField("codeID");
		meta.getColumnMap().put(column0.getColumn(), column2);
		column1.getRuleMap().put(ValidatorRules.isEmpty(), "点号不能为空");

		ExcelColumnMeta column3 = new ExcelColumnMeta();
		column3.setColumnName("位置");
		column3.setColumn(3);
		column3.setDataField("locationDesp");
		meta.getColumnMap().put(column0.getColumn(), column3);
		
		return meta;
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

}
