package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "continue")
    public WebElement continueBtn;

    @FindBy(css = "[data-test='title']")
    public WebElement checkoutTitle;


    @Step("Verify 'Checkout: Your Information' text is displayed")
    public void assertCheckoutTextIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(checkoutTitle));
        String actualText = checkoutTitle.getText().trim();
        String expectedText = "Checkout: Your Information";
        Assert.assertEquals(expectedText, actualText, "Checkout title text does not match expected text");
    }

    @Step("Enter first name: {firstName}")
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
    }

    @Step("Enter last name: {lastName}")
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
    }

    @Step("Enter postal code: {postalCode}")
    public void enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(postalCodeField)).sendKeys(postalCode);
    }

    @Step("Click on the continue button")
    public void clickContinueBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    @Step("Insert checkout information and continue")
    public void enterCheckoutDetailsAndComplete(String firstName, String lastName, String postalCode) {
        assertCheckoutTextIsDisplayed();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinueBtn();
    }
}