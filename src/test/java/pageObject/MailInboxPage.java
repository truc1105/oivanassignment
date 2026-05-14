package pageObject;

import common.CommonFunctions;
import common.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailInboxPage {
    WebDriver driver;
    CommonFunctions commonFunctions = new CommonFunctions(Hooks.driver);

    public MailInboxPage (WebDriver driver){
        this.driver = driver;
    }

    public void funcFindMyAccount (String strText) throws InterruptedException {
        if (strText.equals("randomEmail")) {
                strText = System.getProperty("randomEmail");
            }
        By objLetterOpener = By.xpath("//div[@class='col left']//dt[text()='To:']/following-sibling::dd[contains(text(),'" + strText + "')]");
        driver.findElement(objLetterOpener).click();

//        Hooks.driver.switchTo().frame(Hooks.driver.findElement(By.tagName("iframe")));
//
//        commonFunctions.funcCheckTextIsDisplay("Welcome " + strText + "!");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mail"));   // ← Dùng name="mail"
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        // Kiểm tra text bên trong iframe
        commonFunctions.funcCheckTextIsDisplay("Welcome " + strText + "!");
        commonFunctions.funcClickOnElementByText("Confirm my account");

        // Quay về trang chính (RẤT QUAN TRỌNG)
        driver.switchTo().defaultContent();
        }
}
