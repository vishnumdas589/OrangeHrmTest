package com.orangehrm.pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;


public class LoginPage extends BasePage  {
	private static final Logger logger = LoggerManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {	
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement txtUsername;
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement txtPassword;
	@FindBy(xpath ="//button[@type = 'submit']")
	WebElement BtnLogin;
	@FindBy(xpath = "//p[text() ='Invalid credentials']")
	WebElement alertInvalidCredencial;
	@FindBy(xpath = "//input[@name='username']/parent::div/following-sibling::span[text()='Required']")
	WebElement alertUserNameRequired;
	@FindBy(xpath = "//input[@name='password']/parent::div/following-sibling::span[text()='Required']")
	WebElement alertPasswordRequired;
	@FindBy(xpath = "//h5[text()='Login']")
	WebElement labelLogin;
	
	
	public void setUsername(String username) {
		txtUsername.sendKeys(username);
	}
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void clickLogin() {
		BtnLogin.click();
	}
	public boolean isLoginPageDisplayed() {
		return labelLogin.isDisplayed();
	}
	public void loginWithCredentials(String username,String password){
		
		wait.until(ExpectedConditions.visibilityOf(txtUsername));
		txtUsername.clear();
		txtUsername.sendKeys(username);
		txtPassword.clear();
		txtPassword.sendKeys(password);
		BtnLogin.click();
	}
	
	

	
}
