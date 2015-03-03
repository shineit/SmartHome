/**   
* @Title: Knowledge.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-3-3 下午2:11:46 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: Knowledge 
 * @Description: TODO
 * @author Aether
 * @date 2015-3-3 下午2:11:46 
 *  
 */
public class Knowledge implements PersistenceObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PRI_KEY = "knowledgeID";
	
	private int knowledgeID;		//知识ID，自增长，主键
	private String title;			//知识标题
	private String content;			//知识内容，数据库对应text类型
	private int knowledgeType;		//知识类型.0-常识，1-帮助
	private int knowledgeKind=0;    	//知识种类，0-共用，1-家庭终端，2-企业终端，拓展预留
	
	public int getKnowledgeID()
	{
		return knowledgeID;
	}
	public void setKnowledgeID(int knowledgeID)
	{
		this.knowledgeID = knowledgeID;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getKnowledgeType()
	{
		return knowledgeType;
	}
	public void setKnowledgeType(int knowledgeType)
	{
		this.knowledgeType = knowledgeType;
	}
	public int getKnowledgeKind()
	{
		return knowledgeKind;
	}
	public void setKnowledgeKind(int knowledgeKind)
	{
		this.knowledgeKind = knowledgeKind;
	}


	
}
