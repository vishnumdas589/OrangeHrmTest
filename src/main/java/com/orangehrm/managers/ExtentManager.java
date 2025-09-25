package com.orangehrm.managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;
	public static ExtentReports getInstance() {
		if(extent == null) {
			String reportPath = System.getProperty("user.dir")+"\\output\\test-output\\extent-report.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setReportName("OrangeHRM Automation Test Report");
			reporter.config().setDocumentTitle("Test Execution Result");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent; 
	}
	

}
