package com.orangehrm.tests;

import com.orangehrm.Base.LoginBaseTest;
import com.orangehrm.data.DataProviders;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.Base.BasePageTest;

import com.orangehrm.managers.LoggerManager;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class LoginTests extends LoginBaseTest {
    private static final Logger logger = LoggerManager.getLogger(LoginTests.class);

    @Test(priority = 1)
    public void verifySuccessfulLogin() {
        loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultPassword());
        boolean isLoggedIN = dashboardPg.isDashboardPageDisplayed();
        dashboardPg.logout();
        Assert.assertTrue(isLoggedIN, "Dashboard page not displayed");
    }

    @Test(priority = 2)
    public void verifyLoginWithInValidUsername() {

        loginPg.loginWithCredentials(ConfigReader.getDefaultInvalidUserName(), ConfigReader.getDefaultPassword());
        boolean isErrorDisplayed = loginPg.isErrorDisplayed();
        Assert.assertTrue(isErrorDisplayed, "logged in with Invalid Username ");
    }

    @Test(priority = 2)
    public void verifyLoginWithInValidPassword() {

        loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultInvalidPassword());
        boolean isErrorDisplayed = loginPg.isErrorDisplayed();
        Assert.assertTrue(isErrorDisplayed, "logged in with Invalid password");
    }

    @Test(priority = 3)
    public void verifyLoginWithEmptyCredentials() {

        loginPg.loginWithCredentials("", "");
        boolean isErrorDisplayed = loginPg.isAlertDisplayedForUsername() && loginPg.isAlertDisplayedForPassword();
        Assert.assertTrue(isErrorDisplayed, "logged in with Empty credentials");
    }

    @Test(priority = 3)
    public void verifySuccessLogOut() {
        loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultPassword());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(dashboardPg.getCurrentUrl(), ConfigReader.getDashboardURL());
        dashboardPg.logout();
        softAssert.assertEquals(loginPg.getCurrentUrl(), ConfigReader.getLoginURL());
        softAssert.assertAll();
    }


    @Test(priority = 3, dataProvider = "loginData2D", dataProviderClass = DataProviders.class)
    public void verifyDataDrivenLoginTest(String username, String password, String expectedStr) {
        loginPg.loginWithCredentials(username, password);
        switch (expectedStr.trim().toLowerCase()) {
            case "success":
                boolean isLoggedIN = dashboardPg.isDashboardPageDisplayed();
                dashboardPg.logout();
                Assert.assertTrue(isLoggedIN, "Dashboard page is not displayed");
                break;
            case "failure":
                boolean isErrorDisplayed = loginPg.isAnyErrorDisplayed();
                Assert.assertTrue(isErrorDisplayed, "No Errors displayed in login page for invalid credentials");
                break;
            default:
                Assert.fail("Unexpected expectedvalue : " + expectedStr);

        }
    }
//	@Test(dataProvider = "loginDataMap", dataProviderClass = DataProviders.class)
//	public void testLoginMap(String username, String password) {
//		loginPg.loginWithCredentials(username, password);
//		Assert.assertTrue(dashboardPg.isDashboardPageDisplayed());
//	}
//	@Test(dataProvider = "loginDataFullMap", dataProviderClass = DataProviders.class)
//	public void testLoginFullMap(Map<String, String> data) {
//		String username = data.get("username");
//		String password = data.get("password");
//		boolean expected = Boolean.parseBoolean(data.get("expected"));
//
//		loginPg.loginWithCredentials(username, password);
//		boolean actual = dashboardPg.isDashboardPageDisplayed();
//		Assert.assertEquals(actual, expected);
//	}
//


}
