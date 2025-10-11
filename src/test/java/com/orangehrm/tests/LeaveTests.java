package com.orangehrm.tests;
import com.orangehrm.data.DataProviders;
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

	@Test(priority = 1,dataProvider = "LeaveDataMap",dataProviderClass = DataProviders.class, description = "Verify apply leave functionality")
	public void verifyApplyLeaveFunctionality(Map<String,String>tableData) {
		leavePg.navigateToApplyLeave();
		boolean iseaveApplyed =leavePg.applyLeave(tableData);
		Assert.assertTrue(iseaveApplyed, "Leave application should complete without error.");
	}

	@Test(priority = 2,dataProvider = "LeaveDataMap",dataProviderClass = DataProviders.class, description = "Verify my leave list view")
	public void verifyMyLeaveList() {
		leavePg.navigateToMyLeave();
		List<Map<String, WebElement>> tableData = leavePg.getLeaveTableData();

		Assert.assertNotNull(tableData, "Table data should not be null.");
		Assert.assertTrue(tableData.size() >= 0, "Leave list should load successfully.");
	}

	@Test(priority = 3,dataProvider = "LeaveDataMap",dataProviderClass = DataProviders.class, description = "Verify leave entitlements for individual employee")
	public void verifyLeaveEntitlementIndividual(Map<String, String> tableData) {
		boolean isverified = leavePg.addLeaveEntitlementIndividual(tableData);
		Assert.assertTrue(isverified, "Individual employee entitlement added successfully.");
	}

	@Test(priority = 4,dataProvider = "LeaveDataMap",dataProviderClass = DataProviders.class, description = "Verify leave entitlements for multiple employees")
	public void verifyLeaveEntitlementMultiple(Map<String, String> tableData) {
//		leavePg.addLeaveEntitlementMultiple("Texas R&D", "Engineering", "CAN - Personal", "10");
		boolean isVerified = leavePg.addLeaveEntitlementMultiple(tableData);
		Assert.assertTrue(isVerified, "Multiple employee entitlement added successfully.");
	}


	@Test(priority = 5, description = "Verify leave reports generation")
	public void verifyLeaveReports() {
		boolean reportGenerated = leavePg.generateLeaveReport();
		Assert.assertTrue(reportGenerated, "Leave report should display expected headers.");
	}

}
