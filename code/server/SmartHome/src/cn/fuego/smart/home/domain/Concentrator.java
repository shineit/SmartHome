package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;
import cn.fuego.smart.home.constant.DeviceKindEunm;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long concentratorID;     //集中器编号
	private String ipAddr;			//集中器IP地址
	private int port;
	private Integer status=0;			//集中器状态，0 离线 1在线
	private Float locationNS;		//集中器纬度
	private Float locationWE;		//集中器经度
	private String name;			//集中器名称(预留字段)
	private String description;		//集中器描述(预留字段)
	private String addr;			//集中器地址(预留字段)
	private String mark;			//自定义标签(预留字段)
	
	private int concentratorKind = DeviceKindEunm.HOME_CONCENTRATOR.getIntValue();
	
	public static final String PRI_KEY = "concentratorID";

	
	public int getConcentratorKind()
	{
		return concentratorKind;
	}
	public void setConcentratorKind(int concentratorKind)
	{
		this.concentratorKind = concentratorKind;
	}
	public int getPort()
	{
		return port;
	}
	public void setPort(int port)
	{
		this.port = port;
	}
 
	public long getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(long concentratorID)
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
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	public Float getLocationNS()
	{
		return locationNS;
	}
	public void setLocationNS(Float locationNS)
	{
		this.locationNS = locationNS;
	}
	public Float getLocationWE()
	{
		return locationWE;
	}
	public void setLocationWE(Float locationWE)
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
 
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (concentratorID ^ (concentratorID >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Concentrator other = (Concentrator) obj;
		if (concentratorID != other.concentratorID)
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "Concentrator [concentratorID=" + concentratorID + ", ipAddr="
				+ ipAddr + ", port=" + port + ", status=" + status
				+ ", locationNS=" + locationNS + ", locationWE=" + locationWE
				+ ", name=" + name + ", description=" + description + ", addr="
				+ addr + ", mark=" + mark + "]";
	}
	
	
}
