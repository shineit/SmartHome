/**   
* @Title: SensorType.java 
* @Package cn.fuego.smart.home.domain 
* @Description: TODO
* @author Aether
* @date 2015-1-18 下午7:58:38 
* @version V1.0   
*/ 
package cn.fuego.smart.home.domain;

/** 
 * @ClassName: SensorType 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-18 下午7:58:38 
 *  
 */
public class SensorType
{
	private int typeID;  			//传感器类型ID，自增长
	private String typeName;    //类型名称
	private Float defWarnValue;    //默认预警值
	private Float defErrorValue;   //默认火警值
	
	public int getTypeID()
	{
		return typeID;
	}
	public void setTypeID(int typeID)
	{
		this.typeID = typeID;
	}
	public String getTypeName()
	{
		return typeName;
	}
	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}
	public Float getDefWarnValue()
	{
		return defWarnValue;
	}
	public void setDefWarnValue(Float defWarnValue)
	{
		this.defWarnValue = defWarnValue;
	}
	public Float getDefErrorValue()
	{
		return defErrorValue;
	}
	public void setDefErrorValue(Float defErrorValue)
	{
		this.defErrorValue = defErrorValue;
	}
	@Override
	public String toString()
	{
		return "SensorType [typeID=" + typeID + ", typeName=" + typeName
				+ ", defWarnValue=" + defWarnValue + ", defErrorValue="
				+ defErrorValue + "]";
	}

	
}
