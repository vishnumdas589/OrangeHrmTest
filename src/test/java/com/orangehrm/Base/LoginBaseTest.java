package com.orangehrm.Base;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class LoginBaseTest {
    private static final Logger logger = LoggerManager.getLogger(DriverManager.class);
    protected LoginPage loginPg;
    protected DashboardPage dashboardPg;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        DriverManager.initDriver(browser);
        loginPg = new LoginPage();
        dashboardPg = new DashboardPage();
        logger.info("[BasePage BeforeMethod]: driver initialized and assigned, loginpg & dashboardpg objects set  ");
        navigateToBaseUrl();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("[BasePage AfterMethod]: Exited driver ");
    }

    public void navigateToBaseUrl() {
        DriverManager.getDriver().get(ConfigReader.getLoginURL()); // CHANGE: always pull driver from DriverManager
    }


}
