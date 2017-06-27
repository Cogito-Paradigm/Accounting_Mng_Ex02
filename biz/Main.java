package biz;

import java.util.List;

import excel.ExcelMng;
import menu.Menu;
import vo.TotalVO;
import vo.ValueVO;

public class Main {
	public static void main(String[] args) throws Exception{
		String fileName = "D://재무상태표연습.xlsx";
		String sheetName00 = "자산";
		String sheetName01 = "부채";
		String sheetName02 = "자본";
		String sheetName03 = "통합관리";
		TotalVO total = new TotalVO();
		ExcelMng excelMng = new ExcelMng();
		
		excelMng.InputWorkbook(fileName);
		total.setAssetMapList(excelMng.InputSheet(sheetName00));
		total.setLiabilityMapList(excelMng.InputSheet(sheetName01));
		total.setCapitalMapList(excelMng.InputSheet(sheetName02));

		Menu menu = new Menu(total);
		
		excelMng.ExcelOuput(fileName, sheetName00, total.getAssetMapList());
		excelMng.ExcelOuput(fileName, sheetName01, total.getLiabilityMapList());
		excelMng.ExcelOuput(fileName, sheetName02, total.getCapitalMapList());
		excelMng.ExcelOuputTotal(fileName, sheetName03, total);
	}
}
