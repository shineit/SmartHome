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
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
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

	private AlarmTypeEnum[] alarmTypeList = AlarmTypeEnum.values();
	private AlarmClearEnum[] alarmClearList = AlarmClearEnum.values();
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 
    	
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
	
	
}
