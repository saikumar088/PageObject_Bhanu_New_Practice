package com.selenium.sai.framework.helper.excel;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.selenium.sai.framework.helper.logger.LoggerHelper;


public class ExcelHelper {

	private Logger log = LoggerHelper.getLogger(ExcelHelper.class);

	public Object[][] getExcelData(String excelLocation, String sheetName) {

		try {
			Object dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get sheet Name from Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// count number of active rows in excel sheet
			int totalRow = sheet.getLastRowNum();
            System.out.println(totalRow);
			// count active columns in row
			int totalColumn = sheet.getRow(0).getLastCellNum();

			dataSets = new Object[totalRow+1][totalColumn];

			// Iterate Through each Rows one by one.
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				// for Every row , we need to iterator over columns
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					
					Cell cell = cellIterator.next();
					
					/* Not Needed
					if (cell.getStringCellValue().contains("Start")) {
						i = 0;
						break;
					}*/
					
					
					switch (cell.getCellType()) {
					case STRING:
						dataSets[i-1][j++] = cell.getStringCellValue();
						break;
					case NUMERIC:
						dataSets[i-1][j++] = cell.getNumericCellValue();
						break;
					case BOOLEAN:
						dataSets[i-1][j++] = cell.getBooleanCellValue();
					case FORMULA:
						dataSets[i-1][j++] = cell.getNumericCellValue();
						break;

					default:
						System.out.println("no matching enum date type found");
						break;
					}
				}
			}
			workbook.close();
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateResult(String excelLocation, String sheetName, String testCaseName, String testStatus){
		try{
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get sheet Name from Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active rows in excel sheet
			int totalRow = sheet.getLastRowNum()+1;
			for(int i =1; i<totalRow; i++){
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(0).getStringCellValue();
				if(ce.contains(testCaseName)){
					r.createCell(1).setCellValue(testStatus);
					file.close();
					
					log.info("result updated..");
					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					workbook.close();
					break;
				}
			}
		}
		catch(Exception e){
			
		}
	}
	
/*	public static void main(String[] args) {
	 ExcelHelper	 excelHelper = new ExcelHelper();
	 String excelLocation = ResourceHelper.getResourcePath("/src/main/resources/configfile/testData.xlsx");
	 
	 Object[][] data = excelHelper.getExcelData(excelLocation, "TestData");
	 for(int r=0;r<data.length;r++) {
		 
		 for(int c=0;c<data[r].length;c++) {
			 
			 System.out.print(data[r][c]+" \t");
		 }
		 System.out.println();
	 }
	 
	 //System.out.println(data);
	 excelHelper.updateResult(excelLocation, "TestResults", "Test1", "PASS");
	 excelHelper.updateResult(excelLocation, "TestResults", "Test3", "FAIL");
	 excelHelper.updateResult(excelLocation, "TestResults", "Test2", "PASS");
	 
	}*/
}
