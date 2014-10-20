/**   
* @Title: MemoryDataSourceImpl.java 
* @Package cn.fuego.misp.service.datasource 
* @Description: TODO
* @author Tang Jun   
* @date 2014-3-26 上午12:22:03 
* @version V1.0   
*/ 
package cn.fuego.common.dao.datasource;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: MemoryDataSourceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-3-26 上午12:22:03 
 *  
 */

public class MemoryDataSourceImpl<E> implements AbstractDataSource<E>
{

	private List<E> allPageData = new  ArrayList<E>();

	
	public MemoryDataSourceImpl(List<E> allPageData)
	{
		this.allPageData = allPageData;
	}
	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.datasource.AbstractDataSource#getCurrentPageData()
	 */
	@Override
	public List<E> getCurrentPageData(int startNum,int pageSize)
	{
		int endNum = startNum + pageSize;
		if(endNum > allPageData.size())
		{
			return allPageData.subList(startNum, allPageData.size());
		}
		else
		{
			return allPageData.subList(startNum, endNum);

		}
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.datasource.AbstractDataSource#getAllPageData()
	 */
	@Override
	public List<E> getAllPageData()
	{
		// TODO Auto-generated method stub
		return allPageData;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.misp.service.datasource.AbstractDataSource#getDataCount()
	 */
	@Override
	public long getDataCount()
	{
		// TODO Auto-generated method stub
		return allPageData.size();
	}

}
