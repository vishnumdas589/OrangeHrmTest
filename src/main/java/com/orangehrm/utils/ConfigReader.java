package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class ConfigReader {

	private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "\\resources\\config.properties";
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			FileInputStream fi = new FileInputStream(CONFIG_FILE_PATH);
			if (fi != null) {
				properties.load(fi);
				fi.close();
			} else {
				throw new RuntimeException("config.properties not found!");
			}
		} catch (Exception e) {
			throw new RuntimeException("failed to read config.properties");

		}
	}

	public static String getProperties(String key) {
		return properties.getProperty(key);
	}
	public static int getImplicitWait() {
		return Integer.parseInt(properties.getProperty("implicit.wait"));
	}
	public static int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("explicit.wait"));
	}
	public static String getDefaultUserName() {
		return properties.getProperty("default.username");
	}
	public static String getDefaultPassword() {
		return properties.getProperty("default.password");
	}
	public static String getDefaultInvalidUserName() {
		return properties.getProperty("invalid.username");
	}
	public static String getDefaultInvalidPassword() {
		return properties.getProperty("invalid.password");
	}
	public static String getLoginURL() {
		return properties.getProperty("url.login");
	}
	public static String getWorkBookName() {return properties.getProperty("workbook.name");};
	public static String getDashboardURL() {return properties.getProperty("url.dashboard");}
	public static String getSheetLoginData(){return properties.getProperty("workbook.sheet.LoginData2D");}
	public static String getSheetDashBoardLabelData(){return properties.getProperty("workbook.sheet.DashBoardLabelData");}
	public static String getAssignLeaveURL() {return properties.getProperty("url.assignLeave");}
	public static String getLeaveListURL() {return properties.getProperty("url.leaveList");}
	public static String getEmployeeTimesheetURL() {return properties.getProperty("url.employeeTimesheet");}
	public static String getApplyLeaveURL() {return properties.getProperty("url.applyLeave");}
	public static String getMyLeaveListURL() {return properties.getProperty("url.myLeaveList");}
	public static String getMyTimesheetURL() {return properties.getProperty("url.myTimesheet");}
	public static String getSheetAdminData(){return properties.getProperty("workbook.sheet.AdminData");}
	public static String getOrgStructureData(){return properties.getProperty("workbook.sheet.OrgStructureData");}
	public static String getLeaveData(){return properties.getProperty("workbook.sheet.LeaveData");}



}
