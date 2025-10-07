package com.orangehrm.tests;

import com.orangehrm.data.DataProviders;
import com.orangehrm.pages.PIMPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.orangehrm.Base.BasePageTest;
import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PIMTests extends BasePageTest{
	private static final Logger logger = LoggerManager.getLogger(PIMTests.class);
	private PIMPage pimPage;



	@BeforeClass
	public void setupPim() {
		pimPage = new PIMPage();
	}

	@Test(priority = 1, dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify adding new employee")
	public void verifyAddNewEmployee(Map<String, String> data)  {
		Assert.assertTrue(
				pimPage.createEmployee(data),
				"[Add Employee] Failed to open personal details page for new employee."
		);
	}

	@Test(priority = 4,dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify employee list view")
	public void verifyEmployeeListView() {
		pimPage.navigateToPimPage();
		List<Map<String, WebElement>> tableData = pimPage.getEmployeeTableData();
		Assert.assertTrue(
				tableData.size() > 0,
				"[List View] No employees found in table."
		);
		Assert.assertTrue(
				tableData.get(0).containsKey("First (& Middle) Name"),
				"[List View] Table headers missing expected columns."
		);
	}

	@Test(priority = 5,dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify employee search functionality")
	public void verifyEmployeeSearch(Map<String, String> data) {
		pimPage.searchEmployeeByName(data.get("firstname"));
		Assert.assertTrue(pimPage.isEmployeePresentInTable(data.get("firstname")),
				"[Search] Employee not found in search results.");
	}



	@Test(priority = 2,dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify employee personal details")
	public void verifyEmployeePersonalDetails(Map<String, String> data) {
		boolean personalDetailsVerified = pimPage.verifyEmployeeJobDetails(data.get("firstname"));
		Assert.assertTrue(personalDetailsVerified, "[Personal Details] Unable to open personal details page.");
	}

	@Test(priority =3,dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify employee job details")
	public void verifyEmployeeJobDetails(Map<String, String> data) {
		pimPage.navigateToJobs();
		boolean jobVerified = pimPage.verifyEmployeeJobDetails(data.get("firstname"));
		Assert.assertTrue(jobVerified, "[Job Details] Job details verification failed.");
	}


	

}
