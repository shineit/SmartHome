/**   
* @Title: SensorPlan.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-1-29 下午3:57:58 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

import cn.fuego.common.domain.PersistenceObject;

/** 
 * @ClassName: SensorPlan 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-29 下午3:57:58 
 *  
 */
public class SensorPlan implements PersistenceObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userID; //复合主键1
	private int nodeID; //复合主键2
	private String nodeName;
	private int nodeType;  //0-文件夹类型，1-文件类型
	private int parentID;
	private String filePath; //文件路径

	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public int getNodeID()
	{
		return nodeID;
	}
	public void setNodeID(int nodeID)
	{
		this.nodeID = nodeID;
	}
	public String getNodeName()
	{
		return nodeName;
	}
	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}
	public int getNodeType()
	{
		return nodeType;
	}
	public void setNodeType(int nodeType)
	{
		this.nodeType = nodeType;
	}
	public int getParentID()
	{
		return parentID;
	}
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}
	public String getFilePath()
	{
		return filePath;
	}
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	
	
	
}
