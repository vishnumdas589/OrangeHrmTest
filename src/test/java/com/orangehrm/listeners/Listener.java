package com.orangehrm.listeners;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.*;
import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.ExtentManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.utils.ScreenshotUtil;

public class Listener implements ITestListener {
	private static ExtentReports extent = ExtentManager.getInstance();
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static final Logger logger = LoggerManager.getLogger(Listener.class);

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("[Listener] Test started: {}", result.getMethod().getMethodName());
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		extentTest.assignCategory(result.getTestClass().getName());//assigning category
		//Assign browser from TestNG parameter (avoid null)
		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
		if (browser != null) {
			extentTest.assignCategory("Browser: " + browser);
		}

		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("[Listener] Test Passed: {}", result.getMethod().getMethodName());
		ExtentTest extentTest = test.get();
		if(extentTest != null) {
			test.get().log(Status.PASS, "Test Passed");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("[Listener] Test Failed: {}", result.getMethod().getMethodName());
		ExtentTest extentTest = test.get();
		if(extentTest == null) {
			logger.error("[Listener] ExtentTest was not initialized for {}. Creating fallback.",
					result.getMethod().getMethodName());
			extentTest = extent.createTest(result.getMethod().getMethodName());
			test.set(extentTest);

		}
		extentTest.log(Status.FAIL, "Test Failed: " + result.getThrowable());


		try {
			// CHANGE: pull driver directly from DriverManager (ThreadLocal-safe)
			WebDriver driver = DriverManager.getDriver();
			String path = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
			extentTest.addScreenCaptureFromPath(path);
			logger.info("[Listener] Added screenshot to report on failed test: {}", result.getMethod().getMethodName());
		} catch (Exception e) {
			logger.error("[Listener] Could not capture screenshot: {}", e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("[Listener] Test Skipped: {}", result.getMethod().getMethodName());
		ExtentTest extentTest = test.get();
		if(extentTest != null) {
			test.get().log(Status.SKIP, "Test Skipped");

		}
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("[Listener] All tests started");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("[Listener] All tests finished. Flushing report.");
		extent.flush();
	}
}
