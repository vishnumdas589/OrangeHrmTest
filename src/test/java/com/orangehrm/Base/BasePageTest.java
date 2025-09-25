package com.orangehrm.Base;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.db.jdbc.DriverManagerConnectionSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LeavePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utils.ConfigReader;

public class BasePageTest {
	private static final Logger logger = LoggerManager.getLogger(DriverManager.class);

	protected WebDriver driver;
	private DriverManager driverManager;
	@BeforeTest
	@Parameters({"browser"})
	public void setup(@Optional("chrome") String browser) {
		driverManager = new DriverManager(browser);
		driver = driverManager.getDriver();
			
	}

	@AfterTest
	public void tearDown() {
		driverManager.quitDriver();
		
	}
	public void navigateToBaseUrl() {
		driver.get(ConfigReader.getLoginURL());
	}
	
	public WebDriver getDriver() {
		try {
			if(this.driver == null) {
				throw new IllegalStateException("driver has not been initalized");
			}
			return this.driver;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	public String screenShot(String filename) {
		WebDriver driver = getDriver();
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String dest = System.getProperty("user.dir")+"\\output\\screenshots\\"+filename+"_"+ dateName+".png";
		TakesScreenshot takesScreenShot = (TakesScreenshot)driver;
		File srcFile = takesScreenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(dest);
		try {
			FileUtils.copyFile(srcFile, destFile);
		}catch(Exception e) {
			e.getMessage();
		}
			
		return dest;
	}
	
	
	
}
























//dashboardPg = new DashboardPage(driver);
//AdminPage adminPg = new AdminPage(driver);
//LeavePage leavePg = new LeavePage(driver);
//PIMPage pimPg = new PIMPage(driver);
//		if(driver.getCurrentUrl().equalsIgnoreCase(ConfigReader.getProperties("url.dashboard")))