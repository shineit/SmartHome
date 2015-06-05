package cn.fuego.smart.home.webservice.up.model.base;

import java.io.Serializable;


/** 
* @ClassName: CompanyJson 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午11:35:24 
*  
*/ 
public class CompanyJson implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int companyID;					//单位编号，自增长，主键
	private String companyName; 			//单位名称，可变需控制长度
	private String applyName;				//使用名称，可变需控制长度
	private String companyAddr;				//单位地址
	private String companyType;				//单位类型
	private String companyPhone;			//单位电话
	private float buildingArea;				//建筑面积
	
	private String legalOfficer;			//法人名字
	private String officerPhone;			//法人电话
	private String fireManager;				//管理人名字
	private String managerPhone; 			//管理人电话
	private String fireDuty;				//责任人名字
	private String dutyPhone; 				//责任人电话
	private String extendInfo;				//扩展字段，其他信息补充
	
	private String fireRisk;                //火灾危险性
	
	private String maintainerUnit;         //消防维保单位
	private String maintainerName; 		//消防维保人员
	private String fireCert; 		//消防维保证书图片
	
	private String org_id;

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

	public String getMaintainerUnit()
	{
		return maintainerUnit;
	}

	public void setMaintainerUnit(String maintainerUnit)
	{
		this.maintainerUnit = maintainerUnit;
	}

	public String getMaintainerName()
	{
		return maintainerName;
	}

	public void setMaintainerName(String maintainerName)
	{
		this.maintainerName = maintainerName;
	}

	public String getFireCert()
	{
		return fireCert;
	}

	public void setFireCert(String fireCert)
	{
		this.fireCert = fireCert;
	}

	public String getOrg_id()
	{
		return org_id;
	}

	public void setOrg_id(String org_id)
	{
		this.org_id = org_id;
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

	@Override
	public String toString()
	{
		return "CompanyJson [companyID=" + companyID + ", companyName="
				+ companyName + ", applyName=" + applyName + ", companyAddr="
				+ companyAddr + ", companyType=" + companyType
				+ ", companyPhone=" + companyPhone + ", buildingArea="
				+ buildingArea + ", legalOfficer=" + legalOfficer
				+ ", officerPhone=" + officerPhone + ", fireManager="
				+ fireManager + ", managerPhone=" + managerPhone
				+ ", fireDuty=" + fireDuty + ", dutyPhone=" + dutyPhone
				+ ", extendInfo=" + extendInfo + ", fireRisk=" + fireRisk
				+ ", maintainerUnit=" + maintainerUnit + ", maintainerName="
				+ maintainerName + ", fireCert=" + fireCert + ", org_id="
				+ org_id + "]";
	}


	
	
 
}
