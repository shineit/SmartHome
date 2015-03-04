/**   
* @Title: NewsFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2014-11-3 下午6:12:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;


/** 
* @ClassName: CompanyFilterModel 
* @Description: TODO
* @author Aether
* @date 2015-3-4 下午3:28:23 
*  
*/ 
public class CompanyFilterModel
{
	private String companyName;  	//公司名称
	private String applyName; 		//使用名称
	private String companyType; 	//单位类型
	private String legalOfficer; 		//法人名称


	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 
			if(!ValidatorUtil.isEmpty(this.getCompanyName()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"companyName",this.getCompanyName()));
			}
			if(!ValidatorUtil.isEmpty(this.getApplyName()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"applyName",this.getApplyName()));
			}
			if(!ValidatorUtil.isEmpty(this.getCompanyType()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"companyType",this.getCompanyType()));
			}				
			if(!ValidatorUtil.isEmpty(this.getLegalOfficer()))
			{
				conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"legalOfficer",this.getLegalOfficer()));
			}		 
		return conditionList;
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

	public String getCompanyType()
	{
		return companyType;
	}

	public void setCompanyType(String companyType)
	{
		this.companyType = companyType;
	}

	public String getLegalOfficer()
	{
		return legalOfficer;
	}

	public void setLegalOfficer(String legalOfficer)
	{
		this.legalOfficer = legalOfficer;
	}

	
	
}
