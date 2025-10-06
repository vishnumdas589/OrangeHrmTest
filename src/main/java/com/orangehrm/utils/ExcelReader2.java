package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader2 {
    private final String path;

    public ExcelReader2(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {

            XSSFSheet sh = wb.getSheet(sheetName);
            return (sh == null) ? 0 : sh.getLastRowNum() + 1;
        }
    }

    public int getColumnCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {

            XSSFSheet sh = wb.getSheet(sheetName);
            if (sh != null) {
                XSSFRow row = sh.getRow(0);
                if (row != null) {
                    return row.getLastCellNum();
                }
            }
            return 0;
        }
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {

            XSSFSheet sh = wb.getSheet(sheetName);
            if (sh == null) return "";

            XSSFRow row = sh.getRow(rownum);
            if (row == null) return "";

            XSSFCell cell = row.getCell(colnum);
            if (cell == null) return "";

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        }
    }
}

