package pageObject;

import common.CommonFunctions;
import common.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class LogInPage {
    WebDriver driver;
    int intTIMEOUT = Integer.parseInt(System.getProperty("objectTimeout").trim());
    CommonFunctions commonFunctions = new CommonFunctions(Hooks.driver);

    By chkRememberMe = By.id("user_remember_me");
    By txtEmail = By.id("user_email");
    By txtPassword = By.id("user_password");
    By btnSignIn = By.name("commit");
    By textMyAccountEmail = By.xpath("//nav//*[@data-test = 'current-user-email']");

    public LogInPage(WebDriver driver){
        this.driver = driver;
    }

    public void funcLogin(String strUsername, String strPassword) {
        // Clear cache
        driver.findElement(txtEmail).clear();
        driver.findElement(txtPassword).clear();

        // Login
        driver.findElement(txtEmail).sendKeys(strUsername);
        driver.findElement(txtPassword).sendKeys(strPassword);

        commonFunctions.funcClickCheckBox(chkRememberMe);
        CommonFunctions.funcClickElement(driver, driver.findElement(btnSignIn), intTIMEOUT);
    }

    public void funcVerifyEmailMissingAtError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        // Find input email
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='email'], input[placeholder*='Email'], input[name*='email']")
        ));

        // get validation message directly from browser
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailInput);

        assertTrue("Email is missing the @",
                validationMessage.contains("Please include an '@'") || validationMessage.contains("missing an '@'"));
    }

    public void funcVerifyMyAccount(String strEmailExpected) {
        if (strEmailExpected.equals("default"))
            strEmailExpected = System.getProperty(System.getProperty("ENV") + ".username");

        String strEmailActual = driver.findElement(textMyAccountEmail).getText();

        Assert.assertEquals(strEmailActual, strEmailExpected);
    }
}
