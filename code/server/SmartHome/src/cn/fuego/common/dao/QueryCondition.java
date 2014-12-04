/**   
* @Title: QueryCondition.java 
* @Package cn.fuego.misp.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 下午04:31:29 
* @version V1.0   
*/ 
package cn.fuego.common.dao;

import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;

/** 
 * @ClassName: QueryCondition 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 下午04:31:29 
 *  
 */

public class QueryCondition
{
	private ConditionTypeEnum conditionType;
	private String attrName;
	private Object firstValue;
	private String secondValue;
	private List<String> listValue;
 
	public QueryCondition(ConditionTypeEnum conditionType, String attrName)
	{
		super();
		this.conditionType = conditionType;
		this.attrName = attrName;
 	}
	
	public QueryCondition(ConditionTypeEnum conditionType, String attrName, Object firstValue)
	{
		super();
		this.conditionType = conditionType;
		this.attrName = attrName;
		this.firstValue = firstValue;
	}
	public QueryCondition(ConditionTypeEnum conditionType, String attrName, List<String> listValue)
	{
		super();
		this.conditionType = conditionType;
		this.attrName = attrName;
		this.listValue = listValue;
	}
	/**
	 * @param conditionType
	 * @param attrName
	 * @param firstValue
	 * @param secondValue
	 */
	public QueryCondition(ConditionTypeEnum conditionType, String attrName, String firstValue, String secondValue)
	{
		super();
		this.conditionType = conditionType;
		this.attrName = attrName;
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}
	public String getAttrName()
	{
		return attrName;
	}
	public void setAttrName(String attrName)
	{
		this.attrName = attrName;
	}
	public ConditionTypeEnum getOperate()
	{
		return conditionType;
	}
	public void setOperate(ConditionTypeEnum operate)
	{
		this.conditionType = operate;
	}

 
	public Object getFirstValue()
	{
		return firstValue;
	}
	public void setFirstValue(Object firstValue)
	{
		this.firstValue = firstValue;
	}
	public String getSecondValue()
	{
		return secondValue;
	}
	public void setSecondValue(String secondValue)
	{
		this.secondValue = secondValue;
	}

	public List<String> getListValue()
	{
		return listValue;
	}

	public void setListValue(List<String> listValue)
	{
		this.listValue = listValue;
	}

	@Override
	public String toString()
	{
		return "QueryCondition [conditionType=" + conditionType + ", attrName="
				+ attrName + ", firstValue=" + firstValue + ", secondValue="
				+ secondValue + ", listValue=" + listValue + "]";
	}
	

}
