/**   
 * @Title: ExcelToolJxlImpl.java 
 * @Package cn.fuego.common.util.file.excel 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2015-3-23 下午9:08:42 
 * @version V1.0   
 */
package cn.fuego.common.util.file.excel;

 
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DataCreateUtil;
import cn.fuego.common.util.format.DateUtil;
import cn.fuego.common.util.meta.ReflectionUtil;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MISPException;

/**
 * @ClassName: ExcelToolJxlImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-23 下午9:08:42
 * 
 */
public class ExcelToolJxlImpl implements ExcelTool
{
	private static final FuegoLog log = FuegoLog.getLog(ExcelToolJxlImpl.class);

	public static final int defualt_sheet = 0;
	public static final String file_format = ".xls";
	
	public List readExcel(String fileName, ExcelMeta excelMeta)
	{
		File file = new File(fileName);
		return readExcel(file,  excelMeta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.common.util.file.excel.ExcelTool#readExcel()
	 */
	@Override
	public List readExcel(File excelFile, ExcelMeta excelMeta)
	{
		if (!excelFile.getName().endsWith(file_format))
		{
			throw new MISPException(
					MISPErrorMessageConst.ERROR_FILE_FORMAT_NOT_RIGHT);
		}

		Workbook book;

		List dataList = new ArrayList();
		try
		{
			book = Workbook.getWorkbook(excelFile);

			if (book == null)
			{
				throw new MISPException(
						MISPErrorMessageConst.ERROR_FILE_FORMAT_NOT_RIGHT);
			}
			// 3. 获取所有workSheets
			Sheet sheet = book.getSheet(defualt_sheet);
			int column = sheet.getColumns();
			int row = sheet.getRows();
			log.info("Excel Load Info: row=" + row + "; column=" + column + ";");

			Cell cell;

			for (int i = excelMeta.getStartRow(); i < row; i++)
			{

				Object obj = excelMeta.getModelClass().newInstance();
				Set<Integer> keys = excelMeta.getColumnMap().keySet();
				for (Integer key : keys)
				{
					cell = sheet.getCell(key, i);
					ExcelColumnMeta columnMeta = excelMeta.getColumnMap().get(key);
					if(!ValidatorUtil.isEmpty(cell.getContents()))
					{
						ReflectionUtil.setObjetField(obj,columnMeta.getDataField(), cell.getContents());
					}
					else
					{
						ReflectionUtil.setObjetField(obj,columnMeta.getDataField(), columnMeta.getDefaultValue());
					}
					
				}

				dataList.add(obj);

			}

		} catch (Exception e)
		{

			log.error("read excel file failed", e);
			throw new MISPException(
					MISPErrorMessageConst.ERROR_WRITE_FILE);
		}

		return dataList;
	}

	@Override
	public void writeExcel(List objList, String path,ExcelMeta excelMeta)
	{
 		File file = new File(path);
		try
		{
			WritableWorkbook book = Workbook.createWorkbook(file);
			WritableSheet sheet = book.createSheet("sheet1", defualt_sheet);
			int row = excelMeta.getStartRow();
  
			writeHeader(sheet,0,excelMeta);
			for(Object obj : objList)
			{
				Set<Integer> keys = excelMeta.getColumnMap().keySet();
				for (Integer key : keys)
				{
					ExcelColumnMeta columnMeta = excelMeta.getColumnMap().get(key);
					Object value = ReflectionUtil.getValueByFieldName(obj, columnMeta.getDataField());
					Label label = new Label(columnMeta.getColumn(),row,String.valueOf(value));
					sheet.addCell(label);
				}
				row++;
			}
			book.write();
			book.close();
		}
		catch (Exception e) 
		{

			log.error("write excel file failed", e);
			throw new MISPException( MISPErrorMessageConst.ERROR_DOWN_FILE);
		}
		
	}
	
	private void writeHeader(WritableSheet sheet,int rowNum,ExcelMeta excelMeta) throws RowsExceededException, WriteException
	{
		Set<Integer> keys = excelMeta.getColumnMap().keySet();
		for (Integer key : keys)
		{
			ExcelColumnMeta columnMeta = excelMeta.getColumnMap().get(key);
 			Label label = new Label(columnMeta.getColumn(),rowNum,columnMeta.getColumnName());
			sheet.addCell(label);
		}
	}

}
