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
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.meta.ReflectionUtil;
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
		if (!excelFile.getName().endsWith(".xls"))
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
			Sheet sheet = book.getSheet(0);
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
					ExcelColumnMeta columnMeta = excelMeta.getColumnMap().get(
							key);
					ReflectionUtil.setObjetField(obj,
							columnMeta.getDataField(), cell.getContents());
				}

				dataList.add(obj);

			}

		} catch (Exception e)
		{

			log.error("read excel file failed", e);
		}

		return dataList;
	}

}
