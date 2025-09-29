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
