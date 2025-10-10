# 🧪 nopcommerce-test-automation

![Java](https://img.shields.io/badge/Language-Java-orange?logo=openjdk)
![Selenium](https://img.shields.io/badge/Automation-Selenium%20WebDriver-brightgreen?logo=selenium)
![TestNG](https://img.shields.io/badge/Framework-TestNG-blue)
![Maven](https://img.shields.io/badge/Build-Maven-yellow?logo=apache-maven)
![Jenkins](https://img.shields.io/badge/CI%2FCD-Jenkins-red?logo=jenkins)
![Allure](https://img.shields.io/badge/Reports-Allure-purple?logo=allure)
![Log4j](https://img.shields.io/badge/Logging-Log4j-lightgrey?logo=apache)
![License](https://img.shields.io/badge/License-MIT-green)

---

## 📌 Overview
This repository contains a **Selenium-based test automation framework** for [nopCommerce](https://demo.nopcommerce.com/).  
It is a **practice project** designed to demonstrate strong skills in **Java, Selenium WebDriver, TestNG, Jenkins, Log4j, and Allure Reporting**, following automation best practices and scalable design patterns.

---

## ⚙️ Tech Stack
- **Programming Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Testing Framework:** TestNG  
- **Build Tool:** Maven  
- **Design Pattern:** Factory  
- **Driver Management:** WebDriverManager  
- **Logging Framework:** Log4j  
- **Report Generation:** Allure Reports  
- **CI/CD Integration:** Jenkins  

---

## 🚀 Key Features
- Clean implementation of test scripts with implicit and explicit waits 
- **Cross-browser testing** support  
- Structured and configurable **TestNG** test suites  
- **Log4j** integrated for detailed logging and debugging  
- **Allure Reports** for interactive and visually rich test reports  
- **Jenkins integration** for automated CI/CD execution  
- Reusable **BaseTest**, **DriverFactory**, and **Utility** classes  
- Scalable and modular project structure suitable for expanding test coverage  

---

## 📂 Project Structure
```bash
nopcommerce-test-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── com/actions/           # Page action methods
│   │       ├── com/base/              # Base and setup classes
│   │       ├── com/driver/            # Driver factory and management
│   │       ├── com/utils/             # Utility classes (e.g., logger, wait, config)
│   └── test/
│       └── java/
│           ├── com/tests/home/        # Home page tests
│           ├── com/tests/register/    # Registration page tests
│           ├── com/tests/login/       # Login page tests
│           └── com/tests/e2e/orders/  # End-to-end order flow tests
├── logs/                              # Log4j generated logs
├── allure-results/                    # Raw test results (auto-generated)
├── allure-report/                     # Generated Allure reports (optional)
├── pom.xml                            # Maven build file
├── TEST_CASES.md                      # Automated test case documentation
└── README.md                          # Project documentation
```

---

## ▶️ How to Run Tests
1.  Clone the repository:
    ```bash
    git clone [https://github.com/your-username/nopcommerce-test-automation.git](https://github.com/your-username/nopcommerce-test-automation.git)
    cd nopcommerce-test-automation
    ```
2.  Install dependencies:
    ```bash
    mvn clean install
    ```
3.  Execute tests with Maven:
    ```bash
    mvn test
    ```

---

## 📊 Generating Allure Reports

After test execution, **Allure** automatically stores test results in the `allure-results/` folder.

### ▶️ To View the Interactive Report
Run the following command in the project root:
```bash
allure serve allure-results
```
---
### 🧾 To Generate Static HTML Reports

Use the following command to generate a static Allure report:
```bash
allure generate allure-results --clean -o allure-report
```
---

## ⚙️ Jenkins Integration

Journey: I first integrated the test suite as a freestyle project on Jenkins. Fixed tools integration issues faced during the first few builds. Then I added another job where the test suite was run in a pipeline through a Jenkinsfile.
 
The framework integrates seamlessly with Jenkins for CI/CD automation.

### ✅ Features
- Automatically builds and executes test suites on every commit or scheduled trigger.  
- Generates and displays **Allure Reports** in Jenkins.  
- **Log4j** logs are available directly in the Jenkins console output.  

---

### ⚙️ Setup Steps

1. Install the **Allure Jenkins Plugin**  
2. Add a **Post-build Action → Allure Report**  
3. Set the results path to:  
   ```bash
   allure-results

---

## 🪵 Logging with Log4j

**Log4j** provides detailed logs for every test run to help with debugging and analysis.

### ✅ Features
- Logs are stored in the `logs/` directory.  
- Configured via `log4j.properties`.  
- Supports both **console** and **file appenders** for flexible debugging.

---

### 📄 Example Log Output
```text
2025-10-08 11:15:23 INFO  LoginTest: Login with invalid credentials started  
2025-10-08 11:15:25 ERROR LoginTest: Invalid credentials message not displayed
```
---
## ✅ Sample Test Case

Login Test: Verifies invalid login scenarios using different sets of credentials.

---

## 🛠️ Challenges Faced  
I encountered the following challenges while designing and implementing this automation framework,

### 1. Test Lifecycle and State Management 
- **Problem:** Individual test classes were not isolated; execution order left the browser in inconsistent states (e.g., a login test would leave the session active for the next test).  
- **Resolution:** Implemented a `BaseTest` class to centralize WebDriver initialization and teardown, and introduced `@BeforeMethod` / `@AfterMethod` hooks to guarantee that each test starts from a clean application state while navigating to its corresponding page.
### 2. Manage dependencies between the tests 
- **Problem:** Tests might have dependencies (e.g. a new user should be registered with a known user email and password to execute the successful login test ). Managing them in an efficient way is a must to ensure a smooth test suite run at the end.
- **Resolution:** Avoided test dependencies by adding additional flows which executed first with @BeforeMethod hook, so that the tests can run in parallel in CI/CD much faster, more reliably and less flaky. There is no need to worry about the order of test execution or about the data they need, as they are self-contained.
### 3. The Allure Report tab is invisible on the build page despite the correct configurations set in Jenkins
- **Problem:** The Allure Report tab was not visible on the build page, despite having set the correct configurations in the Jenkinsfile, plugins, and tools. This is because the Jenkins pipeline was configured in such a way that it stops or skips the Allure report generation stage if the preceding stage fails the build because of the failed tests.
<img width="1186" height="248" alt="image" src="https://github.com/user-attachments/assets/e0ab0478-1763-4903-b7f6-76ce0ddc3f9e" />

- **Resolution:** I needed to generate the Allure Report even if the tests fail. So I moved the Allure Report step from a standard stage to a post-action block, specifically within the always or failure condition. By placing the allure() command in the post section with the always condition, the report generation will attempt to run whether the preceding test stage passes or fails, ensuring the tab appears on the build page.

---
## 📈 Future Improvements
- Implement parallel test execution
- Enable data-driven testing using Excel/CSV
- Integrate Dockerized Selenium Grid
- Add Slack/Email notifications for CI build results

---
## 👨‍💻 Author
Kalhari Jayasinghe – Quality Assurance Engineer | Developer
- Practicing automated testing frameworks and UI testing for web applications
- Open to freelance/portfolio projects in test automation
