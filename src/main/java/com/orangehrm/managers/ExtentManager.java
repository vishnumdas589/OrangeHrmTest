package com.orangehrm.managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.File;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			// CHANGE: use File.separator for OS-independence
			String reportPath = System.getProperty("user.dir") + File.separator + "output"
					+ File.separator + "test-output" + File.separator + "extent-report.html";

			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setReportName("OrangeHRM Automation Test Report");
			reporter.config().setDocumentTitle("OrangeHRM Test Execution Result");
			reporter.config().setTheme(Theme.DARK);
			reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");



			extent = new ExtentReports();
			extent.attachReporter(reporter);

			extent.setSystemInfo("Project", "OrangeHRM Automation");
			extent.setSystemInfo("Build", "1.0.0");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			String browsers = String.join(", ", DriverManager.getBrowsersUsed());
			extent.setSystemInfo("Browsers Used", browsers.isEmpty() ? "N/A" : browsers);

			extent.setSystemInfo("Tester", "Vishnu Mohandas");

		}
		return extent;
	}
	

}
