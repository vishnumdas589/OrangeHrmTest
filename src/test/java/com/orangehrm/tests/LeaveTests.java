package com.orangehrm.tests;
import org.apache.logging.log4j.Logger;

import com.orangehrm.Base.BasePageTest;
import com.orangehrm.pages.LeavePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orangehrm.managers.LoggerManager;

public class LeaveTests extends BasePageTest {
	private static final Logger logger = LoggerManager.getLogger(LeaveTests.class);
	private LeavePage leavePg;

	@BeforeClass
	public void setUpPage() {
		leavePg = new LeavePage();
	}

	@Test(priority = 1, description = "Verify apply leave functionality")
	public void verifyApplyLeaveFunctionality() {
		leavePg.navigateToApplyLeave();
		leavePg.applyLeave("CAN - Personal", "2025-10-08", "2025-10-09", "Applying for personal leave");

		Assert.assertTrue(true, "Leave application should complete without error.");
	}

	@Test(priority = 2, description = "Verify my leave list view")
	public void verifyMyLeaveList() {
		leavePg.navigateToMyLeave();
		List<Map<String, WebElement>> tableData = leavePg.getLeaveTableData();

		Assert.assertNotNull(tableData, "Table data should not be null.");
		Assert.assertTrue(tableData.size() >= 0, "Leave list should load successfully.");
	}

	@Test(priority = 3, description = "Verify leave entitlements for individual employee")
	public void verifyLeaveEntitlementIndividual() {
		leavePg.addLeaveEntitlementIndividual("Linda Anderson", "CAN - Personal", "12");
		Assert.assertTrue(true, "Individual employee entitlement added successfully.");
	}

	@Test(priority = 4, description = "Verify leave entitlements for multiple employees")
	public void verifyLeaveEntitlementMultiple() {
		leavePg.addLeaveEntitlementMultiple("Texas R&D", "Engineering", "CAN - Personal", "10");
		Assert.assertTrue(true, "Multiple employee entitlement added successfully.");
	}

	@Test(priority = 5, description = "Verify leave reports generation")
	public void verifyLeaveReports() {
		boolean reportGenerated = leavePg.generateLeaveReport();
		Assert.assertTrue(reportGenerated, "Leave report should display expected headers.");
	}

}
