package com.orangehrm.tests;

import com.orangehrm.data.DataProviders;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utils.ConfigReader;
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



	@BeforeClass(alwaysRun = true)
	public void setupPim() {
		navigateToBaseUrl();
		loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultPassword());
		pimPage = new PIMPage();
	}
	@Test(priority = 1, groups = {"smoke"})
	public void verifyPIMPage() {
		pimPage.navigateToPimPage();
		Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), ConfigReader.getPIMURL());
	}

	@Test(priority = 1, groups = {"sanity"})
	public void verifyAddNewEmployee1() {
		Assert.assertTrue(pimPage.createEmployee("vishnu1" , "mdas", "vishnu1mdas","abcd@12356"));
	}
	@Test(priority = 1,groups = {"regression", },dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify adding new employee")
	public void verifyAddNewEmployee2(Map<String, String> data)  {
		Assert.assertTrue(
				pimPage.createEmployee(data),
				"[Add Employee] Failed to open personal details page for new employee."
		);
	}
	@Test(priority = 2,groups = {"regression"},dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify employee personal details")
	public void verifyEmployeePersonalDetails(Map<String, String> data) {
		boolean personalDetailsVerified = pimPage.verifyEmployeePersonalDetails(data.get("firstname"));
		Assert.assertTrue(personalDetailsVerified, "[Personal Details] Unable to open personal details page.");
	}

	@Test(priority =3,groups = {"regression" },dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify employee job details")
	public void verifyEmployeeJobDetails(Map<String, String> data) {
		pimPage.navigateToJobs();
		boolean jobVerified = pimPage.verifyEmployeeJobDetails(data.get("firstname"));
		Assert.assertTrue(jobVerified, "[Job Details] Job details verification failed.");
	}
	@Test(priority = 4,groups = {"regression"},dataProvider = "adminDataMap", dataProviderClass = DataProviders.class, description = "Verify employee search functionality")
	public void verifyEmployeeSearch(Map<String, String> data) {
		pimPage.searchEmployeeByName(data.get("firstname"));
		Assert.assertTrue(pimPage.isEmployeePresentInTable(data.get("firstname")),
				"[Search] Employee not found in search results.");
	}
	@Test(priority = 5,groups = {"regression"},description = "Verify employee list view")
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








	

}
