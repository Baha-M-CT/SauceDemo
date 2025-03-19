package base;

import factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTest.class);

    public Object getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() {
        driver = new DriverFactory().initializeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String testCaseName = result.getMethod().getMethodName();
            logger.error("Test case failed: " + testCaseName, result.getThrowable());
            File destFile = new File("target" + File.separator + "screenshots" + File.separator + testCaseName + ".png");
            takeScreenShot(destFile);

            // Attach logs to Allure report
            String logFilePath = "target/logs/test.log";
            try (InputStream logStream = new FileInputStream(logFilePath)) {
                Allure.addAttachment("Test Logs", "text/plain", logStream, ".log");
            } catch (IOException e) {
                logger.error("Failed to attach logs to Allure report", e);
            }
        }
        driver.quit();
    }

    public void takeScreenShot(File destFile) {
        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, destFile);
            InputStream is = new FileInputStream(destFile);
            Allure.addAttachment("screenshot", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}