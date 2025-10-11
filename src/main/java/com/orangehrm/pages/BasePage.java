package com.orangehrm.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.utils.ConfigReader;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	private static final Logger logger = LoggerManager.getLogger(BasePage.class);

	@FindBy(xpath = "//span[text()='Admin']")
	WebElement adminMenu;
	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pimMenu;
	@FindBy(xpath = "//span[text()='Leave']")
	WebElement leaveMenu;

	public By getToast() {
		return toast;
	}

	private final By toast = By.cssSelector("div.oxd-toast");

	public BasePage() {
		// TODO Auto-generated constructor stub
		this.driver = DriverManager.getDriver();
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
	public String getCurrentUrl() {return driver.getCurrentUrl();}
	public void navigateToPreviousUrl() {
		driver.navigate().back();
	}
	public void navigateToPimPage() {pimMenu.click();}
	public void navigateToAdminPage() {adminMenu.click();}
	public void navigateToLeavePage() {leaveMenu.click();}

	public void waitUntilVisibility(WebElement element) {wait.until(ExpectedConditions.visibilityOf(element));}
	public void waitUntilClickable(WebElement element) {wait.until(ExpectedConditions.elementToBeClickable(element));}
	public void waitUntilAllElementVisibile(List<WebElement> elements) {wait.until(ExpectedConditions.visibilityOfAllElements(elements));}
	public boolean  isToastDisplayed(String msg) {
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(toast, msg));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

}

