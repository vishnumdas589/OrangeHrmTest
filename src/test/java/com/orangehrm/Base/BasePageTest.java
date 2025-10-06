package com.orangehrm.Base;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.utils.ConfigReader;

public class BasePageTest {
	private static final Logger logger = LoggerManager.getLogger(DriverManager.class);
	protected LoginPage loginPg;
	protected DashboardPage dashboardPg;

		@BeforeClass
	    @Parameters({"browser"})
	    public void setup(@Optional("chrome") String browser) {
	        DriverManager.initDriver(browser);// CHANGE: no new DriverManager instance, call initDriver()
	        loginPg = new LoginPage();
	        dashboardPg = new DashboardPage();
	        navigateToBaseUrl();
	        loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultPassword());
	        logger.info("[LoginBasePage BeforeClass]: navigateToBaseUrl and logged in to dashboard page  ");
	        logger.info("[LoginBasePage BeforeClass]: driver initialized and assigned, loginpg & dashboardpg objects set  ");
	    }

	    @AfterClass// CHANGE: use @AfterMethod instead of @AfterTest
	    public void tearDown() {
	        dashboardPg.logout();
	        logger.info("[LoginBasePage AfterClass]: logged out of  DashBoardPage ");

	        DriverManager.quitDriver();
	        logger.info("[LoginBasePage AfterClass]: Exited driver ");
	    }

	    public void navigateToBaseUrl() {
	        DriverManager.getDriver().get(ConfigReader.getLoginURL()); // CHANGE: always pull driver from DriverManager
	    }
		public void navigateToPreviousUrl(){
		DriverManager.getDriver().navigate().back();
		}



	// CHANGE: remove screenshot responsibility from BaseTest (moved to ScreenshotUtil)
	// Keeping here only if you want it, but recommend removing this method entirely

}
