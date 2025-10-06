package com.orangehrm.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orangehrm.managers.LoggerManager;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

public class DashboardPage extends BasePage {
    private static final Logger logger = LoggerManager.getLogger(DashboardPage.class);

    public DashboardPage() {
        super();
        // TODO Auto-generated constructor stub
    }

    @FindBy(xpath = "//h6[text()='Dashboard']")
    WebElement labelDashboard;
    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    WebElement lnkUserDropdown;
    @FindBy(xpath = "//a[text()='Logout']")
    WebElement lnkLogout;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']")
    List<WebElement> widgets;

    @FindBy(xpath = "//i[@class='oxd-icon bi-gear-fill orangehrm-leave-card-icon']")
    WebElement iconCustomisation;
    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[contains(.,'Quick Launch')]")
    WebElement labelEmpDistByLoc;
    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[contains(.,'Employee Distribution by Sub Unit')]")
    WebElement labelEmpDistBySubUint;

    @FindBy(xpath = "//button[@class='oxd-icon-button orangehrm-quick-launch-icon']")
    List<WebElement> btnsQuickLaunch;

    @FindBy(xpath = "//button[@title='Assign Leave']")
    WebElement btnAssignLeave;
    @FindBy(xpath = "//button[@title='Leave List']")
    WebElement btnLeaveList;
    @FindBy(xpath = "//button[@title='Timesheets']")
    WebElement btnTimesheets;
    @FindBy(xpath = "//button[@title='Apply Leave']")
    WebElement btnApplyLeave;
    @FindBy(xpath = "//button[@title='My Leave']")
    WebElement btnMyLeave;
    @FindBy(xpath = "//button[@title='My Timesheet']")
    WebElement btnMyTimesheet;

    public List<WebElement> getChartsEmpDist() {
        return chartsEmpDist;
    }

    public List<WebElement> getLegendContainer() {
        return legendContainer;
    }

    @FindBy(xpath = "//div[@class='oxd-pie-chart']")
    List<WebElement> chartsEmpDist;
    @FindBy(xpath = "//ul[@class ='oxd-chart-legend']")
    List<WebElement> legendContainer;

    public boolean isCustomisationIconDisplayed() {
        return iconCustomisation.isDisplayed();
    }

    public int getQuickLaunchButtonsCount() {
        return btnsQuickLaunch.size();
    }

    public boolean isAllQuickLaunchButtonsDisplayed() {
        return btnsQuickLaunch.stream().allMatch(btn -> btn.isDisplayed());
    }

    public int getChartCount() {
        return chartsEmpDist.size();
    }

    public int getLegendCount() {
        return legendContainer.size();
    }

    public boolean isEmpDistChartDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(chartsEmpDist));
        return chartsEmpDist.stream().allMatch(chart -> chart.isDisplayed());
    }

    public boolean isEmpDistChartLegendsDisplayed() {
        return legendContainer.stream().allMatch(legend -> wait.until(ExpectedConditions.elementToBeClickable(legend)).isDisplayed());
    }

    public String getRedirectUrlOnBtnclickAssignLeave() {
        btnAssignLeave.click();
        String redirectedUrl = driver.getCurrentUrl();
        navigateToPreviousUrl();
        return redirectedUrl;
    }

    public String getRedirectUrlOnBtnclickMyLeaveList() {
        btnLeaveList.click();
        String redirectedUrl = driver.getCurrentUrl();
        navigateToPreviousUrl();
        return redirectedUrl;
    }

    public String getRedirectUrlOnBtnclickTimesheets() {
        btnTimesheets.click();
        String redirectedUrl = driver.getCurrentUrl();
        navigateToPreviousUrl();
        return redirectedUrl;
    }

    public String getRedirectUrlOnBtnclickApplyLeave() {
        btnApplyLeave.click();
        String redirectedUrl = driver.getCurrentUrl();
        navigateToPreviousUrl();
        return redirectedUrl;
    }

    public String getRedirectUrlOnBtnclickMyLeave() {
        btnMyLeave.click();
        String redirectedUrl = driver.getCurrentUrl();
        navigateToPreviousUrl();
        return redirectedUrl;
    }

    public String getRedirectUrlOnBtnclickMyTimesheet() {
        btnMyTimesheet.click();
        String redirectedUrl = driver.getCurrentUrl();
        navigateToPreviousUrl();
        return redirectedUrl;
    }

    public boolean verifyAllPieChartsInteractive(WebElement chart, WebElement legendContainer) {

        wait.until(ExpectedConditions.visibilityOfAllElements(chartsEmpDist));

        boolean anyChartChanged = false;

        List<WebElement> legends = legendContainer.findElements(By.cssSelector("li, span, .oxd-chart-legend"));

        for (WebElement legend : legends) {
            wait.until(ExpectedConditions.elementToBeClickable(legend));
            String beforeHash = getCanvasHash(chart);
            legend.click();

            // Wait until the chart's image hash changes
            boolean changed = wait.until(driver -> {
                String afterHash = getCanvasHash(chart);
                return !afterHash.equals(beforeHash);
            });

            if (changed) {
                System.out.println("Chart "+chart.getText()+" updated on legend: " + legend.getText());
                anyChartChanged = true;
            } else {
                System.out.println("Chart  did NOT change for legend: " + legend.getText());
            }

        }


        return anyChartChanged;
    }

    private String getCanvasHash(WebElement canvas) {
        try {
            byte[] imageBytes = canvas.getScreenshotAs(OutputType.BYTES);
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(imageBytes);
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public void clickUserDrop() {
        lnkUserDropdown.click();
    }

    public void clickLogout() {
        lnkLogout.click();
    }

    public boolean isDashboardPageDisplayed() {
        return labelDashboard.isDisplayed();
    }

    public void logout() {

        clickUserDrop();
        clickLogout();

    }

    public int getWidgetsCount() {
        return widgets.size();
    }

    public boolean isAllWidgetsDisplayed() {
        return widgets.stream().allMatch(webElement -> webElement.isDisplayed());
    }

    //	for debugging
    public List<String> getAllWidgetTitles() {
        return widgets
                .stream()
                .map(WebElement::getText)
                .toList();
    }

}
