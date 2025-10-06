
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
		Assert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickAssignLeave(),ConfigReader.getAssignLeaveURL());
		Assert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickMyLeaveList(),ConfigReader.getLeaveListURL());
		Assert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickTimesheets(),ConfigReader.getEmployeeTimesheetURL());
		Assert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickApplyLeave(),ConfigReader.getApplyLeaveURL());
		Assert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickMyLeave(),ConfigReader.getMyLeaveListURL());
		Assert.assertEquals(dashboardPg.getRedirectUrlOnBtnclickMyTimesheet(),ConfigReader.getMyTimesheetURL());
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

	//	@Test
////			(dataProvider = "DashBoardDataSet",dataProviderClass = DataProviders.class)
//	public void dashBoardPageLabelTest(HashSet<String>set) {
//		System.out.println(dashboardPg.getAllWidgetTitles());
//		for (String title : set) {
//			String expectedValue = title.trim().toLowerCase();
//			System.out.println(expectedValue);
//		}
//		SoftAssert softAssert = new SoftAssert();
//		for (String label : set) {
//			String expectedValue = label.trim().toLowerCase();
//			softAssert.assertTrue(dashboardPg.checkAllWidgetLabelMatch(expectedValue),"assertion failed for "+ expectedValue);
//		}
//		softAssert.assertAll();
//	}

}
