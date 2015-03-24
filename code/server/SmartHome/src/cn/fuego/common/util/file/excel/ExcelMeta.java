/**   
* @Title: ExcelMeta.java 
* @Package cn.fuego.common.util.file.excel 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-23 下午9:38:33 
* @version V1.0   
*/ 
package cn.fuego.common.util.file.excel;

import java.util.HashMap;
import java.util.Map;

 /** 
 * @ClassName: ExcelMeta 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-23 下午9:38:33 
 *  
 */
public class ExcelMeta
{
	private Class modelClass;
	private int startRow;
	private String tital;
	private Map<Integer,ExcelColumnMeta> columnMap = new HashMap<Integer,ExcelColumnMeta>();
	
	public ExcelMeta(Class modelClass,int startRow)
	{
		this.modelClass = modelClass;
		this.startRow = startRow;
	}
	public Class getModelClass()
	{
		return modelClass;
	}
	public void setModelClass(Class modelClass)
	{
		this.modelClass = modelClass;
	}
	public int getStartRow()
	{
		return startRow;
	}
	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}
	public String getTital()
	{
		return tital;
	}
	public void setTital(String tital)
	{
		this.tital = tital;
	}
	public Map<Integer, ExcelColumnMeta> getColumnMap()
	{
		return columnMap;
	}
	public void setColumnMap(Map<Integer, ExcelColumnMeta> columnMap)
	{
		this.columnMap = columnMap;
	}
 
	

}
