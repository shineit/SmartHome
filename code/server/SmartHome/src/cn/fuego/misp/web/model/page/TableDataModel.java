/**   
* @Title: TableDataModel.java 
* @Package cn.fuego.misp.web.model.page 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-25 下午11:18:38 
* @version V1.0   
*/ 
package cn.fuego.misp.web.model.page;

import java.util.List;

import cn.fuego.common.dao.datasource.AbstractDataSource;

/** 
 * @ClassName: TableDataModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-25 下午11:18:38 
 *  
 */

public class TableDataModel<E>
{
	private PageModel page = new PageModel();
	
	private AbstractDataSource<E> dataSource;
	public List<E> getCurrentPageData()
	{
		return dataSource.getCurrentPageData(page.getStartNum(), page.getPageSize());
	}
  
	public AbstractDataSource<E> getDataSource()
	{
		return dataSource;
	}

	public void setDataSource(AbstractDataSource<E> dataSource)
	{
		this.dataSource = dataSource;
		page.setCount(this.dataSource.getDataCount());
		this.dataSource = dataSource;
	}



	public PageModel getPage()
	{
		return page;
	}



	public void setPage(PageModel page)
	{
		this.page = page;
	}

}
