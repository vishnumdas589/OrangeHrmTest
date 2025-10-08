# OrangeHRM Test Automation Suite 🚀

This repository contains the test automation framework for OrangeHRM, using Selenium WebDriver + TestNG.  
It covers modules such as **Login**, **PIM (Employee)**,**DashBoard**, **Leave** and **Admin** management.

---
## 📁 Project Structure
OrangeHrmTest/
│
├── output/
│ ├── logs/ ← Application & test logs (log4j2)
│ ├── screenshots/ ← Captured screenshots on test failure
│ └── test-output/
│ └── ExtentReport.html ← Consolidated TestNG + Extent Report
│
├── resources/
│ ├── Config.properties ← Global config (URL, credentials, waits, etc.)
│ ├── cross-browser.xml ← XML suite for running tests on multiple browsers
│ ├── testng.xml ← Default test suite (all tests)
│ ├── data-driven.xml ← Suite for running data-driven test sets
│ ├── AdminData.xlsx ← Excel data for Admin module (DataProviders)
│ └── log4j2.xml ← Logger configuration
│
├── src/
│ ├── main/java/com/orangehrm/
│ │ ├── data/ ← TestNG DataProviders
│ │ ├── managers/ ← DriverManager, LoggerManager, ConfigReader
│ │ ├── pages/ ← Page Object classes (LoginPage, PIMPage, LeavePage, AdminPage, etc.)
│ │ ├── utils/ ← Utility classes (ExcelReader, helpers)
│ │ └── resources/ ← log4j2.xml and internal configs
│ │
│ └── test/java/com/orangehrm/
│ ├── base/ ← BasePageTest setup/teardown class
│ ├── listeners/ ← TestNG listeners (logging, screenshot capture)
│ └── tests/ ← Test classes (LoginTests, LeaveTests, AdminTests, etc.)
│
├── pom.xml ← Maven build configuration
└── README.md

 ⚙️ Key Features

- **Page Object Model (POM)** – clean separation of locators and logic  
- **Data-Driven Testing** – Excel-based input using `ExcelReader` and `DataProviders`  
- **Cross-Browser Support** – run tests in Chrome, Edge, or Firefox via `cross-browser.xml`  
- **ExtentReports Integration** – rich HTML reporting with screenshots  
- **Log4j2 Logging** – step-by-step info and error tracking  
- **Modular Test Design** – reusable setup via `BasePageTest` and DriverManager  
- **Configurable Execution** – environment URLs, waits, credentials all from `Config.properties`  

---
graph TD
    A[TestNG XML <br/> (testng.xml / cross-browser.xml / data-driven.xml)]
      --> B[BasePageTest <br/> (Driver setup, Login, Suite Init)]
      --> C[Page Object Layer <br/> (AdminPage, PIMPage, LeavePage, LoginPage)]
      --> D[Utilities Layer <br/> (DriverManager, ConfigReader, ExcelReader, LoggerManager)]
      --> E[Output Layer <br/> (ExtentReport.html, Logs, Screenshots)]

    style A fill:#4c8bf5,stroke:#fff,color:#fff
    style B fill:#4285f4,stroke:#fff,color:#fff
    style C fill:#34a853,stroke:#fff,color:#fff
    style D fill:#fbbc04,stroke:#fff,color:#000
    style E fill:#ea4335,stroke:#fff,color:#fff

⚙️ Execution Flow (Step-by-Step)

1️⃣ TestNG XML

Defines which suites or browsers to run (testng.xml, cross-browser.xml, etc.)

Passes browser type as a parameter (e.g., Chrome, Edge, Firefox).

2️⃣ BasePageTest (Setup Layer)

Initializes WebDriver through DriverManager.

Loads config (ConfigReader).

Logs in once before all tests.

Creates reusable instances of all page classes.

3️⃣ Page Object Layer (POM)

Each module (Login, Admin, PIM, Leave, etc.) has its own class under /pages/.

WebElements located using @FindBy.

Business logic (actions, verifications) encapsulated in page methods.

Example:

adminPage.addUser(data.get("userRole"), data.get("empName"), data.get("status"), ...);


4️⃣ Data Layer (DataProviders + ExcelReader)

Pulls test data from Excel → HashMap or 2D arrays.

Example:

@Test(dataProvider="AdminDataMap")
public void testAddUser(Map<String, String> data) { ... }


5️⃣ Utilities Layer

Handles configurations, logging, driver management, waits, reusable methods.

Classes include:

DriverManager → manages driver instances

LoggerManager → handles Log4j2

ConfigReader → reads from Config.properties

ExcelReader → reads and parses Excel test data

6️⃣ Output & Reporting Layer

ExtentReport.html generated under /output/test-output/.

screenshots/ for failed test captures.

logs/ for runtime log output.

All results consolidated under /output/.

🔧 Future Enhancements

CI/CD pipeline (GitHub Actions / Jenkins)

Parallel test execution optimization

Modular validator package

Integration with Allure or ReportPortal

Modular suite segregation (Smoke, Regression, Sanity)
