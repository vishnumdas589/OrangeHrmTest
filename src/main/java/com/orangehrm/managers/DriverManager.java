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

	// CHANGE: use ThreadLocal instead of a single WebDriver instance
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
	private static final Set<String> browsersUsed = new HashSet<>();

	// CHANGE: remove constructor-based driver creation,
	// instead use initDriver() before each test
	public static void initDriver(String browser) {
		logger.info("[Driver] Initializing driver for browser: {}", browser);
		WebDriver driver;
		switch (browser.toLowerCase()) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup(); // FIXED: wrong setup call
				FirefoxOptions ff = new FirefoxOptions();
				ff.addArguments("--disable-notifications");
				driver = new FirefoxDriver(ff);
				break;
			case "edge":
				WebDriverManager.edgedriver().setup(); // FIXED: wrong setup call
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
		DRIVER.set(driver);// CHANGE: assign driver per thread
		browsersUsed.add(browser);
	}

	public static WebDriver getDriver() {
		return DRIVER.get(); // CHANGE: now always fetch from ThreadLocal
	}

	public static void quitDriver() {
		logger.info("[Driver] Quitting driver");
		WebDriver driver = DRIVER.get();
		if (driver != null) {
			driver.quit();
			DRIVER.remove(); // CHANGE: cleanup ThreadLocal after quit
		}
	}
	public static Set<String> getBrowsersUsed() {
		return browsersUsed;
	}

}
