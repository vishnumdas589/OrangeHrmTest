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

	public LoginPage() {

		super();
	}
	
	@FindBy(xpath="//input[@placeholder='Username']")
	private WebElement txtUsername;
	@FindBy(xpath="//input[@placeholder='Password']")
	private WebElement txtPassword;
	@FindBy(xpath ="//button[@type = 'submit']")
	private WebElement BtnLogin;
	@FindBy(xpath = "//p[text() ='Invalid credentials']")
	private WebElement alertInvalidCredencial;
	@FindBy(xpath = "//input[@name='username']/parent::div/following-sibling::span[text()='Required']")
	private WebElement alertUserNameRequired;
	@FindBy(xpath = "//input[@name='password']/parent::div/following-sibling::span[text()='Required']")
	private WebElement alertPasswordRequired;
	@FindBy(xpath = "//h5[text()='Login']")
	private WebElement labelLogin;


	public WebElement getTxtUsername() {
		return txtUsername;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public void setUsername(String username) {
		txtUsername.sendKeys(username);
	}
	public void setPassword(String password) {txtPassword.sendKeys(password);
	}
	public void clearUsername() {txtUsername.clear();}
	public void clearPassword() {txtPassword.clear();}
	public void clickLogin() {
		BtnLogin.click();
	}
	public boolean isLoginPageDisplayed() {
		return labelLogin.isDisplayed();
	}
	public void loginWithCredentials(String username,String password){

		waitUntilVisibility(getTxtUsername());
		clearUsername();
		setUsername(username);
		clearPassword();
		setPassword(password);
		clickLogin();
	}
	public boolean isErrorDisplayed() {
		try{
			return alertInvalidCredencial.isDisplayed();
		}catch(Exception e){
			return false;
		}

	}
	public boolean isAlertDisplayedForUsername() {
		try{
			return alertUserNameRequired.isDisplayed();
		}catch(Exception e){
			return false;
		}
	}
	public boolean isAlertDisplayedForPassword() {
		try{
			return alertPasswordRequired.isDisplayed();
		}catch(Exception e){
			return false;
		}
	}
	public boolean isAnyErrorDisplayed()  {
			return	isAlertDisplayedForPassword()||isErrorDisplayed()||isAlertDisplayedForUsername();

	}






}
