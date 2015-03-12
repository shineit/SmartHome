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
import cn.fuego.smart.home.constant.AlarmClearEnum;
import cn.fuego.smart.home.constant.AlarmTypeEnum;

/** 
 * @ClassName: AlarmFilterModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:27:43 
 *  
 */
public class AlarmFilterModel
{


	private String concentratorID;
	private String alarmType;		//告警事件类型 AlarmTypeEnum
	private String clearStatus;   //0未清除 1 手动清除 2自动清除 AlarmClearEnum
	private String startDate;
	private String endDate;
	
	private AlarmTypeEnum[] alarmTypeList = AlarmTypeEnum.values();
	private AlarmClearEnum[] alarmClearList = AlarmClearEnum.values();
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
    	
    	if(!ValidatorUtil.isEmpty(this.getConcentratorID()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"concentratorID",this.getConcentratorID()));
    	}
    	if(!ValidatorUtil.isEmpty(this.getAlarmType()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"alarmType",this.getAlarmType()));
    	}
    	if(!ValidatorUtil.isEmpty(this.getClearStatus()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"clearStatus",AlarmClearEnum.getEnumByStr(this.getClearStatus()).getIntValue()));
    	}
		if(!ValidatorUtil.isEmpty(this.getStartDate()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.BIGER_EQ,"alarmTime",this.getStartDate()));
		}
		if(!ValidatorUtil.isEmpty(this.getEndDate()))
		{
			Date endDate = DateUtil.stringToDate(this.getEndDate());
			conditionList.add(new QueryCondition(ConditionTypeEnum.LOWER,"alarmTime",DateUtil.DateToString(DateUtil.dayCalculate(endDate, 1))));
		}
		//时间降序排列
		conditionList.add(new QueryCondition(ConditionTypeEnum.DESC_ORDER,"alarmTime"));
		return conditionList;
    }
	public AlarmTypeEnum[] getAlarmTypeList()
	{
		return alarmTypeList;
	}
	public void setAlarmTypeList(AlarmTypeEnum[] alarmTypeList)
	{
		this.alarmTypeList = alarmTypeList;
	}
	public AlarmClearEnum[] getAlarmClearList()
	{
		return alarmClearList;
	}
	public void setAlarmClearList(AlarmClearEnum[] alarmClearList)
	{
		this.alarmClearList = alarmClearList;
	}
	public String getConcentratorID()
	{
		return concentratorID;
	}
	public void setConcentratorID(String concentratorID)
	{
		this.concentratorID = concentratorID;
	}
	public String getAlarmType()
	{
		return alarmType;
	}
	public void setAlarmType(String alarmType)
	{
		this.alarmType = alarmType;
	}
	public String getClearStatus()
	{
		return clearStatus;
	}
	public void setClearStatus(String clearStatus)
	{
		this.clearStatus = clearStatus;
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
	
	
}
