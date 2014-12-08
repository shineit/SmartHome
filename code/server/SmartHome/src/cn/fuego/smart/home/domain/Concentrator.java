package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

/**
 * 
* @ClassName: Concentrator 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午11:01:04 
*
 */
public class Concentrator implements PersistenceObject
{
	private int concentratorID;     //集中器编号
	private String ipAddr;			//集中器IP地址
	private Integer status;				//集中器状态，0 离线 1在线
	private Float locationNS;		//集中器纬度
	private Float locationWE;		//集中器经度
	private String name;			//集中器名称(预留字段)
	private String description;		//集中器描述(预留字段)
	private String addr;			//集中器地址(预留字段)
	private String mark;			//自定义标签(预留字段)
	
	public static final String PRI_KEY = "concentratorID";

	
	public int getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(int concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public String getIpAddr()
	{
		return ipAddr;
	}
	public void setIpAddr(String ipAddr)
	{
		this.ipAddr = ipAddr;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public float getLocationNS()
	{
		return locationNS;
	}
	public void setLocationNS(float locationNS)
	{
		this.locationNS = locationNS;
	}
	public float getLocationWE()
	{
		return locationWE;
	}
	public void setLocationWE(float locationWE)
	{
		this.locationWE = locationWE;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
