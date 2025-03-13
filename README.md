# Selenium Testing Project for Saucedemo.com

Hello!

This project was created for the "QA Automation Engineer Challenge". 

The task is to develop an automated UI and End-to-End (E2E) test suite for a SauceDemo web application.

The GitHub Actions workflow is set up to automatically publish test report to GitHub Pages.

## Technologies Used

- **Selenium WebDriver**: A powerful tool for automating web browsers.
- **Java**: Core programming language.
- **Maven**: Build automation and dependency management.
- **TestNG**: Test execution and reporting.

## Pattern design used

- Page Object Model (POM) for a scalable and dynamic web automation framework.
- Factory pattern in DriverFactory class to create different WebDriver instances based on the requested browser.

## Prerequisites to run the project

- [JDK 11](https://www.oracle.com/co/java/technologies/javase/jdk11-archive-downloads.html) (Environment variable)
- [Maven](https://maven.apache.org/download.cgi) (Environment variable)


### How set environment variables

1. Download and save the folders where you want. Look at the links above.
2. Take in mind the `path` where you are saving your folders **that contains **jdk**.
3. Copy jdk path.
4. On windows, in search bar, type `environment variables` and click on `Edit the system environment variables`.
5. In the `System properties` window, click `environment variables`.
6. In `Environment variables` window in `Advanced tab`, in `System variables` section, double click `Path`.
7. In `Edit environment variable` click `New` button and paste the **path that contains jdk**, then click `Ok`.
8. Close the open windows and that's all to install JDK

#### Browsers
You can choose what browser to use by changing the value of the `browser` variable in the `src/test/resources/local.properties` file.

- Chrome
- Firefox

## Download and open project


#### Clone project using Gitbash

1. Select the folder when you would like clone the project
2. Open gitbash, copy and paste the following command

```bash
git clone 
```

## Run project

### Headless Mode On/Off

This framework is configured to run tests in headless mode. 
To enable headless mode, ensure the following line is set in the `src/test/resources/local.properties` file:


```ini
headless=true
```

Open the project with the desired IDE, then run the following command.

```bash
mvn clean test
```

## Generate and Open allure report

To open allure report run the following commands

```bash
mvn allure:report 
```
```bash
mvn allure:serve
```

In case if default port 8080 is already in use, you can use another port by specifying it in the command.

```bash
mvn allure:serve -D allure.serve.port=8086
```