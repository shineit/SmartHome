/**   
* @Title: Privilege.java 
* @Package cn.fuego.misp.domain 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-21 上午9:17:09 
* @version V1.0   
*/ 
package cn.fuego.misp.domain;

import cn.fuego.common.domain.PersistenceObject;

 /** 
 * @ClassName: Privilege 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-21 上午9:17:09 
 *  
 */
public class Privilege implements PersistenceObject
{
	
	
	
	private int id;
	private String masterType;
	private String masterValue;
	private String accessObjType;
	private String accessObjValue;
	private String operation;
	
	public static String getMasterTypeAttr()
	{
		return "masterType";
	}
	
	public static String getMasterValueAttr()
	{
		return "masterValue";
	}
	
	
	public static String getAccessObjTypeAttr()
	{
		return "accessObjType";
	}
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getMasterType()
	{
		return masterType;
	}
	public void setMasterType(String masterType)
	{
		this.masterType = masterType;
	}
	public String getMasterValue()
	{
		return masterValue;
	}
	public void setMasterValue(String masterValue)
	{
		this.masterValue = masterValue;
	}
	public String getAccessObjType()
	{
		return accessObjType;
	}
	public void setAccessObjType(String accessObjType)
	{
		this.accessObjType = accessObjType;
	}
	public String getAccessObjValue()
	{
		return accessObjValue;
	}
	public void setAccessObjValue(String accessObjValue)
	{
		this.accessObjValue = accessObjValue;
	}
	public String getOperation()
	{
		return operation;
	}
	public void setOperation(String operation)
	{
		this.operation = operation;
	}

	@Override
	public String toString()
	{
		return "Privilege [id=" + id + ", masterType=" + masterType
				+ ", masterValue=" + masterValue + ", accessObjType="
				+ accessObjType + ", accessObjValue=" + accessObjValue
				+ ", operation=" + operation + "]";
	}
 
	
	
	

}
