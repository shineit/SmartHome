/**   
* @Title: LogFilterModel.java 
* @Package cn.fuego.misp.web.model.log 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-2 下午04:31:03 
* @version V1.0   
*/ 
package cn.fuego.misp.web.model.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.ConcentratorStatusEnum;

/** 
 * @ClassName: LogFilterModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-2 下午04:31:03 
 *  
 */

public class LogFilterModel
{
	private String id;
	private String user;
	private String name;
	private String object;
	private String result;
	private String startTime;
	private String endTime;
	
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 
		if(!ValidatorUtil.isEmpty(this.getId()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"id",this.getId()));
		}
			

		if(!ValidatorUtil.isEmpty(this.getUser()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"user",this.getUser()));
		}
		if(!ValidatorUtil.isEmpty(this.getName()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"name",this.getName()));
		}
		if(!ValidatorUtil.isEmpty(this.getStartTime()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"operTime",this.getStartTime()));
		}
		if(!ValidatorUtil.isEmpty(this.getEndTime()))
		{
			Date endDate = DateUtil.stringToDate(this.getEndTime());
			conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER,"operTime",DateUtil.DateToString(DateUtil.dayCalculate(endDate, 1))));
		}			
		 
		return conditionList;
    }

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getObject()
	{
		return object;
	}
	public void setObject(String object)
	{
		this.object = object;
	}
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	public String getStartTime()
	{
		return startTime;
	}
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	public String getEndTime()
	{
		return endTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	
	
}
