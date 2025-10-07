package com.orangehrm.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class AdminPage extends BasePage {
	private static final Logger logger = LoggerManager.getLogger(AdminPage.class);

	@FindBy(xpath = "//span[text()='Leave']")
	private WebElement menuLeave;

	@FindBy(xpath = "//a[normalize-space()='Apply']")
	private WebElement tabApplyLeave;

	@FindBy(xpath = "//a[normalize-space()='My Leave']")
	private WebElement tabMyLeave;

	@FindBy(xpath = "//a[normalize-space()='Entitlements']")
	private WebElement tabEntitlements;

	@FindBy(xpath = "//a[normalize-space()='Reports']")
	private WebElement tabReports;

	@FindBy(xpath = "//a[normalize-space()='Assign Leave']")
	private WebElement tabAssignLeave;

	@FindBy(xpath = "//span[normalize-space()='Admin']")
	private WebElement menuAdmin;

	@FindBy(xpath = "//span[normalize-space()='User Management']")
	private WebElement menuUserManagement;

	@FindBy(xpath = "//span[normalize-space()='Job']")
	private WebElement menuJob;

	@FindBy(xpath = "//span[normalize-space()='Organization']")
	private WebElement menuOrganization;

	@FindBy(xpath = "//a[normalize-space()='Structure']")
	private WebElement subMenuOrganizationStructure;

	// ---------- COMMON BUTTONS ----------
	@FindBy(xpath = "//button[normalize-space()='Add']")
	private WebElement btnAdd;

	@FindBy(xpath = "//button[@type='submit' and normalize-space()='Search']")
	private WebElement btnSearch;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	private WebElement btnSave;

	// ---------- INPUTS ----------
	@FindBy(xpath = "//label[normalize-space()='Username']/../following-sibling::div//input")
	private WebElement txtUsername;

	@FindBy(xpath = "//label[normalize-space()='Employee Name']/../following-sibling::div//input")
	private WebElement txtEmployeeName;

	@FindBy(xpath = "//div[contains(@class,'oxd-input-field-bottom-space')]//input")
	private WebElement txtNameInput;

	// ---------- LOCATORS ----------
	private final By systemUsersHeader = By.xpath("//h5[normalize-space()='System Users']");
	private final By listTableBody = By.xpath("//div[@role='table']//div[@role='rowgroup']");
	private final By listNoRecords = By.xpath("//span[contains(.,'No Records Found')]");
	private final By spinner = By.xpath("//div[contains(@class,'oxd-loading-spinner')]");
	private final By toast = By.cssSelector("div.oxd-toast");

	public AdminPage() {
		super();
	}


	public void openAdminModule() {
		wait.until(ExpectedConditions.elementToBeClickable(menuAdmin)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(systemUsersHeader));
		logger.info("[AdminPage] Opened Admin module");
	}

	public void navigateToUserManagement() {
		wait.until(ExpectedConditions.elementToBeClickable(menuUserManagement)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(systemUsersHeader));
		logger.info("[AdminPage] Navigated to User Management");
	}

	private void navigateToJobSubMenu(String subMenuText) {
		wait.until(ExpectedConditions.elementToBeClickable(menuJob)).click();
		By sub = By.xpath("//a[normalize-space()='" + subMenuText + "']");
		wait.until(ExpectedConditions.elementToBeClickable(sub)).click();
		waitForTableOrEmpty();
	}

	public boolean navigateToOrganizationStructure() {
		wait.until(ExpectedConditions.elementToBeClickable(menuOrganization)).click();
		wait.until(ExpectedConditions.elementToBeClickable(subMenuOrganizationStructure)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Organization Structure']")));
			return true;
		} catch (TimeoutException e) {
			logger.error("[AdminPage] Organization Structure header not visible");
			return false;
		}
	}


	public boolean addUser(Map<String, String> data) {
		String userRole = data.getOrDefault("userRole", "ESS");
		String empName = data.getOrDefault("empName", "");
		String status = data.getOrDefault("status", "Enabled");
		String username = data.getOrDefault("username", "");
		String password = data.getOrDefault("password", "");

		btnAdd.click();
		selectDropdownByLabel("User Role", userRole);

		txtEmployeeName.sendKeys(empName);
		try {
			By suggestionItem = By.xpath("//div[@role='listbox']//div[@role='option'][1]");
			wait.until(ExpectedConditions.elementToBeClickable(suggestionItem)).click();
		} catch (TimeoutException e) {
			logger.error("Employee autocomplete not found for " + empName);
			return false;
		}

		selectDropdownByLabel("Status", status);
		txtUsername.sendKeys(username);

		WebElement pass = driver.findElement(By.xpath("//label[normalize-space()='Password']/../following-sibling::div//input[@type='password']"));
		WebElement cpass = driver.findElement(By.xpath("//label[normalize-space()='Confirm Password']/../following-sibling::div//input[@type='password']"));
		pass.sendKeys(password);
		cpass.sendKeys(password);

		btnSave.click();
		return isToastDisplayed("Successfully Saved") || searchUser(username);
	}

	public boolean searchUser(String username) {
		WebElement searchUsername = driver.findElement(By.xpath("//label[normalize-space()='Username']/../following-sibling::div//input"));
		searchUsername.clear();
		searchUsername.sendKeys(username);
		btnSearch.click();
		waitForTableOrEmpty();
		return isTextInTable(username);
	}

	public boolean addJobTitle(Map<String, String> data) {
		navigateToJobSubMenu("Job Titles");
		btnAdd.click();
		txtNameInput.sendKeys(data.getOrDefault("jobTitle", ""));
		WebElement description = driver.findElement(By.xpath("//textarea[@placeholder='Type description here']"));
		description.sendKeys(data.getOrDefault("jobDescription", ""));
		btnSave.click();
		return isToastDisplayed("Successfully Saved") || verifyJobTitle(data.get("jobTitle"));
	}

	public boolean verifyJobTitle(String jobTitle) {
		navigateToJobSubMenu("Job Titles");
		return isTextInTable(jobTitle);
	}

	public boolean addPayGradeWithSalary(Map<String, String> data) {
		navigateToJobSubMenu("Pay Grades");
		btnAdd.click();
		txtNameInput.sendKeys(data.getOrDefault("payGrade", ""));
		btnSave.click();
		isToastDisplayed("Successfully Saved");

		By rowByName = By.xpath("//div[@role='rowgroup']//div[@role='row'][.//div[normalize-space()='" + data.get("payGrade") + "']]");
		wait.until(ExpectedConditions.elementToBeClickable(rowByName)).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add Currency']"))).click();
		selectDropdownByLabel("Currency", data.getOrDefault("currency", ""));
		driver.findElement(By.xpath("//label[text()='Minimum Salary']/../following-sibling::div//input"))
				.sendKeys(data.getOrDefault("minSalary", ""));
		driver.findElement(By.xpath("//label[text()='Maximum Salary']/../following-sibling::div//input"))
				.sendKeys(data.getOrDefault("maxSalary", ""));
		btnSave.click();
		return isToastDisplayed("Successfully Saved");
	}

	public boolean verifyPayGrade(String payGradeName) {
		navigateToJobSubMenu("Pay Grades");
		return isTextInTable(payGradeName);
	}

	public boolean addEmploymentStatus(Map<String, String> data) {
		navigateToJobSubMenu("Employment Status");
		btnAdd.click();
		txtNameInput.sendKeys(data.getOrDefault("employmentStatus", ""));
		btnSave.click();
		return isToastDisplayed("Successfully Saved") || verifyEmploymentStatus(data.get("employmentStatus"));
	}

	public boolean verifyEmploymentStatus(String statusName) {
		navigateToJobSubMenu("Employment Status");
		return isTextInTable(statusName);
	}

	public boolean addOrganizationUnit(Map<String, String> data) {
		if (!navigateToOrganizationStructure()) return false;
		driver.findElement(By.xpath("//button[normalize-space()='Edit']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Type name']"))
				.sendKeys(data.getOrDefault("orgUnit", ""));
		selectDropdownByLabel("Parent Unit", data.getOrDefault("subUnit", ""));
		btnSave.click();
		return isToastDisplayed("Successfully Saved");
	}

	// ---------- UTILITIES ----------
	private void selectDropdownByLabel(String label, String optionText) {
		if (optionText.isBlank()) return;
		By dropdown = By.xpath("//label[normalize-space()='" + label + "']/../following-sibling::div//div[contains(@class,'oxd-select-text')]");
		wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
		By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
		wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	}

	private void waitForTableOrEmpty() {
		wait.until(ExpectedConditions.or(
				ExpectedConditions.presenceOfElementLocated(listTableBody),
				ExpectedConditions.presenceOfElementLocated(listNoRecords)
		));
	}

	private boolean isTextInTable(String text) {
		By cell = By.xpath("//div[@role='cell']//div[normalize-space()='" + text + "']");
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(cell));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	private boolean isToastDisplayed(String msg) {
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(toast, msg));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
}




