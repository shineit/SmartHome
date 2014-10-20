/**   
* @Title: CommonDao.java 
* @Package cn.fuego.common.dao 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-29 下午11:10:02 
* @version V1.0   
*/ 
package cn.fuego.common.dao.impl;

/** 
 * @ClassName: CommonDao 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-29 下午11:10:02 
 *  
 */

public class CommonDaoImpl extends AbstractDao
{
	private Class clazz;

	public CommonDaoImpl(Class clazz)
	{
		this.clazz = clazz;
	}
	
	/* (non-Javadoc)
	 * @see cn.fuego.common.dao.AbstractViewDao#getFeaturedClass()
	 */
	@Override
	public Class getFeaturedClass()
	{
		// TODO Auto-generated method stub
		return clazz;
	}

}
