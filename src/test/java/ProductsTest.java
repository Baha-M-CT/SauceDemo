import base.BaseTest;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigUtils;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Feature("Products Feature")
public class ProductsTest extends BaseTest {

    public ProductsTest() {
    }

    LoginPage loginPage;
    ConfigUtils config = ConfigUtils.getInstance();
    String valid_username = config.getUsername();
    String valid_password = config.getPassword();


    @Test
    public void verifyAllProducts() throws Exception {
        ProductsPage productsPage = new ProductsPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.login(valid_username, valid_password);
        productsPage.assertProductsPageIsDisplayed();
        // Path to your Excel file
        String excelFilePath = "src/test/resources/testdata/products.xlsx";

        // Verify all products from Excel
        productsPage.verifyProductsFromExcel(excelFilePath);
    }


}