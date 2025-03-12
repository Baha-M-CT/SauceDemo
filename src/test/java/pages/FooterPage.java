package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

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

}