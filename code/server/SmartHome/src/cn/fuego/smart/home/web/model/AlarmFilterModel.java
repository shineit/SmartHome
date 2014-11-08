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

/** 
 * @ClassName: AlarmFilterModel 
 * @Description: TODO
 * @author Aether
 * @date 2014-11-7 上午10:27:43 
 *  
 */
public class AlarmFilterModel
{

	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 
    	
    	conditionList.add(new QueryCondition(ConditionTypeEnum.DESC_ORDER,"alarmTime"));
		 
		return conditionList;
    }
}
