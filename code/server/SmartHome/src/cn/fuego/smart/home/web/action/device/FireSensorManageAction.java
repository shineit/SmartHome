package cn.fuego.smart.home.web.action.device;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import cn.fuego.common.util.format.DataCreateUtil;
import cn.fuego.common.util.format.JsonConvert;
import cn.fuego.common.util.validate.ValidatorRules;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
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
import cn.fuego.smart.home.service.cache.SensorTypeCache;
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
	//excel 导出
	private InputStream excelStream ;
	private String excelName;

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

		//FireSensor sensorTemp=new FireSensor();
		try
		{
			ExcelTool tool = ExcelToolFactory.getInstance().getExcelTool();
			
			String filePath = MispConstant.getUploadPath() +File.separator+ this.saveUploadFile();
			List<FireSensor> sensorList = tool.readExcel(filePath, getSensorFireExcelMeta());
			FileUtil.deleteFile(filePath);
			
			//List<FireSensor> toCreate = new ArrayList<FireSensor>();
			//List<FireSensor> toModify = new ArrayList<FireSensor>();

			for(FireSensor sensor : sensorList)
			{
				//sensorTemp=sensor;
				sensor.setConcentratorID(company.getConcentratorID());
				sensor.setPlanNodeID(sensorPlan.getPlanID());
				sensor.setSensorTypeName(SensorTypeCache.getInstance().getSensorType(sensor.getSensorType()).getTypeName());
				
				service.validatorForCreate(sensor);
/*				if(sensor.getId()==0)
				{
					 
					service.validatorForCreate(sensor);
					toCreate.add(sensor);
				}
				else
				{
					toModify.add(sensor);
				}*/
				
 			}
		    service.create(this.getLoginUser().getUserID(), sensorList);
		    
			
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);

		}
		catch(MISPException e)
		{
			log.error("import sensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			//this.getOperateMessage().setMessage("sensor="+String.valueOf(sensorTemp.getMachineID()));
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
	public String downloadSensor()
	{
		try
		{
			ExcelTool tool = ExcelToolFactory.getInstance().getExcelTool();
			
			String filePath = MispConstant.getUploadPath() +File.separator+ DataCreateUtil.getUUID()+".xls";
			
			execute();
			List<FireSensor> sensorList = this.table.getDataSource().getAllPageData();
			tool.writeExcel(sensorList, filePath, getSensorFireExcelMeta());
			excelStream = new FileInputStream(new File(filePath));
			this.setDownloadFile(excelStream);
			excelName= "fireSensor"+".xls";
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			return "excel";

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
		ExcelMeta meta = new ExcelMeta(FireSensor.class,1);
/*
		ExcelColumnMeta column0 = new ExcelColumnMeta();
		column0.setColumnName("传感器编号");
		column0.setColumn(0);
		column0.setDataField("id");
		meta.getColumnMap().put(column0.getColumn(), column0);*/
		 
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
		meta.getColumnMap().put(column2.getColumn(), column2);
		column2.getRuleMap().put(ValidatorRules.isEmpty(), "点号不能为空");

		ExcelColumnMeta column3 = new ExcelColumnMeta();
		column3.setColumnName("位置描述");
		column3.setColumn(3);
		column3.setDataField("locationDesp");
		meta.getColumnMap().put(column3.getColumn(), column3);
		
		ExcelColumnMeta column4 = new ExcelColumnMeta();
		column4.setColumnName("传感器类型编码");
		column4.setColumn(4);
		column4.setDataField("sensorType");
		meta.getColumnMap().put(column4.getColumn(), column4);
		column4.getRuleMap().put(ValidatorRules.isEmpty(), "传感器类型码不能为空");

		ExcelColumnMeta column5 = new ExcelColumnMeta();
		column5.setColumnName("联系人");
		column5.setColumn(5);
		column5.setDataField("contacts");
		meta.getColumnMap().put(column5.getColumn(), column5);
	
		ExcelColumnMeta column6 = new ExcelColumnMeta();
		column6.setColumnName("联系人电话");
		column6.setColumn(6);
		column6.setDataField("contactPhone");
		meta.getColumnMap().put(column6.getColumn(), column6);
		
		
		ExcelColumnMeta column7 = new ExcelColumnMeta();
		column7.setColumnName("传感器X坐标");
		column7.setColumn(7);
		column7.setDataField("locationX");
		meta.getColumnMap().put(column7.getColumn(), column7);

		
		ExcelColumnMeta column8 = new ExcelColumnMeta();
		column8.setColumnName("传感器Y坐标");
		column8.setColumn(8);
		column8.setDataField("locationY");
		meta.getColumnMap().put(column8.getColumn(), column8);
		
		return meta;
	}

	public String downloadFireSensor()
	{
		
		return MISP_DONE_PAGE;
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

		Company company =  ServiceContext.getInstance().getCompanyManageService().get(companyID);
		
		if(TableOperateTypeEnum.CREATE.getType().equals(getOperateType()))
        {
			if(!ValidatorUtil.isEmpty(this.getSelectedID()))
			{
				this.getObj().setPlanNodeID(Integer.valueOf(this.getSelectedID()));
			   
				this.getObj().setConcentratorID(company.getConcentratorID());
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




	public InputStream getExcelStream()
	{
		return excelStream;
	}




	public void setExcelStream(InputStream excelStream)
	{
		this.excelStream = excelStream;
	}




	public String getExcelName()
	{
		return excelName;
	}




	public void setExcelName(String excelName)
	{
		this.excelName = excelName;
	}

}
