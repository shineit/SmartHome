/**   
* @Title: SensorTypeFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2015-1-21 上午10:32:20 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.util.validate.ValidatorUtil;


/** 
 * @ClassName: SensorTypeFilterModel 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-21 上午10:32:20 
 *  
 */
public class SensorTypeFilterModel
{
	private String typeName;
	private String typeCode;
	private String typeSys;
	private List<String> sysList = new ArrayList<String>();
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		 
		if (!ValidatorUtil.isEmpty(this.getTypeName()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"typeName", this.getTypeName()));
		}
		if (!ValidatorUtil.isEmpty(this.getTypeSys()))
		{
			conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"typeSys", this.getTypeSys()));
		}	
		 
		return conditionList;
    }

	public String getTypeName()
	{
		return typeName;
	}
	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}
	public String getTypeCode()
	{
		return typeCode;
	}
	public void setTypeCode(String typeCode)
	{
		this.typeCode = typeCode;
	}
	public String getTypeSys()
	{
		return typeSys;
	}
	public void setTypeSys(String typeSys)
	{
		this.typeSys = typeSys;
	}

	public List<String> getSysList()
	{
		return sysList;
	}

	public void setSysList(List<String> sysList)
	{
		this.sysList = sysList;
	}

	
}
