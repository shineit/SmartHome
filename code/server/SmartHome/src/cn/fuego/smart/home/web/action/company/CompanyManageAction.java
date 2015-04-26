/**   
* @Title: CompanyManageAction.java 
* @Package cn.fuego.smart.home.web.action.company 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-3 下午6:39:35 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.action.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.domain.SystemUser;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MISPServiceContext;
import cn.fuego.misp.service.MispCommonService;
import cn.fuego.misp.web.action.basic.DWZTableAction;
import cn.fuego.misp.web.constant.TableOperateTypeEnum;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.page.TableDataModel;
import cn.fuego.smart.home.constant.ErrorMessageConst;
import cn.fuego.smart.home.dao.DaoContext;
import cn.fuego.smart.home.domain.Building;
import cn.fuego.smart.home.domain.Company;
import cn.fuego.smart.home.domain.FireSensor;
import cn.fuego.smart.home.domain.UserConcentrator;
import cn.fuego.smart.home.service.CompanyManageService;
import cn.fuego.smart.home.service.ServiceContext;
import cn.fuego.smart.home.web.model.CompanyFilterModel;
import cn.fuego.smart.home.web.model.UserCompanyModel;


 /** 
 * @ClassName: CompanyManageAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-3 下午6:39:35 
 *  
 */
public class CompanyManageAction extends DWZTableAction<Company>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(this.getClass());
	
	private CompanyManageService companyService = ServiceContext.getInstance().getCompanyManageService();

	private CompanyFilterModel filter = new  CompanyFilterModel();
	
	private TableDataModel<SystemUser> permissionTable = new TableDataModel<SystemUser>();
	
	private UserCompanyModel userPermission= new UserCompanyModel();
	//修改集中器会产生一系列影响
	private String oldConcentID;
	
	
	/* (non-Javadoc)
	 * @see cn.fuego.misp.web.action.basic.TableAction#getService()
	 */
	@Override
	public MispCommonService<Company> getService()
	{
		// TODO Auto-generated method stub
		return companyService;
	}
	@Override
	public List<QueryCondition> getFilterCondition()
	{
		// TODO Auto-generated method stub
		return this.filter.getConidtionList();
	}
	
	@Override
	public String modify()
	{
		
		if(!ValidatorUtil.isEmpty(this.getOldConcentID())&&!this.getOldConcentID().equals("0"))
		{
			if(!String.valueOf(this.obj.getConcentratorID()).equals(this.getOldConcentID()))
			{
				//先判断现有公司中是否还存在该集中器
				Company c= ServiceContext.getInstance().getCompanyManageService().getCompanyByConcentorID(this.obj.getConcentratorID());
				
				if(null!=c)
				{
					log.warn("modify company on concentratorID failed, the ID is:"+this.obj.getConcentratorID());
					this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
					this.getOperateMessage().setMessage("该集中器已经被公司:"+c.getCompanyName()+"关联!");
					return MISP_DONE_PAGE;
				}
				else
				{
					try
					{
						//删除若存在的用户-集中器-公司关联关系		
						companyService.deletePermissionByCompanyID(String.valueOf(this.obj.getCompanyID()));
						//修改关联的传感器信息
						ServiceContext.getInstance().getFireSensorManageService().modifyOnConcentID(this.getOldConcentID(),this.obj.getConcentratorID());
					}
					catch (Exception e)
					{
						log.error("modify company on concentratorID failed",e);
						this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
						this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
						return MISP_DONE_PAGE;
					}
				}
			}
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

	public String showPermission()
	{
		List<String> userIDList= new ArrayList<String>();
		try
		{
			Set<String> idList=MISPServiceContext.getInstance().getMISPPrivilegeManage().getUserIDListByCommpany(this.getSelectedID());
			
			if(!ValidatorUtil.isEmpty(idList))
			{
				userIDList.addAll(idList);

			}
			permissionTable.setPage(this.getPage());
			permissionTable.setDataSource(companyService.getPermissionDataSource(userIDList));

		}
		catch (MISPException e)
		{
			
			log.error("show user-company permission page failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			return MISP_DONE_PAGE;
		} 		
		catch (Exception e)
		{
			log.error("show user-company permission page failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
			return MISP_DONE_PAGE;
		}
		return "showPermission";
		
	}
	/**
	 * 添加权限
	 * @return
	 */
	public String addPermission()
	{
		
		try
		{
			companyService.addPermission(userPermission);
			this.getOperateMessage().setCallbackType(MispMessageModel.FORWARD);
			this.getOperateMessage().setNavTabId("peDialog");
		
		}
		catch (MISPException e)
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
	/**
	 * 删除权限
	 * @return
	 */
	public String deletePermission()
	{
		try
		{
			companyService.deletePermissionByID(userPermission);
			this.getOperateMessage().setNavTabId("peDialog");
		} 
		catch (MISPException e)
		{
			
			log.error("delete user-concentrator permission failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			
			log.error("delete user-concentrator permission failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return MISP_DONE_PAGE;
	}
	
	
	@Override
	public String deleteList()
	{
		String[] idList = this.getSelectedIDList();
		try
		{
			for(int i=0;i<idList.length;i++)
			{
				String companyID=idList[i];
				//Company company = ServiceContext.getInstance().getCompanyManageService().get(companyID);
				if(buildingIsDeleted(companyID))
				{
					//删除用户公司关联权限
					//删除用户集中器关联权限
					companyService.deletePermissionByCompanyID(companyID);
					super.deleteList();
					
				}
				else
				{
					log.error("delete company  failed, the companyID is"+companyID);
					this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
					//this.getOperateMessage().setMessage("该公司还存在未删除楼层信息！");
					this.getOperateMessage().setErrorCode(ErrorMessageConst.BUILDING_NOT_DELETED);
					return MISP_DONE_PAGE;
				}
			}
		} 		
		catch (MISPException e)
		{
			
			log.error("delete company  failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch(Exception e)
		{
			
			log.error("delete company  failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return MISP_DONE_PAGE;
	}

	private boolean buildingIsDeleted(String companyID)
	{
		List<QueryCondition> conditionList= new ArrayList<QueryCondition>();
		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL, "companyID", companyID));
		List<Building> target = ServiceContext.getInstance().getBuildingManageService().getDataSource(conditionList).getAllPageData();
		if(!ValidatorUtil.isEmpty(target))
		{
			return false;
		}
		return true;
	}

	public TableDataModel<SystemUser> getPermissionTable()
	{
		return permissionTable;
	}
	public void setPermissionTable(TableDataModel<SystemUser> permissionTable)
	{
		this.permissionTable = permissionTable;
	}
	public UserCompanyModel getUserPermission()
	{
		return userPermission;
	}
	public void setUserPermission(UserCompanyModel userPermission)
	{
		this.userPermission = userPermission;
	}
	public CompanyFilterModel getFilter()
	{
		return filter;
	}
	public void setFilter(CompanyFilterModel filter)
	{
		this.filter = filter;
	}
	public String getOldConcentID()
	{
		return oldConcentID;
	}
	public void setOldConcentID(String oldConcentID)
	{
		this.oldConcentID = oldConcentID;
	}

	
}
