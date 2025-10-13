package com.orangehrm.tests;

import com.orangehrm.Base.LoginBaseTest;
import com.orangehrm.data.DataProviders;
import com.orangehrm.managers.DriverManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.*;

import com.orangehrm.Base.BasePageTest;

import com.orangehrm.managers.LoggerManager;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class LoginTests extends BasePageTest {
    private static final Logger logger = LoggerManager.getLogger(LoginTests.class);
    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        navigateToBaseUrl();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        if (DriverManager.getDriver().getCurrentUrl().contains("dashboard")) {
            dashboardPg.logout();
            logger.info("[LoginTests] Logged out after test method.");
        }
    }


    @Test(priority = 1,groups = {"smoke"})
    public void verifyLoginPage(){
        navigateToBaseUrl();
        Assert.assertEquals(loginPg.getCurrentUrl(), ConfigReader.getLoginURL());
    }

    @Test(priority = 1,groups = {"smoke","sanity", "regression"})
    public void verifySuccessfulLogin() {
        loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultPassword());
        boolean isLoggedIN = dashboardPg.isDashboardPageDisplayed();
        dashboardPg.logout();
        Assert.assertTrue(isLoggedIN, "Dashboard page not displayed");
    }

    @Test(priority = 2,groups = {"regression"})
    public void verifyLoginWithInValidUsername() {

        loginPg.loginWithCredentials(ConfigReader.getDefaultInvalidUserName(), ConfigReader.getDefaultPassword());
        boolean isErrorDisplayed = loginPg.isErrorDisplayed();
        Assert.assertTrue(isErrorDisplayed, "logged in with Invalid Username ");
    }

    @Test(priority = 2,groups = {"regression"})
    public void verifyLoginWithInValidPassword() {

        loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultInvalidPassword());
        boolean isErrorDisplayed = loginPg.isErrorDisplayed();
        Assert.assertTrue(isErrorDisplayed, "logged in with Invalid password");
    }

    @Test(priority = 3,groups = {"regression"})
    public void verifyLoginWithEmptyCredentials() {

        loginPg.loginWithCredentials("", "");
        boolean isErrorDisplayed = loginPg.isAlertDisplayedForUsername() && loginPg.isAlertDisplayedForPassword();
        Assert.assertTrue(isErrorDisplayed, "logged in with Empty credentials");
    }

    @Test(priority =3,groups = {"sanity", "regression"})
    public void verifySuccessLogOut() {
        loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultPassword());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(dashboardPg.getCurrentUrl(), ConfigReader.getDashboardURL());
        dashboardPg.logout();
        softAssert.assertEquals(loginPg.getCurrentUrl(), ConfigReader.getLoginURL());
        softAssert.assertAll();
    }


    @Test(priority = 4, dataProvider = "loginData2D", dataProviderClass = DataProviders.class,
            groups = {"regression"})
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



}
