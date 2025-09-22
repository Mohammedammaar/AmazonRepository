# Amazon Test Automation Suite

## 📌 Project Overview
This project is an **automated test suite** developed as part of an assessment task.  
It validates core functionalities of a  amazon website using **Selenium WebDriver, Java, TestNG, and Maven**.

The suite covers:
- Login functionality
- Product search functaionality
- Add to cart functionality
- Verify Broken link functionality

## 🛠️ Technology Stack
- **Java** (JDK 8+)
- **Selenium WebDriver** (version 4.15.0)
- **TestNG**
- **Maven** (for dependency management)
- **Extent Reports / TestNG Reports**
- **Git & GitHub** (version control)

## 📂 Project Structure
📂 Project Structure

```text
AssesmentTask/
├── src/test/java
│   ├── com.base             # Driver setup and base classes
│   ├── com.locatorPackage   # Page object locators
│   ├── com.pageExecution    # Test case implementations
│   ├── com.testExecution    # Test suite execution classes
│   ├── com.utilsPackage     # Utilities (listeners, retry, reports)
│   └── com.additionalTasks  # Extra tasks (e.g., broken link check)
│
├── test-output              # TestNG default reports
├── target                   # Extend reports and failed test screenshot
├── AmazonTestExecution.xml  # TestNG suite for core tests
├── VerifyLink.xml           # TestNG suite for broken link validation
├── pom.xml                  # Maven dependencies
└── README.md                # Project documentation

⚙️ Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone https://github.com/Mohammedammaar/AmazonRepository.git

1. *Import Project
    Open Eclipse
2. *Import as Maven Project.
    Download Dependencies
3.  *Update dependencies
     bash
     mvn clean install

▶️ Execution Instructions
 Run the test suite via TestNG XML:
 bash
 mvn clean test -DsuiteXmlFile=testng.xml
 Alternatively, right-click testng.xml in IDE → Run As → TestNG Suite.

 Run Only Broken Link Test
 Open VerifyLink.xml in IDE → Right click → Run As → TestNG Suite

📊 Reporting
After execution, reports are generated in the target/ folder.
Extent Report example:
target/AmazonSuiteTestReport.html
Contains pass/fail status, logs, and screenshots (for failed cases).

🧩 Implementation Guidelines Followed
Page Object Model (POM) for maintainability.
Used different types of WebElements for identifying and interacting with UI elements.
Assertions for test validation.
Independent test cases using @BeforeMethod and @AfterMethod.
Utilities for reusable methods, listeners, and retry logic.

## 🙌 Author
**Mohammed Ammaar**  
_Assessment Task Automation Suite_






