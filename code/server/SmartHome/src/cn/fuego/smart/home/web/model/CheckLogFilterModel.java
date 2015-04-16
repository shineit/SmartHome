/**   
* @Title: AlarmFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2014-11-7 上午10:27:43 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.CheckResultEnum;


/** 
* @ClassName: CheckLogFilterModel 
* @Description: TODO
* @author Aether
* @date 2015-3-12 下午3:02:43 
*  
*/ 
public class CheckLogFilterModel
{

	private String checkItem; 		//项目名称；
	private String checkResult;		//巡检结果，0-未设置，1-正常，2-异常
    private String companyName;
    
	private String startDate;
	private String endDate;
	
	private CheckResultEnum[] resultList = CheckResultEnum.values();
	
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
    	
    	if(!ValidatorUtil.isEmpty(this.getCheckItem()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"checkItem",this.getCheckItem()));
    	}
    	if(!ValidatorUtil.isEmpty(this.getCompanyName()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"companyName",this.getCompanyName()));
    	}
    	if(!ValidatorUtil.isEmpty(this.getCheckResult()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"checkResult",CheckResultEnum.getEnumByStr(this.getCheckResult()).getIntValue()));
    	}
		if(!ValidatorUtil.isEmpty(this.getStartDate()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"checkTime",this.getStartDate()));
		}
		if(!ValidatorUtil.isEmpty(this.getEndDate()))
		{
			Date endDate = DateUtil.stringToDate(this.getEndDate());
			conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER,"checkTime",DateUtil.DateToString(DateUtil.dayCalculate(endDate, 1))));
		}
		//时间降序排列
		conditionList.add(new QueryCondition(ConditionTypeEnum.DESC_ORDER,"checkTime"));
		return conditionList;
    }

	public String getCheckItem()
	{
		return checkItem;
	}

	public void setCheckItem(String checkItem)
	{
		this.checkItem = checkItem;
	}


	public String getCheckResult()
	{
		return checkResult;
	}

	public void setCheckResult(String checkResult)
	{
		this.checkResult = checkResult;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public CheckResultEnum[] getResultList()
	{
		return resultList;
	}

	public void setResultList(CheckResultEnum[] resultList)
	{
		this.resultList = resultList;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	
}
