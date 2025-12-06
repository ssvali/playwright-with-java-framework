## _Playwright Java Automation Framework_

![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)

## Don't forget to give a :star: to make the project popular.

This repository contains an advanced test automation framework built using Playwright for Java.
It supports parallel execution, configurable environments, runtime system variable overrides, and integrates Allure reporting for rich, interactive test results.

## 🚀 Features

### ✔ Playwright With Java

Fast and reliable browser automation supporting:

    Chromium
    WebKit
    Firefox

### ✔ Parallel Test Execution

Configured via Maven Surefire/Failsafe to run tests in parallel, significantly reducing execution time.

### ✔ Flexible Configuration

The framework loads values from:

    config.properties (default)
    System variables passed via CLI or CI
    Environment variables
    Supported variables include:

    Variable	Description
    base.url	URL of application under test
    username	Login username
    password	Login password
    headless	Run browsers headless (true/false)
    env	Environment selection (DEV/QA/PROD)

### ✔ Allure Reporting

Integrated with Allure to generate modern, interactive HTML reports.



⚙️ Configuration Details
Default Configuration (config.properties)

Example:

base.url=https://example.com
username=testuser
password=testpass
headless=true
env=DEV

### 🔧 Override Configuration via System Variables

You can override any property directly from the command line.

Example Overrides
Run tests in headed mode:
mvn clean test -Dheadless=false

Run against QA environment:
mvn clean verify -Denv=QA

    Override URL, username, password:
    mvn clean verify -Dbase.url=https://staging.app.com -Dusername=admin -Dpassword=secret

Full example:

    mvn clean test \
    -Denv=QA \
    -Dbase.url=https://staging.app.com \
    -Dusername=qauser \
    -Dpassword=qapass \
    -Dheadless=false

### 🧪 Running Tests
Standard execution:

    mvn clean test

Run with verification:

    mvn clean verify
    Enable/disable headless:
    mvn clean test -Dheadless=true

### 🧵 Parallel Execution

Parallel execution is configured through Maven.

You can adjust threads based on CPU and CI capacity.

📊 Allure Reporting

Generate and view the Allure report after test execution:

Serve interactive report
allure serve target/allure-results

Generate static HTML report
allure generate target/allure-results -o target/allure-report --clean

### 🧰 Tech Stack

| Component      | Technology                        |
| -------------- | --------------------------------- |
| Language       | Java 17+                          |
| Automation     | Playwright for Java               |
| Build Tool     | Maven                             |
| Reporting      | Allure                            |
| CI Integration | GitHub Actions / Jenkins / Any CI |

