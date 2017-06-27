package excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import biz.BizMng;
import vo.TotalVO;
import vo.ValueVO;

public class ExcelMng {
	XSSFWorkbook workbook;
	
	//row와 cellNum을 받아 해당 셀의 날짜를 String타입으로 반환
	public String dateForm(XSSFRow row, int cellNum ){
		int cellType = row.getCell(cellNum).getCellType();
		String date = "";
		if(cellType==0){
			SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd");
			date = dateform.format(row.getCell(cellNum).getDateCellValue());
		}else if(cellType==1){
			date = row.getCell(cellNum).getStringCellValue();
		}
		return date;
	}

	//fileName으로 파일을 읽어와서 ExcelMng클래스의 workbook 필드에 저장하는 메소드
	public void InputWorkbook(String fileName) throws Exception{
		FileInputStream fileInput = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		this.workbook = workbook;
	}
	
	//워크북과 시트명을 받아서 워크북에 해당 sheet가 있는지 확인 후 있으면 반환, 없으면 생성한 후 반환하는 메소드
	public XSSFSheet checkSheet(XSSFWorkbook workbook, String type){
		try{
			XSSFSheet sheet = 	workbook.getSheet(type);
			return sheet;
		}catch(Exception e){
			XSSFSheet sheet = workbook.createSheet(type);
			return sheet;
		}
	}
	
	//workbook내의 sheet를 type에 따라 호출하여 map으로 반환하는 메소드
	public Map<String, List<ValueVO>> InputSheet(String type) throws Exception{
		//fileName.xlsx 파일을 읽어오기
		 Map<String, List<ValueVO>> mapList = new  HashMap<String, List<ValueVO>>();
		XSSFSheet sheet = checkSheet(workbook, type);
		
		int lastRow = sheet.getLastRowNum();
		
		String date = "";
		String account = "";
		double cha = 0;
		double dae = 0;
		
		for(int i = 1; i<=lastRow; i++){
			XSSFRow row = sheet.getRow(i);
			date = dateForm(row, 0);
			
			if(mapList.containsKey(date)){
				List<ValueVO> imsiList = mapList.get(date);
				account = row.getCell(1).getStringCellValue();
				cha = row.getCell(2).getNumericCellValue();
				dae = row.getCell(3).getNumericCellValue();
				imsiList.add(new ValueVO(type, account, cha, dae));
				mapList.put(date, imsiList);
			}else{
				List<ValueVO> imsiList = new ArrayList<ValueVO>();
				account = row.getCell(1).getStringCellValue();
				cha = row.getCell(2).getNumericCellValue();
				dae = row.getCell(3).getNumericCellValue();
				imsiList.add(new ValueVO(type, account, cha, dae));
				mapList.put(date, imsiList);
			}
		}
		return mapList;
	}

	//전달된 mapList의 정보를 fileName의 엑셀파일에 저장하는 메소드
	public void ExcelOuput(String fileName, String type, Map<String, List<ValueVO>> parameter) throws Exception{

		XSSFSheet sheet = checkSheet(workbook, type);
		
		XSSFRow rowFirst = sheet.createRow(0);
		rowFirst.createCell(0).setCellValue("날짜");
		rowFirst.createCell(1).setCellValue("계정");
		rowFirst.createCell(2).setCellValue("차변");
		rowFirst.createCell(3).setCellValue("대변");
		
		BizMng biz = new BizMng();
		Map<String, List<ValueVO>> mapList = parameter;
		mapList = biz.arrangeList(mapList);
		
		int i = 1;
		for(String key : mapList.keySet()){
			for(ValueVO e : mapList.get(key)){
				XSSFRow row = sheet.createRow(i);
				XSSFCell cell00 = row.createCell(0);
				XSSFCell cell01 = row.createCell(1);
				XSSFCell cell02 = row.createCell(2);
				XSSFCell cell03 = row.createCell(3);
				
				cell00.setCellValue(key);
				cell01.setCellValue(e.getAccount());
				if(e.getCha()>0){
					cell02.setCellValue(e.getCha());
				}else if(e.getDae()>0){
					cell03.setCellValue(e.getDae());
				}
				i++;
			}
		}
		FileOutputStream file = new FileOutputStream(fileName);
		workbook.write(file);
		file.close();
	}
	
 	//TotalVO를 이용해 자산, 부채, 자본이 통합된 엑셀 시트를 만듬
	public void ExcelOuputTotal(String fileName, String type, TotalVO total) throws Exception{
		Map<String, List<ValueVO>>totalList = new HashMap<String, List<ValueVO>>();
		BizMng biz = new BizMng();
		totalList = biz.createTotal(total);
		
		XSSFSheet sheet = checkSheet(workbook, type);
		
		XSSFRow rowFirst = sheet.createRow(0);
		rowFirst.createCell(0).setCellValue("날짜");
		rowFirst.createCell(1).setCellValue("자산계정");
		rowFirst.createCell(2).setCellValue("자산차변");
		rowFirst.createCell(3).setCellValue("자산대변");
		rowFirst.createCell(4).setCellValue("부채계정");
		rowFirst.createCell(5).setCellValue("부채차변");
		rowFirst.createCell(6).setCellValue("부채대변");
		rowFirst.createCell(7).setCellValue("자본계정");
		rowFirst.createCell(8).setCellValue("자본차변");
		rowFirst.createCell(9).setCellValue("자본대변");
		
		int i = 1;
		for(String key : totalList.keySet()){
			for(ValueVO e : totalList.get(key)){
				if(e.getType()=="자산"){
					XSSFRow row = sheet.createRow(i);
					XSSFCell cell00 = row.createCell(0);
					XSSFCell cell01 = row.createCell(1);
					XSSFCell cell02 = row.createCell(2);
					XSSFCell cell03 = row.createCell(3);
					
					cell00.setCellValue(key);
					cell01.setCellValue(e.getAccount());
					if(e.getCha()>0){
						cell02.setCellValue(e.getCha());
					}else if(e.getDae()>0){
						cell03.setCellValue(e.getDae());
					}
					
					i++;
				}else if(e.getType()=="부채"){
					XSSFRow row = sheet.createRow(i);
					XSSFCell cell00 = row.createCell(0);
					XSSFCell cell01 = row.createCell(4);
					XSSFCell cell02 = row.createCell(5);
					XSSFCell cell03 = row.createCell(6);
					
					cell00.setCellValue(key);
					cell01.setCellValue(e.getAccount());
					if(e.getCha()>0){
						cell02.setCellValue(e.getCha());
					}else if(e.getDae()>0){
						cell03.setCellValue(e.getDae());
					}
					
					i++;
				}else if(e.getType()=="자본"){
					XSSFRow row = sheet.createRow(i);
					XSSFCell cell00 = row.createCell(0);
					XSSFCell cell01 = row.createCell(7);
					XSSFCell cell02 = row.createCell(8);
					XSSFCell cell03 = row.createCell(9);

					
					cell00.setCellValue(key);
					cell01.setCellValue(e.getAccount());
					if(e.getCha()>0){
						cell02.setCellValue(e.getCha());
					}else if(e.getDae()>0){
						cell03.setCellValue(e.getDae());
					}

					i++;
				}
			}
		}

		FileOutputStream file = new FileOutputStream(fileName);
		workbook.write(file);
		file.close();
	}
}
