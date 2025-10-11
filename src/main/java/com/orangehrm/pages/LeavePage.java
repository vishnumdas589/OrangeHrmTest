
package com.orangehrm.pages;

import com.orangehrm.managers.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;
		import java.util.stream.Collectors;

public class LeavePage extends BasePage {
	private static final Logger logger = LoggerManager.getLogger(LeavePage.class);
	public LeavePage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[text()='Leave']")
	private WebElement menuLeave;

	@FindBy(xpath = "//a[text()='Apply']")
	private WebElement tabApplyLeave;

	@FindBy(xpath = "//a[text()='My Leave']")
	private WebElement tabMyLeave;

	@FindBy(xpath = "//span[normalize-space()='Entitlements']")
	private WebElement menuEntitlements;

	@FindBy(xpath = "//a[text()='Add Entitlements']")
	private WebElement subTabAddEntitlements;

	@FindBy(xpath = "//a[text()='My Entitlements']")
	private WebElement subTabMyEntitlements;

	@FindBy(xpath = "//span[normalize-space()='Reports']/ancestor::li")
	private WebElement menuReports;

	@FindBy(xpath = "//a[text()='My Leave Entitlements and Usage Report']")
	private WebElement subTabMyLeaveReport;

	@FindBy(xpath = "//a[text()='Leave Entitlements and Usage Report']")
	private WebElement subTabLeaveReport;

	@FindBy(xpath = "//a[text()='Assign Leave']")
	private WebElement tabAssignLeave;

