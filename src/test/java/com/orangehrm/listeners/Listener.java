package com.orangehrm.listeners;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.orangehrm.Base.BasePageTest;
import com.orangehrm.managers.ExtentManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.pages.BasePage;

public class Listener implements ITestListener {
	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest>test = new ThreadLocal<>();
	private static final Logger logger = LoggerManager.getLogger(Listener.class);
   
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("[Listener] Test started: {}",result.getMethod().getMethodName());
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}
    
	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("[Listener] Test Passed: {}",result.getMethod().getMethodName());
		test.get().log(Status.PASS, "Test Passed");
		
	}

    @Override
	public void onTestFailure(ITestResult result) {
		logger.info("[Listener] Test Failed: {}",result.getMethod().getMethodName());
		test.get().log(Status.FAIL,"Test Failed:"+ result.getThrowable());
		logger.info("[Listener] Initiating capturing screenshot to report for Test Failed: {}",result.getMethod().getMethodName());
		BasePageTest baseTest =(BasePageTest)result.getInstance();
		String pathString= baseTest.screenShot(result.getName());
		test.get().addScreenCaptureFromPath(pathString);
		logger.info("[Listener] Added screenshot to report on Failed Test: {}",result.getMethod().getMethodName());
		 
	}

    @Override
	public void onTestSkipped(ITestResult result) {
		logger.info("[Listener] Test Skipped: {}",result.getMethod().getMethodName());
        test.get().log(Status.SKIP, "Test Skipped");
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
