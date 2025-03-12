package test.java.utils;

import base.BaseTest;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.FooterPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigUtils;
import utils.ExcelReader;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Feature("Auth Feature")
public class ProductsTest extends BaseTest {

    public ProductsTest() {
    }

    LoginPage loginPage;
    ProductsPage productsPage;
    FooterPage footerPage;
    CartPage cartPage;
    ConfigUtils config = ConfigUtils.getInstance();
    String valid_username = config.getUsername();
    String valid_password = config.getPassword();
    static boolean isSetUpDone = false;



    @Test
    public void verifyAllProducts() throws Exception {
        ProductsPage productsPage = new ProductsPage(driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        loginPage.login(valid_username, valid_password);
        productsPage.assertProductsPageIsDisplayed();
        // Path to your Excel file
        String excelFilePath = "src/test/resources/testdata/products.xlsx";

        // Verify all products from Excel
        productsPage.verifyProductsFromExcel(excelFilePath);
    }


}