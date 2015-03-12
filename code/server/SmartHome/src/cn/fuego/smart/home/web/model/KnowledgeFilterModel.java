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
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.smart.home.constant.KnowledgeKindEnum;
import cn.fuego.smart.home.constant.KnowledgeTypeEnum;

/** 
* @ClassName: KnowledgeFilterModel 
* @Description: TODO
* @author Aether
* @date 2015-3-12 上午10:55:10 
*  
*/ 
public class KnowledgeFilterModel
{

	private String title;  //标题条件
	private String knowledgeType;		//知识类型.0-常识，1-帮助
	private String knowledgeKind;    	//知识种类，0-共用，1-家庭终端，2-企业终端，拓展预留

	private KnowledgeTypeEnum[] typeList = KnowledgeTypeEnum.values();
	private KnowledgeKindEnum[] kindList = KnowledgeKindEnum.values();
	
	public List<QueryCondition> getConidtionList()
    {
    	List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
    	
    	if(!ValidatorUtil.isEmpty(this.getTitle()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.INCLUDLE,"title",this.getTitle()));
    	}
    	if(!ValidatorUtil.isEmpty(this.getKnowledgeType()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"knowledgeType",KnowledgeTypeEnum.getEnumByStr(this.getKnowledgeType()).getIntValue()));
    	}
    	if(!ValidatorUtil.isEmpty(this.getKnowledgeKind()))
    	{
    		conditionList.add(new QueryCondition(ConditionTypeEnum.EQUAL,"knowledgeKind",KnowledgeKindEnum.getEnumByStr(this.getKnowledgeKind()).getIntValue()));
    	}

		return conditionList;
    }

	public KnowledgeTypeEnum[] getTypeList()
	{
		return typeList;
	}

	public void setTypeList(KnowledgeTypeEnum[] typeList)
	{
		this.typeList = typeList;
	}

	public KnowledgeKindEnum[] getKindList()
	{
		return kindList;
	}

	public void setKindList(KnowledgeKindEnum[] kindList)
	{
		this.kindList = kindList;
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getKnowledgeType()
	{
		return knowledgeType;
	}
	public void setKnowledgeType(String knowledgeType)
	{
		this.knowledgeType = knowledgeType;
	}
	public String getKnowledgeKind()
	{
		return knowledgeKind;
	}
	public void setKnowledgeKind(String knowledgeKind)
	{
		this.knowledgeKind = knowledgeKind;
	}

	
	
}
