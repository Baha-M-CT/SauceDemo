package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    String expectedLoginButtonColor = "rgba(61, 220, 145, 1)"; // Equivalent of #3ddc91 in RGBA
    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login-button")
    public WebElement loginbutton;

    @FindBy(css = "[data-test='error']")
    public WebElement errorMessage;

    @FindBy(id = "login_credentials")
    public WebElement loginCredentials;

    @FindBy(css = "[data-test='login-password']")
    public WebElement loginPassword;

    @Step("Enter username: {username}")
    public void enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginbutton)).click();
    }

    @Step("Login with username: {username} and password")
    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Assert invalid username or password error message is displayed")
    public void assertInvalidUsernameOrPasswordErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String actualText = errorMessage.getText().trim();
        String expectedText = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(expectedText, actualText, "Error message text does not match expected text");
    }

    @Step("Assert empty username or both error message is displayed")
    public void assertEmptyUsernameOrBothErrorMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String actualText = errorMessage.getText().trim();
        String expectedText = "Epic sadface: Username is required";
        Assert.assertEquals(expectedText, actualText, "Error message text does not match expected text");
    }

    @Step("Assert empty password error message is displayed")
    public void assertEmptyPasswordErrorMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String actualText = errorMessage.getText().trim();
        String expectedText = "Epic sadface: Password is required";
        Assert.assertEquals(expectedText, actualText, "Error message text does not match expected text");
    }

    @Step("Assert login credentials are displayed correctly")
    public void assertLoginCredentials() {
        wait.until(ExpectedConditions.visibilityOf(loginCredentials));
        String expectedText = "Accepted usernames are:\nstandard_user\nlocked_out_user\nproblem_user\nperformance_glitch_user\nerror_user\nvisual_user";
        String actualText = loginCredentials.getText().replace("\r", "").trim();
        Assert.assertEquals(actualText, expectedText, "Login credentials text does not match expected text");
    }

    @Step("Assert password is displayed correctly")
    public void assertPasswordDetails() {
        wait.until(ExpectedConditions.visibilityOf(loginPassword));
        String expectedText = "Password for all users:\nsecret_sauce";
        String actualText = loginPassword.getText().replace("\r", "").trim();
        Assert.assertEquals(actualText, expectedText, "Login password text does not match expected text");
    }

    @Step("Assert login button color is green #3ddc91")
    public void assertLoginButtonColor() {
        wait.until(ExpectedConditions.visibilityOf(loginbutton));
        String actualColor = loginbutton.getCssValue("background-color");
        if (actualColor.startsWith("rgb(")) {
            // Convert rgb(r, g, b) to rgba(r, g, b, 1)
            actualColor = actualColor.replace("rgb(", "rgba(").replace(")", ", 1)");
        }
        Assert.assertEquals(actualColor, expectedLoginButtonColor, "Login button background color does not match expected color");
    }

    @Step("Verify Login page UI elements")
    public void asserLoginPageElements() {
        wait.until(ExpectedConditions.visibilityOf(loginbutton));
        Assert.assertTrue(loginbutton.isDisplayed(), "Login button is not visible");
        Assert.assertTrue(usernameField.isDisplayed(), "Username field is not visible");
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not visible");
        assertLoginButtonColor();

    }
}