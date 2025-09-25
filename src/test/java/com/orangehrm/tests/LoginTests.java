package com.orangehrm.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.Base.BasePageTest;
import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;

public class LoginTests extends BasePageTest {
	private static final Logger logger = LoggerManager.getLogger(LoginTests.class);

	DashboardPage dashboardPg;
	LoginPage loginPg;
//	@Test
//	public void IntentionalFailureTest() {
//		navigateToBaseUrl();
//		loginPg = new LoginPage(driver);
//		driver.get(ConfigReader.getLoginURL());
//		loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(),ConfigReader.getDefaultPassword());
//		dashboardPg = new DashboardPage(driver);
//	
//		Assert.assertFalse(dashboardPg.isDashboardPageDisplayed(), "dashboard page not displayed");
//	}
	@Test
	public void testSuccessfulLogin() {
		navigateToBaseUrl();
		loginPg = new LoginPage(driver);
		driver.get(ConfigReader.getLoginURL());
		loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(),ConfigReader.getDefaultPassword());
		dashboardPg = new DashboardPage(driver);
	
		Assert.assertTrue(dashboardPg.isDashboardPageDisplayed(), "dashboard page not displayed");
	}

	@Test
	public void testLoginWithInValidCredentials(String username,String password) {
		logger.error("[Test] logged in with InValid Credentials");
		Assert.assertTrue(false,"logged in with invlaid credencial");
	}
	

}
