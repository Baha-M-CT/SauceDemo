import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import pages.CartPage;
import pages.CheckoutPage;
import pages.OverviewPage;
import pages.ProductsPage;
import pages.LoginPage;
import pages.ThankYouPage;
import utils.ConfigUtils;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Feature("Place Order Feature")
public class OrderTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OverviewPage overviewPage;
    private ThankYouPage thankYouPage;

    @Test
    @Description("Sample test to demonstrate Allure reporting")

    public void productPlaceOrderTest() {
        ConfigUtils config = ConfigUtils.getInstance();
        String username = config.getUsername();
        String password = config.getPassword();

        loginPage = new LoginPage(driver);

        productsPage = new ProductsPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
        thankYouPage = new ThankYouPage(driver);
        cartPage = new CartPage(driver);

        loginPage.login(username, password);

        productsPage.addProductToCart();

        productsPage.navigateToCart();

        cartPage.navigateToCheckoutPage();

        checkoutPage.enterCheckoutDetailsAndComplete("Baha", "Mametyarov", "06117");

        overviewPage.finishOrder();

        thankYouPage.assertOrderDispatchedMessagesDisplayed();
    }

}