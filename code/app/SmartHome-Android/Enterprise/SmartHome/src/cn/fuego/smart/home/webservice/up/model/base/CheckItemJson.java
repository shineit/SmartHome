package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;


/** 
* @ClassName: CheckItemJson 
* @Description: TODO
* @author Aether
* @date 2015-3-16 下午5:10:12 
*  
*/ 
public class CheckItemJson implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int itemID;			//项目编号，自增长；
	private String itemName;	//项目名称
	private String itemSys;		//项目所属系统

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
	@Override
	public String toString()
	{
		return "CheckItemJson [itemID=" + itemID + ", itemName=" + itemName
				+ ", itemSys=" + itemSys + "]";
	}

 
}
