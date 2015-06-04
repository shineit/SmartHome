/**   
* @Title: Company.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-3-3 上午11:20:32 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: Company 
 * @Description: 公司单位信息
 * @author Aether
 * @date 2015-3-3 上午11:20:32 
 *  
 */
public class Company implements PersistenceObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String PRI_KEY = "companyID";
	private long concentratorID=0;     //集中器编号
	private String password;
	private int companyID;					//单位编号，自增长，主键
	private String companyName; 			//单位名称，可变需控制长度
	private String applyName;				//使用名称，可变需控制长度
	private String companyAddr;				//单位地址
	private String companyType;				//单位类型
	private String companyPhone;			//单位电话
	private float buildingArea=(float)0;	//建筑面积
	
	private String legalOfficer;			//法人名字
	private String officerPhone;			//法人电话
	private String fireManager;				//管理人名字
	private String managerPhone; 			//管理人电话
	private String fireDuty;				//责任人名字
	private String dutyPhone; 				//责任人电话
	private String extendInfo;				//扩展字段，其他信息补充
	
	private String fireRisk;                //火灾危险性
	
	private String org_id;
	
	
	public long getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(long concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public int getCompanyID()
	{
		return companyID;
	}
	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}
	public String getCompanyName()
	{
		return companyName;
	}
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	public String getApplyName()
	{
		return applyName;
	}
	public void setApplyName(String applyName)
	{
		this.applyName = applyName;
	}
	public String getCompanyAddr()
	{
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr)
	{
		this.companyAddr = companyAddr;
	}
	public String getCompanyType()
	{
		return companyType;
	}
	public void setCompanyType(String companyType)
	{
		this.companyType = companyType;
	}
	public String getCompanyPhone()
	{
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone)
	{
		this.companyPhone = companyPhone;
	}
	public float getBuildingArea()
	{
		return buildingArea;
	}
	public void setBuildingArea(float buildingArea)
	{
		this.buildingArea = buildingArea;
	}
	public String getLegalOfficer()
	{
		return legalOfficer;
	}
	public void setLegalOfficer(String legalOfficer)
	{
		this.legalOfficer = legalOfficer;
	}
	public String getOfficerPhone()
	{
		return officerPhone;
	}
	public void setOfficerPhone(String officerPhone)
	{
		this.officerPhone = officerPhone;
	}
	public String getFireManager()
	{
		return fireManager;
	}
	public void setFireManager(String fireManager)
	{
		this.fireManager = fireManager;
	}
	public String getManagerPhone()
	{
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone)
	{
		this.managerPhone = managerPhone;
	}
	public String getFireDuty()
	{
		return fireDuty;
	}
	public void setFireDuty(String fireDuty)
	{
		this.fireDuty = fireDuty;
	}
	public String getDutyPhone()
	{
		return dutyPhone;
	}
	public void setDutyPhone(String dutyPhone)
	{
		this.dutyPhone = dutyPhone;
	}
	public String getExtendInfo()
	{
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo)
	{
		this.extendInfo = extendInfo;
	}
	public String getFireRisk()
	{
		return fireRisk;
	}
	public void setFireRisk(String fireRisk)
	{
		this.fireRisk = fireRisk;
	}
	public String getOrg_id()
	{
		return org_id;
	}
	public void setOrg_id(String org_id)
	{
		this.org_id = org_id;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	
}
