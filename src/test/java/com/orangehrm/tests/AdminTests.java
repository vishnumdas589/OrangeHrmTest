package com.orangehrm.tests;

import com.orangehrm.pages.PIMPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.orangehrm.Base.BasePageTest;
import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import com.orangehrm.utils.ConfigReader;


import com.orangehrm.Base.BasePageTest;
import com.orangehrm.data.DataProviders;
import com.orangehrm.pages.AdminPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class AdminTests extends BasePageTest{
	private static final Logger logger = LoggerManager.getLogger(AdminTests.class);
	private AdminPage adminPg;


	@BeforeClass(alwaysRun = true)
	public void setupAdminPage() {
		navigateToBaseUrl();
		loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultPassword());
		adminPg = new AdminPage();
	}
	@Test(priority = 1, groups = {"smoke"})
	public void verifyAdminPage() {
		adminPg.navigateToAdminPage();
		Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(),ConfigReader.getAdminURL());
	}
	@Test(priority = 1, groups = {"sanity"})
	public void verifyAddUser1() {
		adminPg.navigateToAdminPage();
		Assert.assertTrue(adminPg.addUser("Admin","vishnu1","Enabled","vishnu1mdas2","abcdef@12345"), "Failed to add new user");
		Assert.assertTrue(adminPg.searchUser("vishnu1mdas2"), "User not found after creation");
	}
	@Test(priority = 1, groups = {"regression", "admin"},dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyAddUser2(Map<String, String> data) {
		Assert.assertTrue(adminPg.addUser(data), "Failed to add new user");
		Assert.assertTrue(adminPg.searchUser(data.get("username")), "User not found after creation");
	}

	@Test(priority = 2,groups = {"regression", "admin"},dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyJobTitle(Map<String,String>adminDataMap) {
		Assert.assertTrue(adminPg.addJobTitle(adminDataMap), "Failed to add Job Title");
		Assert.assertTrue(adminPg.verifyJobTitle(adminDataMap.get("jobTitle")), "Job Title not listed");
	}

	@Test(priority = 3, groups = {"regression", "admin"},dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyPayGrade(Map<String, String> data) {
		Assert.assertTrue(adminPg.addPayGradeWithSalary(data), "Failed to add Pay Grade");
		Assert.assertTrue(adminPg.verifyPayGrade(data.get("payGrade")), "Pay Grade not listed");
	}

	@Test(priority = 4, groups = {"regression", "admin"},dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyEmploymentStatus(Map<String, String> data) {
		Assert.assertTrue(adminPg.addEmploymentStatus(data), "Failed to add Employment Status");
		Assert.assertTrue(adminPg.verifyEmploymentStatus(data.get("employmentStatus")), "Employment Status not listed");
	}

	@Test(priority = 5,groups = {"regression", "admin"},dataProvider = "orgStructureDataSet", dataProviderClass = DataProviders.class)
	public void verifyOrganizationStructure(String orgName) {
			boolean elementIsPresent = adminPg.validateExpectedElementExistInOrgStructure(orgName);
			Assert.assertTrue(elementIsPresent, "Organization structure does not exist for -" + orgName);
	}
}


	
	
	


