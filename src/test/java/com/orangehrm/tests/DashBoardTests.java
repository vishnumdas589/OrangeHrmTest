
package com.orangehrm.tests;
import com.orangehrm.managers.DriverManager;
import com.orangehrm.utils.ConfigReader;
import org.apache.logging.log4j.Logger;
import com.orangehrm.Base.BasePageTest;
import com.orangehrm.managers.LoggerManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


public class DashBoardTests extends BasePageTest {
	private static final Logger logger = LoggerManager.getLogger(DashBoardTests.class);
	@BeforeMethod
	public void beforeMethod() {
		if(DriverManager.getDriver().getCurrentUrl().trim().equalsIgnoreCase(ConfigReader.getLoginURL())){
			loginPg.loginWithCredentials(ConfigReader.getDefaultUserName(), ConfigReader.getDefaultPassword());
		}
	}
	@AfterMethod
	public void afterMethod() {
		dashboardPg.logout();
	}

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
		softAssert.assertEquals(dashboardPg.getRedirectUrl(dashboardPg.getBtnAssignLeave()),ConfigReader.getAssignLeaveURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrl(dashboardPg.getBtnLeaveList()),ConfigReader.getLeaveListURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrl(dashboardPg.getBtnTimesheets()),ConfigReader.getEmployeeTimesheetURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrl(dashboardPg.getBtnApplyLeave()),ConfigReader.getApplyLeaveURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrl(dashboardPg.getBtnMyLeave()),ConfigReader.getMyLeaveListURL());
		softAssert.assertEquals(dashboardPg.getRedirectUrl(dashboardPg.getBtnMyTimesheet()),ConfigReader.getMyTimesheetURL());
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
//	@Test
//	public void verifyPendingLeaves(){
//		Assert.assertTrue(true);
//	}

	@Test
	public void verifyDashboardCustomization() {
		Assert.assertTrue(dashboardPg.isCustomisationIconDisplayed());
	}

}