	// APPLY LEAVE
	@FindBy(xpath = "//label[normalize-space()='Leave Type']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropdownLeaveType;

	@FindBy (xpath = "//label[normalize-space()='From Date']/../following-sibling::div//input[@placeholder='yyyy-dd-mm']")
	private WebElement txtFromDate;

	@FindBy(xpath = "//label[normalize-space()='To Date']/../following-sibling::div//input[@placeholder='yyyy-dd-mm']")
	private WebElement txtToDate;


	@FindBy(xpath = "//label[normalize-space()='Comments']/../following-sibling::div//textarea")
	private WebElement txtComments;

	@FindBy(xpath = "//button[normalize-space()='Apply']")
	private WebElement btnApply;

	@FindBy(xpath = "//div[contains(@class,'orangehrm-leave-balance')]/following-sibling::div/p")
	private WebElement labelLeaveBalance;

	// ADD LEAVE ENTITLEMENT
	@FindBy(xpath = "//label[normalize-space()='Individual Employee']/span")
	private WebElement radioIndividualEmployee;

	@FindBy(xpath = "//label[normalize-space()='Multiple Employees']/span")
	private WebElement radioMultipleEmployees;
	@FindBy(xpath = "//label[normalize-space()='Employee Name']/../following-sibling::div//input")
	private WebElement txtEmployeeName;

	@FindBy(xpath = "//label[normalize-space()='Location']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropdownLocation;

	@FindBy(xpath = "//label[normalize-space()='Sub Unit']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropdownSubUnit;

	@FindBy(xpath = "//label[normalize-space()='Leave Type']/../following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement dropdownEntitlementLeaveType;

	@FindBy(xpath = "//label[normalize-space()='Entitlement']/../following-sibling::div//input[@class='oxd-input oxd-input--active']")
	private WebElement txtEntitlementValue;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	private WebElement btnSaveEntitlement;

	//Table
	@FindBy(css = "div.oxd-table-card div.oxd-table-row")
	private List<WebElement> tableRows;

	@FindBy(css = ".oxd-table-header-cell")
	private List<WebElement> tableHeaders;



	//REPORTS
	@FindBy(xpath = "//button[normalize-space()='Generate']")
	private WebElement btnGenerateReport;

	private final By spinner = By.xpath("//div[contains(@class,'oxd-loading-spinner')]");
	private final By toast = By.cssSelector("div.oxd-toast");


	public void selectDropdownOption(WebElement dropdown, String optionText) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
			String optionXpath = String.format("//div[@role='listbox']//span[normalize-space()='%s']", optionText);
			WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
			option.click();
			logger.info("[selectDropdownOption] Selected option: " + optionText);
		} catch (Exception e) {
			logger.error("[selectDropdownOption] Failed to select: " + optionText + " | " + e.getMessage());
		}
	}


	public void navigateToApplyLeave() {
		wait.until(ExpectedConditions.elementToBeClickable(menuLeave)).click();
		wait.until(ExpectedConditions.elementToBeClickable(tabApplyLeave)).click();
		logger.info("[navigateToApplyLeave]: Navigated to Apply Leave");
	}

	public void navigateToMyLeave() {
		wait.until(ExpectedConditions.elementToBeClickable(menuLeave)).click();
		wait.until(ExpectedConditions.elementToBeClickable(tabMyLeave)).click();
		logger.info("[navigateToMyLeave]: Navigated to My Leave tab");
	}

	public void navigateToAddEntitlement() {
		wait.until(ExpectedConditions.elementToBeClickable(menuLeave)).click();
		wait.until(ExpectedConditions.elementToBeClickable(menuEntitlements)).click();
		wait.until(ExpectedConditions.elementToBeClickable(subTabAddEntitlements)).click();
		logger.info("[navigateToAddEntitlement]: Navigated to Add Leave Entitlement");
	}

	public void navigateToMyEntitlements() {
		wait.until(ExpectedConditions.elementToBeClickable(menuLeave)).click();
		wait.until(ExpectedConditions.elementToBeClickable(menuEntitlements)).click();
		wait.until(ExpectedConditions.elementToBeClickable(subTabMyEntitlements)).click();
		logger.info("[navigateToMyEntitlements]: Navigated to My Leave Entitlements");
	}

	public void navigateToReports() {
		wait.until(ExpectedConditions.elementToBeClickable(menuLeave)).click();
		wait.until(ExpectedConditions.elementToBeClickable(menuReports)).click();
		wait.until(ExpectedConditions.elementToBeClickable(subTabLeaveReport)).click();
		logger.info("[navigateToReports]: Navigated to Leave Entitlements and Usage Report");
	}


	public void applyLeave(String leaveType, String fromDate, String toDate, String comments) {
		try {
			navigateToApplyLeave();
			selectDropdownOption(dropdownLeaveType, leaveType);
			wait.until(ExpectedConditions.visibilityOf(labelLeaveBalance));

			txtFromDate.sendKeys(fromDate);
			txtToDate.sendKeys(toDate);
			txtComments.sendKeys(comments);
			btnApply.click();

			logger.info("[applyLeave]: Leave applied for type: " + leaveType);
		} catch (Exception e) {
			logger.error("[applyLeave]: Failed to apply leave | " + e.getMessage());
		}

	}

	public boolean applyLeave(Map<String, String> leaveData) {
		navigateToApplyLeave();
		selectDropdownOption(dropdownLeaveType, leaveData.getOrDefault("leaveType", "-- Select --"));
		txtFromDate.sendKeys(leaveData.getOrDefault("fromDate", ""));
		txtToDate.sendKeys(leaveData.getOrDefault("toDate", ""));
		txtComments.sendKeys(leaveData.getOrDefault("comments", ""));
		btnApply.click();
		logger.info("[applyLeave-HashMap]: Leave applied for " + leaveData);
		return isToastDisplayed("Successfully Saved");
	}
	public void addLeaveEntitlementIndividual(String employeeName, String leaveType, String entitlement) {
		try {
			navigateToAddEntitlement();
			selectIndividualEmployee();

			wait.until(ExpectedConditions.visibilityOf(txtEmployeeName));
			txtEmployeeName.sendKeys(employeeName);
			Thread.sleep(1000); // allow suggestions to appear
			txtEmployeeName.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

			selectDropdownOption(dropdownEntitlementLeaveType, leaveType);
			txtEntitlementValue.sendKeys(entitlement);
			btnSaveEntitlement.click();

			logger.info("[addLeaveEntitlementIndividual]: Added entitlement for " + employeeName);
		} catch (Exception e) {
			logger.error("[addLeaveEntitlementIndividual]: Failed | " + e.getMessage());
		}
	}

	public boolean addLeaveEntitlementIndividual(Map<String, String> entitlementData) {
		try{
			navigateToAddEntitlement();
			selectIndividualEmployee();

			wait.until(ExpectedConditions.visibilityOf(txtEmployeeName));
			txtEmployeeName.sendKeys(entitlementData.getOrDefault("employeeName", ""));
			Thread.sleep(1000);
			txtEmployeeName.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

			selectDropdownOption(dropdownEntitlementLeaveType, entitlementData.getOrDefault("leaveType", "-- Select --"));
			txtEntitlementValue.sendKeys(entitlementData.getOrDefault("entitlement", "10"));
			btnSaveEntitlement.click();

			logger.info("[addLeaveEntitlementIndividual-HashMap]: Entitlement added for " + entitlementData);
		} catch (Exception e) {
			logger.error("[addLeaveEntitlementIndividual-HashMap]: Failed | " + e.getMessage());
		}
		return isToastDisplayed("Successfully Saved");

	}


	public void addLeaveEntitlementMultiple(String location, String subUnit, String leaveType, String entitlement) {
		try {
			navigateToAddEntitlement();
			selectMultipleEmployees();

			selectDropdownOption(dropdownLocation, location);
			selectDropdownOption(dropdownSubUnit, subUnit);
			selectDropdownOption(dropdownEntitlementLeaveType, leaveType);
			txtEntitlementValue.sendKeys(entitlement);
			btnSaveEntitlement.click();

			logger.info("[addLeaveEntitlementMultiple]: Added entitlement for type: " + leaveType);
		} catch (Exception e) {
			logger.error("[addLeaveEntitlementMultiple]: Failed to add entitlement | " + e.getMessage());
		}
	}

	public boolean addLeaveEntitlementMultiple(Map<String, String> entitlementData) {
		navigateToAddEntitlement();
		selectMultipleEmployees();

		selectDropdownOption(dropdownLocation, entitlementData.getOrDefault("location", "-- Select --"));
		selectDropdownOption(dropdownSubUnit, entitlementData.getOrDefault("subUnit", "-- Select --"));
		selectDropdownOption(dropdownEntitlementLeaveType, entitlementData.getOrDefault("leaveType", "-- Select --"));
		txtEntitlementValue.sendKeys(entitlementData.getOrDefault("entitlement", "10"));
		btnSaveEntitlement.click();

		logger.info("[addLeaveEntitlementMultiple-HashMap]: Entitlement added for " + entitlementData);
		return isToastDisplayed("Successfully Saved");
	}

	public boolean generateLeaveReport() {
		try {
			navigateToReports();
			waitUntilClickable(btnGenerateReport);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(toast));
			btnGenerateReport.click();
			wait.until(ExpectedConditions.visibilityOf(tableHeaders.get(1)));
			List<String> headers = tableHeaders.stream().map(WebElement::getText).toList();
			for (String header : headers) {
				System.out.println(header);
			}
			boolean allPresent = headers.containsAll(
					Arrays.asList("Employee", "Leave Entitlements (Days)", "Leave Pending Approval (Days)", "Leave Taken (Days)", "Leave Balance (Days)")
			);

			logger.info("[generateLeaveReport]: Headers verified -> " + headers);
			return allPresent;
		} catch (Exception e) {
			logger.error("[generateLeaveReport]: Report generation failed | " + e.getMessage());
			return false;
		}
	}


	public List<Map<String, WebElement>> getLeaveTableData() {
		List<String> headers = tableHeaders.stream()
				.map(WebElement::getText)
				.filter(h -> !h.isEmpty())
				.collect(Collectors.toList());

		List<Map<String, WebElement>> data = new ArrayList<>();

		for (WebElement row : tableRows) {
			List<WebElement> cells = row.findElements(By.cssSelector("div.oxd-table-cell"));
			Map<String, WebElement> map = new LinkedHashMap<>();

			for (int i = 0; i < Math.min(cells.size(), headers.size()); i++) {
				map.put(headers.get(i), cells.get(i));
			}
			data.add(map);
		}

		logger.info("[getLeaveTableData]: Extracted " + data.size() + " rows");
		return data;
	}
	public void selectIndividualEmployee() {
		wait.until(ExpectedConditions.elementToBeClickable(radioIndividualEmployee)).click();
		logger.info("[LeavePage][selectIndividualEmployee]: Selected Individual Employee option");
	}

	public void selectMultipleEmployees() {
		wait.until(ExpectedConditions.elementToBeClickable(radioMultipleEmployees)).click();
		logger.info("[LeavePage][selectMultipleEmployees]: Selected Multiple Employees option");
	}

}
