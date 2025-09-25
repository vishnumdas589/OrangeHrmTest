package com.orangehrm.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;

public class DashboardPage extends BasePage {
	private static final Logger logger = LoggerManager.getLogger(DashboardPage.class);
	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath = "//h6[text()='Dashboard']")
	WebElement labelDashboard;
	
	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	WebElement lnkUserDropdown;
	
	@FindBy(xpath = "a[text()='Logout']")
	WebElement lnkLogout;
	
	public void clickUserDrop() {
		lnkUserDropdown.click();
	}
	public void clickLogout() {
		lnkUserDropdown.click();
	}
	public boolean isDashboardPageDisplayed() {
		return labelDashboard.isDisplayed();
	}
	

}
