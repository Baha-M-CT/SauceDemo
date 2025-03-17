package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.ExcelReader;

import java.util.List;


public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    public WebElement fleeceJacketAddtoCartBtn;

    @FindBy(id = "shopping_cart_container")
    public WebElement cartIcon;

    @FindBy(css = "[data-test='product-sort-container']")
    public WebElement filterTab;

    @FindBy(css = "[data-test='title']")
    public WebElement productTitle;

    @FindBy(xpath = "//*[@class=\"app_logo\"]")
    public WebElement appLogo;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuBtn;

    @FindBy(id = "react-burger-cross-btn")
    public WebElement burgerMenuCloseBtn;

    @FindBy(className = "bm-menu")
    public WebElement sideMenu;

    @FindBy(xpath = "//*[@id='inventory_sidebar_link']")
    public WebElement inventorySidebarLink;

    @FindBy(xpath = "//*[@id='about_sidebar_link']")
    public WebElement aboutSidebarLink;

    @FindBy(xpath = "//*[@id='logout_sidebar_link']")
    public WebElement logoutSidebarLink;

    @FindBy(xpath = "//*[@id='reset_sidebar_link']")
    public WebElement resetSidebarLink;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    public WebElement cartBadge;


    public boolean isAppLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(appLogo));
        return appLogo.isDisplayed();
    }

    public void selectHighToLowFilterOption() {
        Select filterDropDown = new Select(filterTab);
        filterDropDown.selectByValue("hilo");
    }

    public void clickFleeceJacketAddtoCartBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(fleeceJacketAddtoCartBtn)).click();
    }

    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void clickBurgerMenuBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenuBtn)).click();
    }

    @Step("Verify 'Products' text is displayed")
    public void assertProductsTextIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        String actualText = productTitle.getText().trim();
        String expectedText = "Products";
        Assert.assertEquals(expectedText, actualText, "Product title text does not match expected text");
    }

    @Step("Assert cart badge number is {num}")
    public void assertCartBadgeNumber(int num) {
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
        String actualText = cartBadge.getText().trim();
        String expectedText = String.valueOf(num);
        Assert.assertEquals(actualText, expectedText, "Cart badge number does not match expected number");
    }

    @Step("Add product to cart")
    public void addProductToCart() {
        assertProductsTextIsDisplayed();
        isAppLogoDisplayed();
        clickFleeceJacketAddtoCartBtn();
        assertCartBadgeNumber(1);
    }

    @Step("Navigate to cart")
    public void navigateToCart() {
        clickCartIcon();
    }

    @Step("Verify products page is displayed")
    public void assertProductsPageIsDisplayed() {
        assertProductsTextIsDisplayed();
    }

    @Step("Verify 'Products' side menu is displayed")
    public void assertProductsSideMenu() {
        wait.until(ExpectedConditions.visibilityOf(burgerMenuBtn));
        clickBurgerMenuBtn();
        Assert.assertTrue(sideMenu.isDisplayed(), "Side menu is not visible");
        wait.until(ExpectedConditions.visibilityOf(inventorySidebarLink));
        Assert.assertTrue(inventorySidebarLink.isDisplayed(), "All items link is not visible");
        Assert.assertTrue(aboutSidebarLink.isDisplayed(), "About link is not visible");
        Assert.assertTrue(logoutSidebarLink.isDisplayed(), "Logout link is not visible");
        Assert.assertTrue(resetSidebarLink.isDisplayed(), "Reset App State link is not visible");
        burgerMenuCloseBtn.click();
    }

    @Step("Verify 'Products' page UI elements")
    public void assertProductsPageElements() {
        wait.until(ExpectedConditions.visibilityOf(cartIcon));
        Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon is not visible");
        Assert.assertTrue(filterTab.isDisplayed(), "Filter tab is not visible");
    }

    public void verifyProductsFromExcel(String excelFilePath) throws Exception {
        // Read products data from Excel
        List<Product> products = ExcelReader.getProductsFromExcel(excelFilePath);

        // Loop through each product and check on the page
        for (Product product : products) {
            String productId = product.getId();
            String expectedName = product.getName();
            String expectedDescription = product.getDescription();

            // Find product name and description on the page using the product ID
            String actualName = getProductNameById(productId);
            String actualDescription = getProductDescriptionById(productId);

            // Assert the product data
            Assert.assertEquals(actualName, expectedName, "Product name mismatch for ID " + productId + ": Expected " + expectedName + ", but got " + actualName);
            Assert.assertEquals(actualDescription, expectedDescription, "Product description mismatch for ID " + productId + ": Expected " + expectedDescription + ", but got " + actualDescription);

            // Assert the "Add to Cart" button is visible and clickable
            Assert.assertTrue(isAddToCartBtnVisibleAndClickable(productId), "Add to Cart button is not visible or clickable for product ID " + productId);

        }
    }

    public String getProductNameById(String id) {
        String nameSelector = String.format("[data-test='item-%s-title-link']", id);
        return driver.findElement(By.cssSelector(nameSelector)).getText();
    }

    public boolean isAddToCartBtnVisibleAndClickable(String id) {
        String addToCartBtnSelector = String.format("//a[@data-test='item-%s-title-link']/../following-sibling::div[@class='pricebar']/button", id);
        return driver.findElement(By.xpath(addToCartBtnSelector)).isDisplayed() && driver.findElement(By.xpath(addToCartBtnSelector)).isEnabled();
    }


    public String getProductDescriptionById(String id) {
        String nameSelector = String.format("[data-test='item-%s-title-link']+div", id);
        return driver.findElement(By.cssSelector(nameSelector)).getText();
    }

}
