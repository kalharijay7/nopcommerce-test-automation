# nopcommerce-test-automation

## 📌 Overview
This repository contains a **Selenium-based test automation framework** for [nopCommerce](https://demo.nopcommerce.com/).  
It is a **practice project** designed to demonstrate strong skills in **Java, Selenium WebDriver, and TestNG**, following automation best practices and scalable design patterns.

---

## ⚙️ Tech Stack
- **Programming Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Testing Framework:** TestNG  
- **Build Tool:** Maven  
- **Design Pattern:** Page Object Model (POM), Factory  
- **Driver Management:** WebDriverManager  

---

## 🚀 Key Features
- Clean implementation of **Page Object Model** for maintainable tests  
- **Cross-browser testing** support  
- Structured test suites with **TestNG**  
- Reusable base and utility classes  
- Easy WebDriver setup using **WebDriverManager**  
- Scalable and modular project structure suitable for expanding test coverage  

---

## 📂 Project Structure
```nopcommerce-test-automation/
├── src/
│ ├── main/
│ │ └── java/
│ │ └── com/actions/ # actions methods
│ │ └── com/base/ # base classes
│ │ └── com/driver/ # browser driver classes
│ │ └── com/utils/ # utility classes  
│ └── test/
│ └── java/
│ └── com/tests/home # home page tests
│ └── com/tests/register # register page tests
│ └── com/tests/login # login page tests
│ └── com/tests/e2e/orders # end to end orders tests
├── pom.xml # Maven build file
├── TEST_CASES.md # Test cases already automated
└── README.md # Project documentation
```

---

## ▶️ How to Run Tests
1. Clone the repository:  
   ```bash
   git clone https://github.com/your-username/nopcommerce-test-automation.git
   cd nopcommerce-test-automation
   
2. Install dependencies:
mvn clean install

3. Execute tests with Maven:
mvn test

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

---
## 📈 Future Improvements
- Add HTML/Allure reports for better test reporting
- Implement CI/CD pipelines with Jenkins
- Enable data-driven testing using Excel/CSV
- Support parallel test execution to improve efficiency

---
## 👨‍💻 Author
Kalhari Jayasinghe – Quality Assurance Engineer | Developer
- Practicing automated testing frameworks and UI testing for web applications
- Open to freelance/portfolio projects in test automation
