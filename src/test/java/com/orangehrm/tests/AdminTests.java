package com.orangehrm.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.orangehrm.Base.BasePageTest;
import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.utils.ConfigReader;


import com.orangehrm.Base.BasePageTest;
import com.orangehrm.data.DataProviders;
import com.orangehrm.pages.AdminPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Map;



public class AdminTests extends BasePageTest{
	private static final Logger logger = LoggerManager.getLogger(AdminTests.class);
	private AdminPage adminPg;

	@BeforeClass
	public void setupAdminPage() {
		adminPg = new AdminPage();
		dashboardPg.navigateToAdminPage();
	}

	@Test(priority = 1, dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyAddUser(Map<String, String> data) {
		Assert.assertTrue(adminPg.addUser(data), "Failed to add new user");
		Assert.assertTrue(adminPg.searchUser(data.get("username")), "User not found after creation");
	}

	@Test(priority = 2, dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyJobTitle(Map<String, String> data) {
		Assert.assertTrue(adminPg.addJobTitle(data), "Failed to add Job Title");
		Assert.assertTrue(adminPg.verifyJobTitle(data.get("jobTitle")), "Job Title not listed");
	}

	@Test(priority = 3, dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyPayGrade(Map<String, String> data) {
		Assert.assertTrue(adminPg.addPayGradeWithSalary(data), "Failed to add Pay Grade");
		Assert.assertTrue(adminPg.verifyPayGrade(data.get("payGrade")), "Pay Grade not listed");
	}

	@Test(priority = 4, dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyEmploymentStatus(Map<String, String> data) {
		Assert.assertTrue(adminPg.addEmploymentStatus(data), "Failed to add Employment Status");
		Assert.assertTrue(adminPg.verifyEmploymentStatus(data.get("employmentStatus")), "Employment Status not listed");
	}

	@Test(priority = 5, dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyOrganizationStructure(Map<String, String> data) {
		Assert.assertTrue(adminPg.addOrganizationUnit(data), "Failed to add Organization Unit");
	}
}


	
	
	


