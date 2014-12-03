package cn.fuego.smart.home.web.action.device;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.domain.Concentrator;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.ConcentratorManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.ConcentFilterModel;

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
