/**   
* @Title: ExcelToolFactory.java 
* @Package cn.fuego.common.util.file.excel 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-23 下午9:56:02 
* @version V1.0   
*/ 
package cn.fuego.common.util.file.excel;


 /** 
 * @ClassName: ExcelToolFactory 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-23 下午9:56:02 
 *  
 */
public class ExcelToolFactory
{
	private static ExcelToolFactory instance;
	
	private ExcelTool tool;
	
	public static synchronized ExcelToolFactory getInstance()
	{
		if (null == instance)
		{
			instance = new ExcelToolFactory();
		}
		return instance;
	}
	
	public ExcelTool getExcelTool()
	{
 		if(null == tool)
		{
 			tool = new ExcelToolJxlImpl();
		}
		return tool;
	}
}
