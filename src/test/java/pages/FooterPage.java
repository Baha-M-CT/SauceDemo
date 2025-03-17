package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;

public class FooterPage extends BasePage {
    public FooterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test='social-twitter']")
    public WebElement twitterLogo;

    @FindBy(css = "[data-test='social-facebook']")
    public WebElement facebookLogo;

    @FindBy(css = "[data-test='social-linkedin']")
    public WebElement linkedinLogo;
    @FindBy(css = "[data-test='footer-copy']")
    public WebElement copyRightText;


    @Step("Verify Footer elements are displayed")
    public void assertFooterElementsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(copyRightText));
        String actualText = copyRightText.getText().trim();
        String expectedText = "© 2025 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";
        Assert.assertEquals(expectedText, actualText, "Copyright text does not match expected text");
        Assert.assertTrue(twitterLogo.isDisplayed(), "Twitter Logo is not visible");
        Assert.assertTrue(facebookLogo.isDisplayed(), "Facebook Logo is not visible");
        Assert.assertTrue(linkedinLogo.isDisplayed(), "Linkedin Logo is not visible");
    }

    @Step("Click on Social media icons and verify the URL in new tab")
    public void checkSocialMediaIcons() {
        clickLogoAndVerify(twitterLogo, "https://x.com/saucelabs");
        clickLogoAndVerify(facebookLogo, "https://www.facebook.com/saucelabs");
        clickLogoAndVerify(linkedinLogo, "https://www.linkedin.com/company/sauce-labs/");
    }


    public void clickLogoAndVerify(WebElement logoElement, String expectedUrl) {
        String originalTab = driver.getWindowHandle();
        logoElement.click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(tabs.size() > 1, "Logo did not open in a new tab");
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL does not match expected URL");
        driver.close();
        driver.switchTo().window(originalTab);
    }

}