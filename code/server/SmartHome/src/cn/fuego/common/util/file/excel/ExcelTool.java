/**   
* @Title: ExcelTool.java 
* @Package cn.fuego.common.util.file.excel 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-23 下午9:07:08 
* @version V1.0   
*/ 
package cn.fuego.common.util.file.excel;

import java.io.File;
import java.util.List;

 /** 
 * @ClassName: ExcelTool 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-23 下午9:07:08 
 *  
 */
public interface ExcelTool
{
	public List readExcel(File excelFile,ExcelMeta excelMeta);
	public List readExcel(String fileName, ExcelMeta excelMeta);

}
