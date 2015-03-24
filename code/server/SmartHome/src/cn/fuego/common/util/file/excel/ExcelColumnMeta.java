/**   
* @Title: ExcelMetaInfo.java 
* @Package cn.fuego.common.util.file.excel 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-23 下午9:19:01 
* @version V1.0   
*/ 
package cn.fuego.common.util.file.excel;

import java.util.HashMap;
import java.util.Map;

 /** 
 * @ClassName: ExcelMetaInfo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-23 下午9:19:01 
 *  
 */
public class ExcelColumnMeta
{
	private int column;
	private String columnName;
	private String dataField;
	private String pointOut;
	private Object defaultValue;
	private Map<String,String> ruleMap = new HashMap<String,String>();
	
	
	public int getColumn()
	{
		return column;
	}
	public void setColumn(int column)
	{
		this.column = column;
	}
	public String getColumnName()
	{
		return columnName;
	}
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
	public String getDataField()
	{
		return dataField;
	}
	public void setDataField(String dataField)
	{
		this.dataField = dataField;
	}
	public String getPointOut()
	{
		return pointOut;
	}
	public void setPointOut(String pointOut)
	{
		this.pointOut = pointOut;
	}
	public Object getDefaultValue()
	{
		return defaultValue;
	}
	public void setDefaultValue(Object defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	public Map<String, String> getRuleMap()
	{
		return ruleMap;
	}
	public void setRuleMap(Map<String, String> ruleMap)
	{
		this.ruleMap = ruleMap;
	}
	 
	
 
}
