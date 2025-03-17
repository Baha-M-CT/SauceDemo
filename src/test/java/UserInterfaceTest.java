import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.FooterPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigUtils;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Feature("UI Feature")
public class UserInterfaceTest extends BaseTest {

    public UserInterfaceTest() {
    }

    LoginPage loginPage;
    ProductsPage productsPage;
    FooterPage footerPage;
    CartPage cartPage;
    ConfigUtils config = ConfigUtils.getInstance();
    String valid_username = config.getUsername();
    String valid_password = config.getPassword();


    @Test
    @Description("Validate Login page UI elements")
    public void checkLoginPageUI() {
        loginPage = new LoginPage(driver);
        loginPage.assertLoginCredentials();
        loginPage.assertPasswordDetails();
        loginPage.asserLoginPageElements();
    }

    @Test
    @Description("Validate Products page UI elements")
    public void checkProductPageUI() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        footerPage = new FooterPage(driver);

        loginPage.login(valid_username, valid_password);
        productsPage.assertProductsPageIsDisplayed();
        productsPage.assertProductsSideMenu();
        productsPage.assertProductsPageElements();
        footerPage.assertFooterElementsDisplayed();
        footerPage.checkSocialMediaIcons();
    }

    @Test
    @Description("Validate Cart page UI elements")
    public void checkCartPageUI() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        footerPage = new FooterPage(driver);
        cartPage = new CartPage(driver);

        loginPage.login(valid_username, valid_password);
        productsPage.assertProductsPageIsDisplayed();
        productsPage.navigateToCart();
        cartPage.assertCartPageElements();
        footerPage.assertFooterElementsDisplayed();
        footerPage.checkSocialMediaIcons();
        cartPage.continueShoppingBtn.click();
        productsPage.assertProductsPageIsDisplayed();
    }


}