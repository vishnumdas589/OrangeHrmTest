package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public FileInputStream fi;
	public FileOutputStream fo ;
	public XSSFWorkbook wb;
	public XSSFSheet sh;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	
	public ExcelReader(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		
		int rowCount = 0;
		if (sh != null) {
			rowCount = sh.getLastRowNum();
		}
		
		wb.close();
		fi.close();
		return rowCount + 1; // getLastRowNum() returns the last row index (0-based)
	}
	public int getColumnCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		
		int colCount = 0;
		if (sh != null) {
			row = sh.getRow(0); // Get the first row
			if (row != null) {
				colCount = row.getLastCellNum();
			}
		}
		
		wb.close();
		fi.close();
		return colCount;
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		}catch(Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;	
	}
	
	
}
