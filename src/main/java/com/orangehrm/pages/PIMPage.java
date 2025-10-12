package com.orangehrm.pages;

import com.orangehrm.managers.DriverManager;
import org.apache.logging.log4j.Logger;

import com.orangehrm.managers.LoggerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v139.css.CSS;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;
import java.util.stream.Collectors;

public class PIMPage extends BasePage{
	private static final Logger logger = LoggerManager.getLogger(PIMPage.class);

	public PIMPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnSearch;
	@FindBy(xpath = "//button[@type='reset']")
	private WebElement btnReset;
	@FindBy(xpath = "//button[normalize-space()='Add']")
	private WebElement btnAdd;
	@FindBy(xpath = "//label[normalize-space()='Employee Name']/../following-sibling::div//input")
	private WebElement txtSearchEmpName;
	@FindBy(xpath = "//label[normalize-space()='Employee Id']/../following-sibling::div//input")
	private WebElement txtSearchEmpId;

	@FindBy(xpath = "//label[normalize-space()='Other Id']/../following-sibling::div//input")
	private WebElement txtEmpOtherId;
	@FindBy(xpath = "//label[contains(normalize-space(),'License Number')]/../following-sibling::div//input")
	private WebElement txtEmpLicenseNumber;
	@FindBy(xpath = "//label[contains(normalize-space(),'License Expiry Date')]/../following-sibling::div//input")
	private WebElement txtEmpLicenseExpiryDate;
	@FindBy(xpath = "//label[normalize-space()='Nationality']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropDownEmpNationality;
	@FindBy(xpath = "//label[normalize-space()='Marital Status']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropDownEmpMaritalStatus;
	@FindBy(xpath = "//label[normalize-space()='Date of Birth']/../following-sibling::div//input[@placeholder='yyyy-dd-mm']")
	private WebElement txtEmpBirthDate;
	@FindBy(xpath = "//label[normalize-space()='Blood Type']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropDownEmpBloodType;
	@FindBy(xpath = "//label[normalize-space()='Test_Field']/../following-sibling::div//input")
	private WebElement txtEmpTestField;
	@FindBy(xpath = "//label[normalize-space()='Male']/span")
	private WebElement radioBtnMale;
	@FindBy(xpath = "//label[normalize-space()='Female']/span")
	private WebElement radioBtnFemale;

	@FindBy(xpath = "//label[normalize-space()='Job Category']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropDownJobCategory;
	@FindBy(xpath = "//label[normalize-space()='Employment Status']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropDownEmploymentStatus;
	@FindBy(xpath = "//p[normalize-space()='Include Employment Contract Details']//following-sibling::div//span")
	private WebElement toggleBtnEmpContractDetails;
	@FindBy(xpath = "//label[normalize-space()='Contract Start Date']/../following-sibling::div//input[@placeholder='yyyy-dd-mm']")
	private WebElement txtEmpContractStartDate;
	@FindBy(xpath = "//label[normalize-space()='Contract End Date']/../following-sibling::div//input[@placeholder='yyyy-dd-mm']")
	private WebElement txtEmpContractEndDate;
	@FindBy(xpath = "//button[normalize-space()='Terminate Employment']")
	private WebElement btnTerminateEmployment;
	@FindBy(xpath = "//div[normalize-space()='Browse']")
	private WebElement btnBrowseFileToUpload;


	@FindBy(xpath = "//label[normalize-space()='Employment Status']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropdownSearchEmpStatus;
	@FindBy(xpath = "//label[normalize-space()='Include']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropdownSearchEmpInclude;
	@FindBy(xpath = "//label[normalize-space()='Job Title']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropdownSearchEmpJobTitle;
	@FindBy(xpath = "//label[normalize-space()='Sub Unit']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropdownSearchEmpSubUnit;
	@FindBy (xpath = "//button[normalize-space()='Delete Selected']")
	private WebElement btnDeleteSelected;

	@FindBy(xpath = "//input[@name='firstName']")
	private WebElement txtEmpFirstName;
	@FindBy(xpath = "//input[@name='middleName']")
	private WebElement txtEmpMiddleName;
	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement txtEmpLastName;
	@FindBy(xpath = "//span[contains(@class,'oxd-switch-input')]")
	private WebElement toggleBtnAddEmpCreateLogin;
	@FindBy(xpath = "//label[text()='Username']/../following-sibling::div//input")
	private WebElement txtAddEmpUserName;
	@FindBy(xpath = "//label[text()='Password']/../following-sibling::div//input")
	private WebElement txtAddEmpPassword;
	@FindBy(xpath = "//label[text()='Confirm Password']/../following-sibling::div//input")
	private WebElement txtAddEmpConfirmPassword;
	@FindBy(xpath = "//button[normalize-space()='Save']")
	private WebElement btnSave;
	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	private WebElement btnCancel;
	@FindBy(xpath = "//div[normalize-space()='Job Specification']//following-sibling::div//div")
	private WebElement txtJobSpecification;

	@FindBy(xpath = "//a[normalize-space()='Employee List']")
	private WebElement tabEmployeeList;
	@FindBy(xpath = "//a[normalize-space()='Add Employee']")
	private WebElement tabAddEmployee;


	@FindBy(xpath = "//a[normalize-space()='Job']")
	private WebElement tabJob;

	@FindBy(xpath = "//h6[normalize-space()='Personal Details']")
	private WebElement labelPersonalDetails;
	@FindBy(xpath = "//h6[normalize-space()='Job Details']")
	private WebElement labelJobDetails;

	@FindBy(css = ".oxd-table-header-cell.oxd-padding-cell.oxd-table-th")
	private List<WebElement> tableHeaders;
	@FindBy(css = "div.oxd-table-card div.oxd-table-row")
	private List<WebElement> tableRows;
	@FindBy(xpath = "i[@class='oxd-icon bi-chevron-right']")
	private WebElement btnNextPageTableEntry;
	@FindBy(xpath = "i[@class='oxd-icon bi-chevron-left']")
	private WebElement btnPrevPageTableEntry;

	public void navigateToJobs(){
		tabJob.click();
	}
	public void createEmployee(String firstname, String lastname, String username, String password) {
		navigateToPimPage();
		btnAdd.click();
		wait.until(ExpectedConditions.visibilityOf(txtEmpFirstName));
		txtEmpFirstName.sendKeys(firstname);
		txtEmpLastName.sendKeys(lastname);
		toggleBtnAddEmpCreateLogin.click();
		wait.until(ExpectedConditions.visibilityOf(txtAddEmpUserName));
		txtAddEmpUserName.sendKeys(username);
		txtAddEmpPassword.sendKeys(password);
		txtAddEmpConfirmPassword.sendKeys(password);
		btnSave.click();
	}
	public boolean createEmployee(Map<String, String> employeeData) {
		navigateToPimPage();
		btnAdd.click();
		wait.until(ExpectedConditions.visibilityOf(txtEmpFirstName));
		txtEmpFirstName.sendKeys(employeeData.getOrDefault("firstname", ""));
		txtEmpLastName.sendKeys(employeeData.getOrDefault("lastname", ""));
		txtEmpMiddleName.sendKeys(employeeData.getOrDefault("middleName", ""));
		txtSearchEmpId.sendKeys(employeeData.getOrDefault("searchEmpId", "Dummy"));

		if (employeeData.containsKey("username")) {
			toggleBtnAddEmpCreateLogin.click();
			wait.until(ExpectedConditions.visibilityOf(txtAddEmpUserName));

			String username = employeeData.get("adminUsername");
			String password = employeeData.getOrDefault("password", "P@ssword123"); // Use a default if password is not provided

			txtAddEmpUserName.sendKeys(username);
			txtAddEmpPassword.sendKeys(password);
			txtAddEmpConfirmPassword.sendKeys(password);
		}
		btnSave.click();
		return  (labelPersonalDetails.isDisplayed())?true:false;
	}



	private boolean hasNextPage() {
		try {
			return btnNextPageTableEntry.isDisplayed()  && btnNextPageTableEntry.isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public List<Map<String, WebElement>> getEmployeeTableData() {
		List<Map<String, WebElement>> tableData = new ArrayList<>();

		// Extract column headers
		List<String> headers = tableHeaders.stream()
				.map(WebElement::getText)
				.map(String::trim)
				.filter(h -> !h.isEmpty())
				.collect(Collectors.toList());
		boolean nextPageExist;
		// Iterate over each table row
		do {
			for (WebElement row : tableRows) {
				List<WebElement> cells = row.findElements(By.cssSelector("div.oxd-table-cell"));
				Map<String, WebElement> rowData = new LinkedHashMap<>();

				for (int i = 0; i < Math.min(cells.size(), headers.size()); i++) {
					rowData.put(headers.get(i), cells.get(i));
				}

				tableData.add(rowData);
			}
			nextPageExist = hasNextPage();
			if (nextPageExist) {
				wait.until(ExpectedConditions.elementToBeClickable(btnNextPageTableEntry)).click();
				wait.until(ExpectedConditions.visibilityOfAllElements(tableRows));
			}
		}
		while(nextPageExist);

		return tableData;
	}
	public void searchEmployeeByName(String empName) {
		navigateToPimPage();
		wait.until(ExpectedConditions.visibilityOf(txtSearchEmpName)).clear();
		txtSearchEmpName.sendKeys(empName);
		btnSearch.click();
//		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		// Wait for new rows to load after search
		By rowsLocator = By.cssSelector("div.oxd-table-card div.oxd-table-row");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rowsLocator));

		logger.info("[searchEmployeeByName]: Searched for employee name: " + empName);
	}

	public boolean isEmployeePresentInTable(String employeeName) {
		List<Map<String, WebElement>> rows = getEmployeeTableData();

		return rows.stream().anyMatch(row ->
				row.values().stream()
						.anyMatch(cell -> cell.getText().trim().equalsIgnoreCase(employeeName))
		);
	}


	public void searchEmployeeById(String empId) {
		navigateToPimPage();
		wait.until(ExpectedConditions.visibilityOf(txtSearchEmpId)).clear();
		txtSearchEmpId.sendKeys(empId);
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		wait.until(ExpectedConditions.visibilityOfAllElements(tableRows));
		logger.info("[PIMPage][searchEmployeeById] : "+"Searched for employee ID: " + empId);

	}

	public boolean clickEditButtonForEmployee(String employeeName) {
		List<Map<String, WebElement>> tableData = getEmployeeTableData();

		for (Map<String, WebElement> row : tableData) {
			WebElement nameCell = row.get("First (& Middle) Name"); // adjust header text if needed
			if (nameCell != null && nameCell.getText().trim().equalsIgnoreCase(employeeName)) {
				WebElement actionsCell = row.get("Actions");
				if (actionsCell != null) {
					WebElement editButton = actionsCell.findElement(By.cssSelector("button:has(i.bi-pencil-fill)"));
					editButton.click();
					logger.info("[PIMPage][clickEditButtonForEmployee] : "+"Clicked edit for employee: " + employeeName);
					return true;
				}
			}
		}
		logger.info("[PIMPage][clickEditButtonForEmployee] : "+"Employee not found: " + employeeName);
		return false;
	}

	public boolean clickRowByHeaderValue(String headerName, String matchValue) {
		List<Map<String, WebElement>> rows = getEmployeeTableData();

		for (Map<String, WebElement> row : rows) {
			WebElement targetCell = row.get(headerName);
			if (targetCell != null && targetCell.getText().trim().equalsIgnoreCase(matchValue)) {
				wait.until(ExpectedConditions.elementToBeClickable(targetCell)).click();
				logger.info("[PIMPage][clickRowByHeaderValue] : "+"Clicked row where " + headerName + " = " + matchValue);
				return true;
			}
		}
		logger.info("[PIMPage][clickRowByHeaderValue] : "+"No row found where " + headerName + " = " + matchValue);
		return false;
	}
	public boolean clickDeleteIconForEmployee(String employeeName) {
		List<Map<String, WebElement>> rows = getEmployeeTableData();

		for (Map<String, WebElement> row : rows) {
			WebElement nameCell = row.get("First (& Middle) Name");
			if (nameCell != null && nameCell.getText().trim().equalsIgnoreCase(employeeName)) {
				WebElement actionsCell = row.get("Actions");
				WebElement deleteBtn = actionsCell.findElement(By.cssSelector("button:has(i.bi-trash)"));
				wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
				logger.info("[PIMPage][clickDeleteIconForEmployee] : "+"Clicked Delete icon for: " + employeeName);
				return true;
			}
		}
		logger.info("[PIMPage][clickDeleteIconForEmployee] : "+"Delete icon not found for: " + employeeName);
		return false;
	}
	public void clickDeleteSelectedButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDeleteSelected)).click();
		logger.info("[PIMPage][clickDeleteSelectedButton] : "+"Clicked 'Delete Selected' button ");
	}


	public boolean openEmployeeDetailsByName(String employeeName) {
		try {
			searchEmployeeByName(employeeName);
			clickEditButtonForEmployee(employeeName);
			wait.until(ExpectedConditions.visibilityOf(labelPersonalDetails));
			if (labelPersonalDetails.isDisplayed()) {
				logger.info("[PIMPage][openEmployeeDetailsByName] : "+"Navigated to Personal Details page for: " + employeeName);
				return true;
			}
			logger.info("[PIMPage][openEmployeeDetailsByName] : "+"Employee not found after search: " + employeeName);
			return false;

		} catch (Exception e) {
			logger.error("[PIMPage][openEmployeeDetailsByName] : "+"Error in openEmployeeDetailsByName: " + e.getMessage());
			return false;
		}
	}


	public boolean openEmployeeDetailsById(String empId) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtSearchEmpId)).clear();
			txtSearchEmpId.sendKeys(empId);
			String newEmptId  = txtSearchEmpId.getText();
			logger.info(txtSearchEmpId.getText());
			wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();

			wait.until(ExpectedConditions.visibilityOfAllElements(tableRows));

			for (Map<String, WebElement> row : getEmployeeTableData()) {
				WebElement idCell = row.get("Id");
				logger.info(idCell.getText());
				if (idCell != null && idCell.getText().trim().equalsIgnoreCase(newEmptId)) {
					wait.until(ExpectedConditions.elementToBeClickable(idCell)).click();
					logger.info("[PIMPage][openEmployeeDetailsById] : "+"Clicked row for employee ID: " + newEmptId);

//					WebElement detailsHeader = new WebDriverWait(driver, Duration.ofSeconds(8))
					wait.until(ExpectedConditions.visibilityOf(labelPersonalDetails));
					if (labelPersonalDetails.isDisplayed()) {
						logger.info("[PIMPage][openEmployeeDetailsById] : "+ "Navigated to Personal Details page for ID: " + newEmptId);
						return true;
					}
				}
			}

			logger.info("[PIMPage][openEmployeeDetailsById] : "+"Employee ID not found after search: " + empId);
			return false;

		} catch (Exception e) {
			logger.error("[PIMPage][openEmployeeDetailsById] : "+"Error in openEmployeeDetailsById: " + e.getMessage());
			return false;
		}
	}

	public boolean verifyEmployeeJobDetails(String employeeName) {
		try {
			logger.info("[PIMPage][verifyEmployeeJobDetails] : Verifying Job Details for employee: " + employeeName);

			boolean baseDetail =
					dropdownSearchEmpJobTitle.isDisplayed() &&
							dropDownEmploymentStatus.isDisplayed() &&
							dropDownJobCategory.isDisplayed() &&
							dropdownSearchEmpSubUnit.isDisplayed()&&
							toggleBtnEmpContractDetails.isDisplayed();;
			toggleBtnEmpContractDetails.click();
			boolean contractDetails = txtEmpContractStartDate.isDisplayed() &&
					txtEmpContractEndDate.isDisplayed() &&
					btnTerminateEmployment.isDisplayed();
			boolean allDisplayed = baseDetail && contractDetails;
			if (allDisplayed) {
				logger.info("[PIMPage][verifyEmployeeJobDetails] : All job detail fields displayed successfully for " + employeeName);
				return true;
			} else {
				logger.warn("[PIMPage][verifyEmployeeJobDetails] : One or more job detail fields are not visible for " + employeeName);
				return false;
			}

		} catch (Exception e) {
			logger.error("[PIMPage][verifyEmployeeJobDetails] : Exception during verification for "
					+ employeeName + " | " + e.getMessage());
			return false;
		}
	}

	public boolean verifyEmployeePersonalDetails(String employeeName) {
		try {
			logger.info("[PIMPage][verifyEmployeePersonalDetails] : Verifying Personal Details for employee: " + employeeName);

			wait.until(ExpectedConditions.visibilityOf(labelPersonalDetails));

			boolean allDisplayed =
					txtEmpOtherId.isDisplayed() &&
							txtEmpLicenseNumber.isDisplayed() &&
							txtEmpLicenseExpiryDate.isDisplayed() &&
							dropDownEmpNationality.isDisplayed() &&
							dropDownEmpMaritalStatus.isDisplayed() &&
							txtEmpBirthDate.isDisplayed() &&
							(radioBtnMale.isDisplayed() || radioBtnFemale.isDisplayed()) &&
							dropDownEmpBloodType.isDisplayed();

			if (allDisplayed) {
				logger.info("[PIMPage][verifyEmployeePersonalDetails] : All personal details fields displayed successfully for " + employeeName);
				return true;
			} else {
				logger.warn("[PIMPage][verifyEmployeePersonalDetails] : One or more personal details fields not visible.");
				return false;
			}

		} catch (Exception e) {
			logger.error("[PIMPage][verifyEmployeePersonalDetails] : Exception during verification: " + e.getMessage());
			return false;
		}
	}



}
