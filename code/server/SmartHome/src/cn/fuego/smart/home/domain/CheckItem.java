/**   
* @Title: CheckItem.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-3-3 上午11:47:37 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: CheckItem 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-3 上午11:47:37 
 *  
 */
public class CheckItem implements PersistenceObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PRI_KEY = "itemID";
	
	private int itemID;			//项目编号，自增长；
	private String itemName;	//项目名称
	private String itemSys;		//项目所属系统
	private int companyID; 		//单位编号，用于拓展，单位自定义特殊项目
	public int getItemID()
	{
		return itemID;
	}
	public void setItemID(int itemID)
	{
		this.itemID = itemID;
	}
	public String getItemName()
	{
		return itemName;
	}
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	public String getItemSys()
	{
		return itemSys;
	}
	public void setItemSys(String itemSys)
	{
		this.itemSys = itemSys;
	}
	public int getCompanyID()
	{
		return companyID;
	}
	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}

	
}
