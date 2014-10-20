/**   
* @Title: DWZTableAction.java 
* @Package cn.fuego.misp.web.action.basic 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-9 上午11:30:46 
* @version V1.0   
*/ 
package cn.fuego.misp.web.action.basic;

import cn.fuego.misp.web.model.page.PageModel;

/** 
 * @ClassName: DWZTableAction 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-9 上午11:30:46 
 *  
 */

public abstract class DWZTableAction extends TableAction
{

	private int numPerPage = 20;
	private int pageNum = 1;
	
	
	public PageModel getPage()
	{
		PageModel page = new PageModel();
		page.setPageSize(numPerPage);
		page.setCurrentPage(pageNum);
		return page;
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
