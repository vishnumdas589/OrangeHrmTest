package com.orangehrm.utils;

import com.orangehrm.managers.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String name) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileName = name + "_" + timestamp + ".png";
        String outDir = System.getProperty("user.dir") + "/output/screenshots/";
        String destpath = outDir + fileName;
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(destpath);
            FileUtils.copyFile(src, dest);

        } catch (Exception e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }
        return "../screenshots/" + fileName;


    }


}
