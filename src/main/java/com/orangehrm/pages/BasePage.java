package com.orangehrm.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.utils.ConfigReader;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	private static final Logger logger = LoggerManager.getLogger(BasePage.class);
	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver,this);
		wait = new WebDriverWait(driver,Duration.ofSeconds(ConfigReader.getExplicitWait()));
	}
	public void hooverOverElement(WebElement target) {
		actions.moveToElement(target).perform();
	}
	public void dragAndDrop(WebElement source,WebElement target) {
		actions.dragAndDrop(source, target).perform();
	}

}

