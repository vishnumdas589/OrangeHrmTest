package com.orangehrm.managers;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.orangehrm.utils.ConfigReader;


public class DriverManager {
	private static final Logger logger = LoggerManager.getLogger(DriverManager.class);


	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
	private static final Set<String> browsersUsed = new HashSet<>();


	public static void initDriver(String browser) {
		logger.info("[Driver] Initializing driver for browser: {}", browser);
		WebDriver driver;
		switch (browser.toLowerCase()) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions ff = new FirefoxOptions();
				ff.addArguments("--disable-notifications");
				driver = new FirefoxDriver(ff);
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				EdgeOptions edge = new EdgeOptions();
				edge.addArguments("--disable-notifications");
				driver = new EdgeDriver(edge);
				break;
			default:
				WebDriverManager.chromedriver().setup();
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--disable-notifications");
				driver = new ChromeDriver(co);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
		DRIVER.set(driver);
		browsersUsed.add(browser);
	}

	public static WebDriver getDriver() {
		return DRIVER.get();
	}

	public static void quitDriver() {
		logger.info("[Driver] Quitting driver");
		WebDriver driver = DRIVER.get();
		if (driver != null) {
			driver.quit();
			DRIVER.remove();
		}
	}
	public static Set<String> getBrowsersUsed() {
		return browsersUsed;
	}

}
