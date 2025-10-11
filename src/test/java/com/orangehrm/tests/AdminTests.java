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
	private Map<String, String> adminDataMap = new HashMap<String, String>();
	private Set<String> orgList = new HashSet<String>();

	@BeforeClass
	public void setupAdminPage() {
		adminPg = new AdminPage();
		dashboardPg.navigateToAdminPage();
		adminDataMap.put("jobTitle", "QA Intern");
		adminDataMap.put("jobDescription", "abcd sdajia fasd;;kda");
		orgList.add("100: Administration");
		orgList.add("Engineering");
		orgList.add("Development");
		orgList.add("Quality Assurance");
		orgList.add("TechOps");
		orgList.add("Sales & Marketing");
		orgList.add("Sales");
		orgList.add("Marketing");
		orgList.add("Client Services");
		orgList.add("Technical Support");
		orgList.add("Finance");
		orgList.add("1: hola");
		orgList.add("juan perez");
	}
//	dataProvider = "adminDataMap", dataProviderClass = DataProviders.class
	@Test(priority = 1, dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyAddUser(Map<String, String> data) {
		Assert.assertTrue(adminPg.addUser(data), "Failed to add new user");
		Assert.assertTrue(adminPg.searchUser(data.get("username")), "User not found after creation");
	}

	@Test(priority = 2,dataProvider = "adminDataMap", dataProviderClass = DataProviders.class)
	public void verifyJobTitle(Map<String,String>adminDataMap) {
		Assert.assertTrue(adminPg.addJobTitle(adminDataMap), "Failed to add Job Title");
		Assert.assertTrue(adminPg.verifyJobTitle(adminDataMap.get("jobTitle")), "Job Title not listed");
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

	@Test(priority = 5,dataProvider = "orgStructureDataSet", dataProviderClass = DataProviders.class)
	public void verifyOrganizationStructure(String orgName) {
			boolean elementIsPresent = adminPg.validateExpectedElementExistInOrgStructure(orgName);
			Assert.assertTrue(elementIsPresent, "Organization structure does not exist for -" + orgName);
	}
}


	
	
	


