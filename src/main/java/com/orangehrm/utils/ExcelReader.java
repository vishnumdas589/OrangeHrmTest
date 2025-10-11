package com.orangehrm.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
	private String path;

	//  Constructor
	public ExcelReader(String path) {
		this.path = path;
	}

	//  1. Return 2D Object array (for TestNG DataProvider)
	public Object[][] getTestData(String sheetName) {
		try (FileInputStream fi = new FileInputStream(path);
			 XSSFWorkbook wb = new XSSFWorkbook(fi)) {

			XSSFSheet sh = wb.getSheet(sheetName);

			int rowCount = sh.getLastRowNum(); // excludes header
			int colCount = sh.getRow(0).getLastCellNum();

			Object[][] data = new Object[rowCount][colCount];
			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i <= rowCount; i++) { // skip header row
				XSSFRow row = sh.getRow(i);
				for (int j = 0; j < colCount; j++) {
					XSSFCell cell = row.getCell(j);
					data[i - 1][j] = formatter.formatCellValue(cell);
				}
			}
			return data;
		} catch (IOException e) {
			throw new RuntimeException("Error reading Excel data: " + e.getMessage(), e);
		}
	}

	// 2. Return Map for quick login lookups
	public Map<String, String> getLoginCredentials(String sheetName) {
		Map<String, String> creds = new HashMap<>();
		try (FileInputStream fi = new FileInputStream(path);
			 XSSFWorkbook wb = new XSSFWorkbook(fi)) {

			XSSFSheet sh = wb.getSheet(sheetName);
			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i <= sh.getLastRowNum(); i++) { // skip header
				XSSFRow row = sh.getRow(i);
				String username = formatter.formatCellValue(row.getCell(0));
				String password = formatter.formatCellValue(row.getCell(1));
				creds.put(username, password);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading login credentials: " + e.getMessage(), e);
		}
		return creds;
	}

	// ✅ 3. Return List of Maps (key = column name, value = cell value)
	public Object[][] getObjOfMap(String sheetName) {
        List<Map<String, String>> allData = new ArrayList<>();
        Object[][] data;
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {

            XSSFSheet sh = wb.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();

            XSSFRow headerRow = sh.getRow(0);
            int colCount = headerRow.getLastCellNum();
            int rowCount = sh.getLastRowNum(); // excludes header
            data = new Object[rowCount][1];
//			for (int i = 0; i < list.size(); i++) {
//				data[i][0] = list.get(i);
//			}
//			return data;
            for (int i = 1; i <= sh.getLastRowNum(); i++) {
                XSSFRow row = sh.getRow(i);
                Map<String, String> dataMap = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    String key = formatter.formatCellValue(headerRow.getCell(j));
                    String value = formatter.formatCellValue(row.getCell(j));
                    dataMap.put(key, value);
                }
                data[i - 1][0] = dataMap;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel data: " + e.getMessage(), e);
        }
        return data;
    }
	public List<Map<String, String>> getDataAsMap(String sheetName) {
		List<Map<String, String>> allData = new ArrayList<>();
		try (FileInputStream fi = new FileInputStream(path);
			 XSSFWorkbook wb = new XSSFWorkbook(fi)) {

			XSSFSheet sh = wb.getSheet(sheetName);
			DataFormatter formatter = new DataFormatter();

			XSSFRow headerRow = sh.getRow(0);
			int colCount = headerRow.getLastCellNum();

			for (int i = 1; i <= sh.getLastRowNum(); i++) {
				XSSFRow row = sh.getRow(i);
				Map<String, String> dataMap = new HashMap<>();
				for (int j = 0; j < colCount; j++) {
					String key = formatter.formatCellValue(headerRow.getCell(j));
					String value = formatter.formatCellValue(row.getCell(j));
					dataMap.put(key, value);
				}
				allData.add(dataMap);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading Excel data: " + e.getMessage(), e);
		}
		return allData;
	}
	public HashSet<String> getDashboardlabels(String sheetName) {
		HashSet<String > set = new HashSet<>();
		try (FileInputStream fi = new FileInputStream(path);
			 XSSFWorkbook wb = new XSSFWorkbook(fi)) {

			XSSFSheet sh = wb.getSheet(sheetName);
			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i <= sh.getLastRowNum(); i++) { // skip header
				XSSFRow row = sh.getRow(i);
				String label = formatter.formatCellValue(row.getCell(0));

				set.add(label);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading Dashboard labels using Excel reader: " + e.getMessage(), e);
		}
		return set;
	}
	public Object [][] getdataAsString(String sheetName) {
		HashSet<String > set = new HashSet<>();
		try (FileInputStream fi = new FileInputStream(path);
			 XSSFWorkbook wb = new XSSFWorkbook(fi)) {

			XSSFSheet sh = wb.getSheet(sheetName);
			DataFormatter formatter = new DataFormatter();

			Object [][] data = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
			for (int i = 1; i <= sh.getLastRowNum(); i++) { // skip header
				XSSFRow row = sh.getRow(i);
				XSSFCell cell = row.getCell(0);
				data[i - 1][0] = formatter.formatCellValue(cell);
//				formatter.formatCellValue(row.getCell(0));
			}
			return data;
		} catch (IOException e) {
			throw new RuntimeException("Error reading Dashboard labels using Excel reader: " + e.getMessage(), e);
		}

	}
}
