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

public class ThankYouPage extends BasePage {
    public ThankYouPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test='title']")
    public WebElement thankYouTitle;

    @FindBy(css = "[data-test='complete-header']")
    public WebElement thankYouMessage;

    @FindBy(css = "[data-test='complete-text']")
    public WebElement dispatchedMessage;


    @Step("Verify 'Checkout: Complete!' text is displayed")
    public void assertThankYouPageTitleIsDisplayed() {
        WebElement productTitleElement = wait.until(ExpectedConditions.visibilityOf(thankYouTitle));
        String actualText = productTitleElement.getText().trim();
        String expectedText = "Checkout: Complete!";
        Assert.assertEquals(expectedText, actualText, "Thank You title text does not match expected text");
    }

    @Step("Verify 'Thank you for your order!' message is displayed")
    public void assertThankYouMessageIsDisplayed() {
        WebElement productTitleElement = wait.until(ExpectedConditions.visibilityOf(thankYouMessage));
        String actualText = productTitleElement.getText().trim();
        String expectedText = "Thank you for your order!";
        Assert.assertEquals(expectedText, actualText, "Thank You Message text does not match expected text");
    }

    @Step("Verify 'Your order has been dispatched' message is displayed")
    public void assertDispatchedMessageIsDisplayed() {
        WebElement productTitleElement = wait.until(ExpectedConditions.visibilityOf(dispatchedMessage));
        String actualText = productTitleElement.getText().trim();
        String expectedText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        Assert.assertEquals(expectedText, actualText, "Dispatched Message text does not match expected text");
    }

    @Step("Assert that the 'Thank You' message is displayed")
    public void assertThankYouMessageDisplayed() {
        assertThankYouMessageIsDisplayed();
    }

    @Step("Assert that the 'Order has been dispatched' message is displayed")
    public void assertDispatchedMessageDisplayed() {
        assertDispatchedMessageIsDisplayed();
    }

    @Step("Assert that the 'Thank You' and 'Order has been dispatched' messages are displayed")
    public void assertOrderDispatchedMessagesDisplayed() {
        assertThankYouMessageDisplayed();
        assertDispatchedMessageDisplayed();
    }
}