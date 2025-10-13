package com.orangehrm.Base;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.utils.ConfigReader;

public class BasePageTest {
	private static final Logger logger = LoggerManager.getLogger(BasePageTest.class);
	protected LoginPage loginPg;
	protected DashboardPage dashboardPg;

	@BeforeClass(alwaysRun = true)
	@Parameters({"browser"})
	public void setup(@Optional("chrome") String browser) {
		DriverManager.initDriver(browser);
		loginPg = new LoginPage();
		dashboardPg = new DashboardPage();

		logger.info("[BaseTest] Driver initialized for browser: {}", browser);
		logger.info("[BaseTest] Page objects created successfully.");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			if (DriverManager.getDriver() != null) {
				DriverManager.quitDriver();
				logger.info("[BaseTest] Driver quit successfully.");
			}
		} catch (Exception e) {
			logger.error("[BaseTest] Error while quitting driver: {}", e.getMessage());
		}
	}

	protected void navigateToBaseUrl() {
		DriverManager.getDriver().get(ConfigReader.getLoginURL());
		logger.info("[BaseTest] Navigated to base URL: {}", ConfigReader.getLoginURL());
	}
}