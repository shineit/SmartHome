/**   
* @Title: PageModel.java 
* @Package cn.tinder.fuego.dao.util 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-20 下午10:29:28 
* @version V1.0   
*/ 
package cn.fuego.misp.web.model.page;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.dao.datasource.AbstractDataSource;

/** 
 * @ClassName: PageModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-20 下午10:29:28 
 *  
 */

public class PageModel
{
 
	private List<Integer> pages =new ArrayList<Integer>();
	private int[] pageSizeList ={20,50,100,200}; 
	private int pageSize =20;  //defualt page size
	private int currentPage = 1;
	private long count = 0;
	
 
	
	public int getStartNum()
	{
		return (currentPage-1)*pageSize;  
	}
	
	public int getEndNum()
	{
		return  getStartNum()+pageSize;
	}
	public List<Integer> getPages()
	{
		pages.clear();
		int i = 1;
		for(;i<=count/pageSize;i++)
		{
			pages.add(i);
		}
		if(count%pageSize!=0)  
		{
			pages.add(i);
		}
		return pages;
	}
	public void setPages(List<Integer> pages)
	{
		this.pages = pages;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	public int getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	public long getCount()
	{
		return count;
	}
	public void setCount(long count)
	{
		this.count = count;
	}

	public int[] getPageSizeList()
	{
		return pageSizeList;
	}

	public void setPageSizeList(int[] pageSizeList)
	{
		this.pageSizeList = pageSizeList;
	}

 

}
