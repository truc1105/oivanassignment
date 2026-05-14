package pageObject;

import common.CommonFunctions;
import common.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
    WebDriver driver;
    int intTIMEOUT = Integer.parseInt(System.getProperty("objectTimeout").trim());
    CommonFunctions commonFunctions = new CommonFunctions(Hooks.driver);

    By chkRememberMe = By.id("user_remember_me");
    By txtEmail = By.id("user_email");
    By txtPassword = By.id("user_password");
    By btnSignIn = By.name("commit");

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
}
