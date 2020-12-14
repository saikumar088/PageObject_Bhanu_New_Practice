package com.selenium.sai.framework.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelHelperTest {
	
	@Test
	public Object[][] getExcelData () {
		
		try {
			Object  dataSets[][]= null;
			FileInputStream file =new FileInputStream(new File("C:\\Users\\ssk\\eclipse-workspace_v2\\pageObject_LiveProject\\src\\main\\resources\\configfile\\CRM_TestData.xlsx"));
			//Create workbook instance
			XSSFWorkbook  workbook = new XSSFWorkbook(file);
			//Get sheet name
			XSSFSheet sheet =workbook.getSheetAt(0);
			//Get number of rows
			int totalRow=sheet.getLastRowNum();
			System.out.println("total number of Rows:  "+totalRow);
			//Get number of columns
			int totalColumn = sheet.getRow(0).getLastCellNum();
			System.out.println("total number of Rows:  "+totalColumn);
			
			//Initializing number of rows and columns to two dimentional String array
			dataSets= new String[totalRow][totalColumn] ;
			
			for(int row=0;row<totalRow;row++) {
				
				for(int clm=0; clm<totalColumn;clm++) {
					
					XSSFCell cell	=  sheet.getRow(row+1).getCell(clm);
					
					switch(cell.getCellType()) {
					
					case STRING: 	
						dataSets[row][clm]=cell.getStringCellValue(); 
						break;
					case BOOLEAN:
						dataSets[row][clm]=cell.getBooleanCellValue(); 
						break;
					case FORMULA:
						dataSets[row][clm]=cell.getCellFormula(); break;
						default:
							System.out.println("No matching datatype found found"); break;			
					}
								
				}
			}
			workbook.close();
			return dataSets;
		}		
			catch(Exception e) {
				e.printStackTrace();
			}
		
		System.out.println("COmpleted");
		return null;	
	}


	@Test
	public void updateExcelData () {
		
		try {
			//Object  dataSets[][]= null;
			FileInputStream file =new FileInputStream(new File("C:\\Users\\ssk\\eclipse-workspace_v2\\pageObject_LiveProject\\src\\main\\resources\\configfile\\CRM_TestData.xlsx"));
			//Create workbook instance
			XSSFWorkbook  workbook = new XSSFWorkbook(file);
			//Get sheet name
			XSSFSheet sheet =workbook.getSheetAt(0);
			//Get number of rows
			int totalRow=sheet.getLastRowNum()+1;
			System.out.println("total number of Rows:  "+totalRow);
			//Get number of columns
			int totalColumn = sheet.getRow(0).getLastCellNum();
			System.out.println("total number of Rows:  "+totalColumn);
			
			//Initializing number of rows and columns to two dimentional String array
			//dataSets= new String[totalRow][totalColumn] ;
			
			for(int row=0;row<totalRow;row++) {
				
				for(int clm=0; clm<totalColumn;clm++) {
					  XSSFRow r = sheet.getRow(row);
					 String columnValue=  r.getCell(clm).getStringCellValue();
					 
					 if(columnValue.contains("SAI")) {
						 r.createCell(3).setCellValue("PASS");
						 file.close();
						 System.out.println("Updated successfully" );
						 FileOutputStream out =new FileOutputStream(new File("C:\\Users\\ssk\\eclipse-workspace_v2\\pageObject_LiveProject"
						 		+ "\\src\\main\\resources\\configfile\\CRM_TestData.xlsx"));	 
						 workbook.write(out);
						 out.close();
						 break;
						 
					 }
					
			

				}
			}
			workbook.close();
		}		
			catch(Exception e) {
				e.printStackTrace();
			}
		
		System.out.println("COmpleted");

	}	
	
	
	
	
	
	
}


