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
import cn.fuego.smart.home.domain.Company;
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

}
