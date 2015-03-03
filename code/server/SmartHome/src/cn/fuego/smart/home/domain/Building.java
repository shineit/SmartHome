/**   
* @Title: SensorPlan.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-1-29 下午3:57:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: SensorPlan 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-29 下午3:57:58 
 *  
 */
public class Building implements PersistenceObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PRI_KEY = "buildingID";
	
	
	private int buildingID; //自增长，主键
	private int companyID; //
	private String name;
	private String desp;
	private String addr;
	private String picPath; //文件路径
	public int getBuildingID()
	{
		return buildingID;
	}
	public void setBuildingID(int buildingID)
	{
		this.buildingID = buildingID;
	}
	public int getCompanyID()
	{
		return companyID;
	}
	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDesp()
	{
		return desp;
	}
	public void setDesp(String desp)
	{
		this.desp = desp;
	}
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getPicPath()
	{
		return picPath;
	}
	public void setPicPath(String picPath)
	{
		this.picPath = picPath;
	}



	
	
	
}
