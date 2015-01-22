/**   
* @Title: SensorTypeFilterModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Aether
* @date 2015-1-21 上午10:32:20 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;


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

	
}
