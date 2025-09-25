package com.orangehrm.managers;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.logging.log4j.LogManager;

import com.orangehrm.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private static final Logger logger = LoggerManager.getLogger(DriverManager.class);
	private WebDriver driver;
	
	public DriverManager(String broswer) {
		logger.info("[Driver] Executing DriverManager constructor");
		this.driver = getDriver(broswer);
		setupDriver();
	};
	public WebDriver getDriver() {
		return this.driver;
	}
	
	private WebDriver getDriver(String browser) {
		logger.info("[Driver] Creating {} Driver", browser);
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.chromedriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--disable-notifications");
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			WebDriverManager.chromedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--disable-notifications");
			driver = new EdgeDriver(edgeOptions);
			break;
		default:logger.error("[Driver] invalid broswer entry detected! browser = {}" , browser);
			throw new IllegalArgumentException("invalid browser - " + browser);
			
		}
		logger.info("[Driver] successfully created {} driver", browser);
		return driver;

	}
	
	private void setupDriver() {
		logger.info("[Driver] setting up driver" );
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
		logger.info("[Driver] successfully set up of driver");
	}
	
	public  void quitDriver() {
		logger.info("[Driver] quitting driver");
		if (driver!= null) {
			driver.quit();	
		}
	}

}
