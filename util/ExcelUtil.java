package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	//XSSFWorkbook 이름, 시트 위치, 행의 위치, 셀의 위치 이용하여 셀의 값을 String형으로 반환
	public static String convertString(XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum) throws Exception{
		XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		XSSFCell cell = sheet.getRow(rowNum).getCell(cellNum);
		String cellValue = "";
		
		if(cell != null){
			if(cell.getCellType() == cell.CELL_TYPE_FORMULA){
				cellValue = cell.getCellFormula();
			}
			else if(cell.getCellType() == cell.CELL_TYPE_STRING){
				cellValue = cell.getStringCellValue();
			}
			else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
				cellValue = String.valueOf(cell.getNumericCellValue());
			}
			else if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN){
				cellValue = String.valueOf(cell.getBooleanCellValue());
			}
			else if(cell.getCellType() == cell.CELL_TYPE_BLANK){
				cellValue = "";
			}
			else if(cell.getCellType() == cell.CELL_TYPE_ERROR){
				cellValue = cell.getErrorCellString();
			}
		}
		return cellValue;
	}
	
	//XSSFWorkbook 이름, 시트 위치, 행의 위치, 셀의 위치 이용하여 셀의 값을 Double형으로 반환
	public static Double convertDouble(XSSFWorkbook workbook, int sheetNum, int rowNum, int cellNum){
		XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		XSSFCell cell = sheet.getRow(rowNum).getCell(cellNum);
		double cellValue = 0;
		
		if(cell != null){
			if(cell.getCellType() == cell.CELL_TYPE_FORMULA){
				cellValue = Double.parseDouble(cell.getCellFormula());
			}
			else if(cell.getCellType() == cell.CELL_TYPE_STRING){
				cellValue = Double.parseDouble(cell.getStringCellValue());
			}
			else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
				cellValue = cell.getNumericCellValue();
			}
			else if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN){
				cellValue = Double.parseDouble(String.valueOf(cell.getBooleanCellValue()));
			}
			else if(cell.getCellType() == cell.CELL_TYPE_BLANK){
				cellValue = 0;
			}
			else if(cell.getCellType() == cell.CELL_TYPE_ERROR){
				cellValue = Double.parseDouble(cell.getErrorCellString());
			}
		}
		return cellValue;
	}
}
