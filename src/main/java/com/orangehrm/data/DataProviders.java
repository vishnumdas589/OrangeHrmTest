package com.orangehrm.data;

import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.ExcelReader;
import org.testng.annotations.DataProvider;

import java.util.*;

public class DataProviders {
    private static final String EXCEL_PATH = System.getProperty("user.dir") + "/resources/"+ ConfigReader.getWorkBookName();

    // 1. Simple 2D array for username/password/expected
    @DataProvider(name = "loginData2D")
    public Object[][] getLoginData2D() {
        System.out.println(EXCEL_PATH);
        ExcelReader reader = new ExcelReader(EXCEL_PATH);
        return reader.getTestData(ConfigReader.getSheetLoginData()); // sheet with 3 cols (username, password, expected)
    }
    @DataProvider(name = "DashBoardLabelData")
    public Object[][] getDashBoardLabelData() {
        System.out.println(EXCEL_PATH);
        ExcelReader reader = new ExcelReader(EXCEL_PATH);
        return reader.getTestData(ConfigReader.getSheetDashBoardLabelData()); // sheet with 3 cols (username, password, expected)
    }



    // 2. From Map<String, String> of credentials
    @DataProvider(name = "loginDataMap")
    public Object[][] getLoginDataMap() {
        System.out.println(EXCEL_PATH);
        ExcelReader reader = new ExcelReader(EXCEL_PATH);
        Map<String, String> creds = reader.getLoginCredentials(ConfigReader.getSheetLoginData());

        Object[][] data = new Object[creds.size()][2];
        int i = 0;
        for (Map.Entry<String, String> entry : creds.entrySet()) {
            data[i][0] = entry.getKey();   // username
            data[i][1] = entry.getValue(); // password
            i++;
        }
        return data;
    }
    @DataProvider(name = "DashBoardDataSet")
    public Object[][] getDashBoardDataSet() {
        System.out.println(EXCEL_PATH);
        ExcelReader reader = new ExcelReader(EXCEL_PATH);
        HashSet<String> set = reader.getDashboardlabels(ConfigReader.getSheetDashBoardLabelData());

        Object[][] data = new Object[1][1];
        data[0][0] = set;

        return data;
    }
    @DataProvider(name = "orgStructureDataSet")
    public Object[][] getOrgStructureDataSet() {
        System.out.println(EXCEL_PATH);
        ExcelReader reader = new ExcelReader(EXCEL_PATH);
        return reader.getdataAsString(ConfigReader.getOrgStructureData() );
    }


    // 3. Full row as a Map<String,String> (key=column name, value=cell value)
    @DataProvider(name = "loginDataFullMap")
    public Object[][] getLoginDataFullMap() {
        System.out.println(EXCEL_PATH);
        ExcelReader reader = new ExcelReader(EXCEL_PATH);
        List<Map<String, String>> list = reader.getDataAsMap("LoginData");

        Object[][] data = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
        }
        return data;
    }

    @DataProvider(name = "adminDataMap")
    public Object[][] getAdminDataMap() {
        System.out.println(EXCEL_PATH);
        ExcelReader reader = new ExcelReader(EXCEL_PATH);
        return reader.getObjOfMap(ConfigReader.getSheetAdminData());
    }

    @DataProvider(name = "LeaveDataMap")
    public Object[][] getLeaveDataMap() {
        System.out.println(EXCEL_PATH);
        ExcelReader reader = new ExcelReader(EXCEL_PATH);
        return reader.getObjOfMap(ConfigReader.getLeaveData());

    }

}
