package cn.fuego.smart.home.web.action.device;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.constant.SensorKindEunm;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.HomeSensor;
import cn.fuego.smart.home.domain.SensorType;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.ConcentratorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.ConcentFilterModel;
import cn.fuego.smart.home.web.model.HomeSensorFilterModel;
import cn.fuego.smart.home.web.model.SensorTypeFilterModel;

public class ConcentratorManageAction extends DWZTableAction<Concentrator>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());
	
	private ConcentratorManageService concentService = ServiceContext.getInstance().getConcentratorManageService();
	private ConcentFilterModel filter = new ConcentFilterModel();
 	
	private TableDataModel<UserConcentrator> permissionTable = new TableDataModel<UserConcentrator>();
	private UserConcentrator userPermission;
	private String concentratorID;
	
	private List<SensorType> sensorTypeList;
	//传感器配置
	private TableDataModel<HomeSensor> homeSensorTable = new TableDataModel<HomeSensor>();
	private HomeSensorFilterModel hsFilter = new HomeSensorFilterModel();
	private HomeSensor homeSensor;
	//传感器类型列表
	private TableDataModel<SensorType> sensorTypeTable = new TableDataModel<SensorType>();
	private SensorTypeFilterModel stFilter= new SensorTypeFilterModel();
	private List<String> sysList = new ArrayList<String>();
    @Override
    public List<QueryCondition> getFilterCondition()
    {
    	return filter.getConidtionList();
    }
    @Override
    public String show()
    {
    	try
		{
    		this.setOperatePemission(concentService.getOperatePemission(this.getLoginUser().getUserID(),this.getSelectedID()));
			super.show();
		}catch (MISPException e)
		{
			
			log.error("show  permission-modify page failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			return MISP_DONE_PAGE;
		} 
    	catch (Exception e)
		{
			log.error("show  permission-modify page failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
			return MISP_DONE_PAGE;
		}
    	return "modify";
    }
    @Override
    public String delete()
    {
		super.delete();
		this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
    	return MISP_DONE_PAGE;
    	
    }
    
    public String syncSensor()
    {
    	try
		{
    		ServiceContext.getInstance().getSensorManageService().syncSensorList(Long.valueOf(this.getSelectedID()));
        	
		}catch(MISPException e)
		{
			log.error("syncSensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch (Exception e)
		{
			log.error("syncSensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return MISP_DONE_PAGE;
    }
    
	public String showPermission()
	{
		try
		{
			
			concentratorID = this.getSelectedID();
			List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID));
			permissionTable.setPage(this.getPage());
			permissionTable.setDataSource(concentService.getPermissionDataSource(this.getLoginUser().getAccountType(),conditionList));
		}catch (MISPException e)
		{
			
			log.error("show permission page failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			return MISP_DONE_PAGE;
		} 		
		catch (Exception e)
		{
			log.error("show permission page failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
			return MISP_DONE_PAGE;
		}
		return "addInfo";
		
	}

	public String addPermission()
	{
		
		try
		{
			concentService.addPermission(userPermission);
			this.getOperateMessage().setCallbackType(MispMessageModel.FORWARD);
			this.getOperateMessage().setNavTabId("peDialog");
		
		} catch (MISPException e)
		{
			
			log.error("create user-concentrator permission failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			
			log.error("create user-concentrator permission failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}

		
		return MISP_DONE_PAGE;
		
	}
	public String modifyPermission()
	{
		userPermission = concentService.getPermissionByID(this.getSelectedID(),this.getConcentratorID());
		return "modifyPermission";
		
	}
	public String modifySure()
	{
		concentService.modifyPermission(userPermission);
		this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
		this.getOperateMessage().setNavTabId("peDialog");
		return MISP_DONE_PAGE;
		
	}	
	public String deletePermission()
	{
		concentService.deletePermissionByID(this.getSelectedID(),this.getConcentratorID());
		this.getOperateMessage().setNavTabId("peDialog");
		return MISP_DONE_PAGE;
	}
    /**
     * 
     * 显示该集中器下传感器列表
     */
    public String showSensor()
    {
		try
		{
			
			concentratorID = this.getSelectedID();
			log.info("concentratorID :"+concentratorID);
			List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",concentratorID));
			if (!ValidatorUtil.isEmpty(hsFilter.getSensorID()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"id", hsFilter.getSensorID()));
			}
			if (!ValidatorUtil.isEmpty(hsFilter.getSensorKind()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"sensorKind", SensorKindEunm.getEnumByStr(hsFilter.getSensorKind()).getIntValue()));
			}
			homeSensorTable.setPage(this.getPage());
			homeSensorTable.setDataSource(concentService.getHomeSensorDataSource(conditionList));
		}catch (MISPException e)
		{
			
			log.error("show sensorList failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			return MISP_DONE_PAGE;
		} 		
		catch (Exception e)
		{
			log.error("show sensorList failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
			return MISP_DONE_PAGE;
		}
    	return "showSensor";
    	
    }
    /**
     * 
     * 传感器参数配置--显示
     */
    public String configSensor()
    {
		
		try
		{
			
			String sensorID=this.getSelectedID();
			homeSensor= concentService.getHomeSensorByID(sensorID);

		}catch (MISPException e)
		{
			
			log.error("show configSensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			return MISP_DONE_PAGE;
		} 		
		catch (Exception e)
		{
			log.error("show configSensor failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
			return MISP_DONE_PAGE;
		}
    	return "configSensor";
    	
    }
    /**
     * 
     * 传感器配置修改提交
     */
    public String configSensorSure()
    {
		try
		{
			concentService.modifySensor(homeSensor);
			//this.getOperateMessage().setCallbackType(MispMessageModel.FORWARD);
			//this.getOperateMessage().setNavTabId("configSensor");
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
		
		} catch (MISPException e)
		{
			
			log.error("configSensorSure failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			
			log.error("configSensorSure failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}

    	return MISP_DONE_PAGE;
    	
    }
    public String showSensorTypeList()
    {
		try
		{
			List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
			if (!ValidatorUtil.isEmpty(stFilter.getTypeName()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"typeName", stFilter.getTypeName()));
			}
			if (!ValidatorUtil.isEmpty(stFilter.getTypeSys()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"typeSys", stFilter.getTypeSys()));
			}
			sensorTypeTable.setPage(this.getPage());
			sensorTypeTable.setDataSource(concentService.getSensorTypeDatasource(conditionList));
			List<SensorType> sensorTypeList=sensorTypeTable.getDataSource().getAllPageData();
			for(SensorType type:sensorTypeList)
			{
				if(!this.getSysList().contains(type.getTypeSys()))
				{
					this.getSysList().add(type.getTypeSys());
				}
				
			}
		}catch (MISPException e)
		{
			
			log.error("show SensorTypeList failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			return MISP_DONE_PAGE;
		} 		
		catch (Exception e)
		{
			log.error("show SensorTypeList failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
			return MISP_DONE_PAGE;
		}

    	return "showType";
    	
    }
   
	public ConcentFilterModel getFilter()
	{
		return filter;
	}
	public void setFilter(ConcentFilterModel filter)
	{
		this.filter = filter;
	}
 
	public TableDataModel<UserConcentrator> getPermissionTable()
	{
		return permissionTable;
	}
	public void setPermissionTable(TableDataModel<UserConcentrator> permissionTable)
	{
		this.permissionTable = permissionTable;
	}
	public UserConcentrator getUserPermission()
	{
		return userPermission;
	}
	public void setUserPermission(UserConcentrator userPermission)
	{
		this.userPermission = userPermission;
	}
	public String getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(String concentratorID)
	{
		this.concentratorID = concentratorID;
	}


	public HomeSensorFilterModel getHsFilter()
	{
		return hsFilter;
	}
	public void setHsFilter(HomeSensorFilterModel hsFilter)
	{
		this.hsFilter = hsFilter;
	}
	public TableDataModel<HomeSensor> getHomeSensorTable()
	{
		return homeSensorTable;
	}
	public void setHomeSensorTable(TableDataModel<HomeSensor> homeSensorTable)
	{
		this.homeSensorTable = homeSensorTable;
	}
	
	public HomeSensor getHomeSensor()
	{
		return homeSensor;
	}
	public void setHomeSensor(HomeSensor homeSensor)
	{
		this.homeSensor = homeSensor;
	}
	

	public TableDataModel<SensorType> getSensorTypeTable()
	{
		return sensorTypeTable;
	}
	public void setSensorTypeTable(TableDataModel<SensorType> sensorTypeTable)
	{
		this.sensorTypeTable = sensorTypeTable;
	}

	public SensorTypeFilterModel getStFilter()
	{
		return stFilter;
	}
	public void setStFilter(SensorTypeFilterModel stFilter)
	{
		this.stFilter = stFilter;
	}
	public List<SensorType> getSensorTypeList()
	{
		return sensorTypeList;
	}
	public void setSensorTypeList(List<SensorType> sensorTypeList)
	{
		this.sensorTypeList = sensorTypeList;
	}
	
	public List<String> getSysList()
	{
		return sysList;
	}
	public void setSysList(List<String> sysList)
	{
		this.sysList = sysList;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Concentrator> getService()
	{
		// TODO Auto-generated method stub
		return this.concentService;
	}


}
