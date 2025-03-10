package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName,int rowNum, int celNum) throws Throwable, IOException {
		
		FileInputStream fis= new FileInputStream("./testdata/TestsciptData.xlsx");
		
		Workbook wb=WorkbookFactory.create(fis);
		
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws Throwable, IOException {
		
		FileInputStream fis=new FileInputStream("./testdata/TestsciptData.xlsx");
		
		Workbook wb=WorkbookFactory.create(fis);
		
		int rowcount=wb.getSheet(sheetName).getLastRowNum();
		
		return rowcount;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int celNum, String data) throws Throwable {
		
		FileInputStream fis=new FileInputStream("./testdata/TestsciptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum);
		
		FileOutputStream fos=new FileOutputStream("./testdata/testsciptdata.xlsx");
		wb.write(fos);
		wb.close();
	}

}
