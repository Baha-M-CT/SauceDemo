import org.testng.annotations.Listeners;
import base.BaseTest;
import pages.LoginPage;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.ProductsPage;
import utils.ConfigUtils;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Feature("Auth Feature")
public class LoginTest extends BaseTest {

    public LoginTest() {
    }

    LoginPage loginPage;
    ProductsPage productsPage;
    ConfigUtils config = ConfigUtils.getInstance();
    String valid_username = config.getUsername();
    String valid_password = config.getPassword();


    @Test(description = "Verify user can login with valid username and password")
    public void loginWithValidUsernameAndPassword() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        loginPage.login(valid_username, valid_password);
        productsPage.assertProductsPageIsDisplayed();
    }

    @Test(description = "Verify user cannot login with invalid username")
    public void loginWithInvalidUsername() {
        loginPage = new LoginPage(driver);
        loginPage.login("user_name", "secret_sauce");
        loginPage.assertInvalidUsernameOrPasswordErrorMessage();

    }

    @Test(description = "Verify user cannot login with invalid password")
    public void loginWithInvalidPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login(valid_username, "inv_password");
        loginPage.assertInvalidUsernameOrPasswordErrorMessage();

    }

    @Test(description = "Verify user cannot login with invalid credentials")
    public void loginWithInvalidUsernameAndPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login("user", "inv_password");
        loginPage.assertInvalidUsernameOrPasswordErrorMessage();

    }

    @Test(description = "Verify user cannot login with empty credentials")
    public void loginWithEmptyUsernameAndPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login("", "");
        loginPage.assertEmptyUsernameOrBothErrorMessageIsDisplayed();

    }

    @Test(description = "Verify user cannot login with empty username")
    public void loginWithEmptyUsername() {
        loginPage = new LoginPage(driver);
        loginPage.login("", valid_password);
        loginPage.assertEmptyUsernameOrBothErrorMessageIsDisplayed();

    }

    @Test(description = "Verify user cannot login with empty password")
    public void loginWithEmptyPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login(valid_username, "");
        loginPage.assertEmptyPasswordErrorMessageIsDisplayed();

    }
}