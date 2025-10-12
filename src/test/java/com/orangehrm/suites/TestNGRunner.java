package com.orangehrm.suites;

import org.testng.TestNG;
import org.apache.logging.log4j.Logger;
import com.orangehrm.managers.LoggerManager;

import java.util.ArrayList;
import java.util.List;

public class TestNGRunner {

    private static final Logger logger = LoggerManager.getLogger(TestNGRunner.class);

    public static void main(String[] args) {
        try {
            // Determine suite to execute (default: regression)
            String suiteName = (args.length > 0) ? args[0].toLowerCase() : "regression";
            String suitePath = getSuitePath(suiteName);

            logger.info("Starting TestNG Suite: " + suiteName.toUpperCase());
            logger.info("Suite file path: " + suitePath);

            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<>();
            suites.add(suitePath);
            testng.setTestSuites(suites);
            testng.run();

            logger.info("Suite Execution Completed: " + suiteName.toUpperCase());

        } catch (Exception e) {
            logger.error("Suite Execution Failed: " + e.getMessage());
            -e.printStackTrace();
        }
    }

    private static String getSuitePath(String suiteName) {
        String basePath = System.getProperty("user.dir") + "/src/test/resources/testng-suites/";
        switch (suiteName) {
            case "smoke":
                return basePath + "smoke-suite.xml";
            case "sanity":
                return basePath + "sanity-suite.xml";
            case "full":
                return basePath + "full-suite.xml";
            case "regression":
            default:
                return basePath + "regression-suite.xml";
        }
    }
}
