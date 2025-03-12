package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class OverviewPage extends BasePage {
    public OverviewPage(WebDriver driver) {
        super(driver);
        //PageFactory.initElements(driver, this);
    }

//    public static final By finishButn = By.id("finish");
//    private static final String EXPECTED_URL = "https://www.saucedemo.com/checkout-step-two.html";
//    public static final By overviewTitle = By.cssSelector("[data-test='title']");
//    public static final By subtotalValue = By.cssSelector("[data-test='subtotal-label']");
    @FindBy(id = "finish")
    public WebElement finishButn;

    @FindBy(css = "[data-test='title']")
    public WebElement overviewTitle;

    @FindBy(css = "[data-test='subtotal-label']")
    public WebElement subtotalValue;

    private static final String EXPECTED_URL = "https://www.saucedemo.com/checkout-step-two.html";


    @Step("Verify 'Checkout: Overview' text is displayed")
    public void assertProductsTextIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(overviewTitle));
        String actualText = overviewTitle.getText().trim();
        String expectedText = "Checkout: Overview";
        Assert.assertEquals(expectedText, actualText, "Product text does not match expected text");
    }

    @Step("Verify subtotal value is displayed")
    public void assertSubtotalValue() {
        wait.until(ExpectedConditions.visibilityOf(subtotalValue));
        String actualText = subtotalValue.getText().trim();
        String expectedText = "Item total: $49.99";
        Assert.assertEquals(expectedText, actualText, "Subtotal Value text does not match expected text");
    }

    @Step("Click on the finish button")
    public void clickFinishBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButn)).click();
    }

    @Step("Verify current URL matches expected URL")
    public void assertURLMatchesExpected() {
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, EXPECTED_URL, "Current URL does not match expected URL");
    }

    @Step("Complete the order")
    public void finishOrder() {
        assertProductsTextIsDisplayed();
        assertSubtotalValue();
        assertURLMatchesExpected();
        clickFinishBtn();
    }

}