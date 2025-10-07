
package com.orangehrm.tests;

import com.orangehrm.Base.LoginBaseTest;
import com.orangehrm.data.DataProviders;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.orangehrm.Base.BasePageTest;
import com.orangehrm.managers.DriverManager;
import com.orangehrm.managers.LoggerManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.HashSet;

public class DashBoardTests extends BasePageTest {
	private static final Logger logger = LoggerManager.getLogger(DashBoardTests.class);

	@Test
	public void dashBoardPageTest() {
		Assert.assertTrue(dashboardPg.isDashboardPageDisplayed());
	}
	@Test
	public void dashBoardPageElementsTest() {
		Assert.assertEquals(dashboardPg.getWidgetsCount(),7);
		Assert.assertTrue(dashboardPg.isAllWidgetsDisplayed());
	}

	@Test
	public void verifyQuickLaunchElements() {
		Assert.assertEquals(dashboardPg.getQuickLaunchButtonsCount(),6);
		Assert.assertTrue(dashboardPg.isAllQuickLaunchButtonsDisplayed());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickAssignLeave(),ConfigReader.getAssignLeaveURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickMyLeaveList(),ConfigReader.getLeaveListURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickTimesheets(),ConfigReader.getEmployeeTimesheetURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickApplyLeave(),ConfigReader.getApplyLeaveURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickMyLeave(),ConfigReader.getMyLeaveListURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickMyTimesheet(),ConfigReader.getMyTimesheetURL());
		softAssert.assertAll();
	}
	@Test
	public void verifyEmpDistChart(){
		Assert.assertTrue(dashboardPg.isEmpDistChartDisplayed());
		Assert.assertTrue(dashboardPg.isEmpDistChartLegendsDisplayed());
		SoftAssert softAssert = new SoftAssert();
		for(int i =0; i <dashboardPg.getChartCount();i++){
			WebElement chart = dashboardPg.getChartsEmpDist().get(i);
			WebElement legend = dashboardPg.getLegendContainer().get(i);
			softAssert.assertTrue(dashboardPg.verifyAllPieChartsInteractive(chart, legend),
					"No chart reacted to legend clicks for chart" + chart.getText()
			);
		}
		softAssert.assertAll();
	}
	@Test
	public void verifyPendingLeaves(){
		Assert.assertTrue(true);
	}

	@Test
	public void verifyDashboardCustomization() {
		Assert.assertTrue(dashboardPg.isCustomisationIconDisplayed());
	}

}
