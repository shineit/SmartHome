/**   
* @Title: DWZTableAction.java 
* @Package cn.fuego.misp.web.action.basic 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-9 上午11:30:46 
* @version V1.0   
*/ 
package cn.fuego.misp.web.action.basic;

import java.util.Arrays;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.web.model.message.MispMessageModel;
import cn.fuego.misp.web.model.page.PageModel;

/** 
 * @ClassName: DWZTableAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-9 上午11:30:46 
 *  
 */

public abstract class DWZTableAction<E> extends TableAction<E>
{

	private FuegoLog log = FuegoLog.getLog(getClass());
	private int numPerPage = 20;
	private int pageNum = 1;
	
	
	public PageModel getPage()
	{
		PageModel page = new PageModel();
		page.setPageSize(numPerPage);
		page.setCurrentPage(pageNum);
		return page;
	}
	
	public String execute()
	{
		table.setPage(this.getPage());
		table.setDataSource(this.getService().getDataSource(this.getFilterCondition()));
		return SUCCESS;
	}
	
	public String show()
	{
		 this.setObj(getService().get(this.getSelectedID()));
		
		return this.getNextPage();
	}
	public String create()
	{
		try
		{
			this.getService().create(this.getObj());
			this.getOperateMessage().setCallbackType(MispMessageModel.CLOSE_CURENT_PAGE);
			
		} 
		catch(MISPException e)
		{
			log.error("create user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch (Exception e)
		{
			log.error("create user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return MISP_DONE_PAGE;
	}
	public String delete()
	{
		try
        {
			this.getService().delete(this.getSelectedID());
        }
		catch(MISPException e)
		{
			log.error("delete user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch (Exception e)
		{
			log.error("delete user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return MISP_DONE_PAGE;
	}
	public String deleteList()
	{
		try
        {
			this.getService().delete(Arrays.asList(this.getSelectedIDList()));
        }
		catch(MISPException e)
		{
			log.error("delete user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch (Exception e)
		{
			log.error("delete user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return MISP_DONE_PAGE;
	}
	
	public String modify()
	{
		try
        {
			this.getService().modify(this.getObj());
        }
		catch(MISPException e)
		{
			log.error("modify user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(e.getErrorCode());
		}
		catch (Exception e)
		{
			log.error("modify user failed",e);
			this.getOperateMessage().setStatusCode(MispMessageModel.FAILURE_CODE);
			this.getOperateMessage().setErrorCode(MISPErrorMessageConst.OPERATE_FAILED);
		}
		return MISP_DONE_PAGE;
	}
	public int getNumPerPage()
	{
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage)
	{
		this.numPerPage = numPerPage;
	}
	public int getPageNum()
	{
		return pageNum;
	}
	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}
	
}
