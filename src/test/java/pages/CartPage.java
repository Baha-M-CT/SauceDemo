package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test='title']")
    public WebElement cartTitle;

    @FindBy(xpath = "(//*[@class=\"inventory_item_name\"])")
    public WebElement productText;

    @FindBy(id = "checkout")
    public WebElement checkoutBtn;

    @FindBy(css = "[data-test='continue-shopping']")
    public WebElement continueShoppingBtn;

    @FindBy(css = "[data-test='cart-quantity-label']")
    public WebElement cartQuantityLabel;

    @FindBy(css = "[data-test='cart-desc-label']")
    public WebElement cartDescLabel;


    @Step("Verify 'Your Cart' text is displayed")
    public void assertYourCartTextIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(cartTitle));
        String actualText = cartTitle.getText().trim();
        String expectedText = "Your Cart";
        Assert.assertEquals(expectedText, actualText, "Your Cart title text does not match expected text");
    }

    @Step("Verify product text is displayed")
    public void assertProductTextIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(productText));
        String actualText = productText.getText().trim();
        String expectedText = "Sauce Labs Fleece Jacket";
        Assert.assertEquals(expectedText, actualText, "Product title text does not match expected text");
    }

    @Step("Click on the checkout button")
    public void clickCheckoutBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    @Step("Navigate to the checkout page")
    public void navigateToCheckoutPage() {
        assertYourCartTextIsDisplayed();
        assertProductTextIsDisplayed();
        clickCheckoutBtn();
    }

    @Step("Verify 'Cart' page UI elements")
    public void assertCartPageElements() {
        wait.until(ExpectedConditions.visibilityOf(cartTitle));
        Assert.assertTrue(cartTitle.isDisplayed(), "Cart page is not visible");
        Assert.assertTrue(checkoutBtn.isDisplayed(), "Checkout button is not visible");
        Assert.assertTrue(continueShoppingBtn.isDisplayed(), "Continue Shopping button is not visible");
        Assert.assertTrue(cartQuantityLabel.isDisplayed(), "Continue Shopping button is not visible");
        Assert.assertTrue(cartDescLabel.isDisplayed(), "Continue Shopping button is not visible");
    }

}
