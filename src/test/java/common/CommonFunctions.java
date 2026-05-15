package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Properties;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import com.github.javafaker.Faker;

public class CommonFunctions {
    WebDriver driver;
    Faker faker = new Faker();
    int intTIMEOUT = Integer.parseInt(System.getProperty("objectTimeout").trim());
    By btnSignUp = By.xpath("//div[@class='p-tabview-nav-container']//button[@aria-label='Next Page']");
    By alertMessages= By.xpath("//*[contains(@class,'alert alert')]");

    public CommonFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public static void funcReadPropertiesFile(String fileProperties) {

        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(fileProperties)) {

            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));

            for (String key : prop.stringPropertyNames()) {

                String val = prop.getProperty(key);

                System.setProperty(key, val);
            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }

//    public void funcClickOnElementByText(String strText) throws InterruptedException {
//        funcClickOnElementByText(strText, 0);
//    }
//
    public void funcClickOnElementByText(String strText) throws InterruptedException {
        By ele = By.xpath("//*[text() = '" + strText + "']");
        try
        {
            CommonFunctions.funcClickElement(driver, driver.findElement(ele), intTIMEOUT);
        }
        catch (Exception e)
        {
            try {
                System.out.println("Webpage on loading...");
                //CommonFunctions.waitForPageLoadCompleted(driver);

                CommonFunctions.funcClickElement(driver, driver.findElement(ele), intTIMEOUT);
            }
            catch (Exception ex)
            {
                System.out.println("Fail!");
            }
        }
    }

    public static void funcClickElement(WebDriver driver, WebElement ele, int timeoutInSecond) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSecond));
        driverWait.until(ExpectedConditions.elementToBeClickable(ele)).click();
    }

    public void inputFieldWithLabel(String label, String text, int index) {
        if (text.equals("randomEmail")) {
            if (System.getProperty("randomEmail") == null) {
                text = faker.name().nameWithMiddle().replaceAll(" ", "") + "@oivan.com";
                System.setProperty("randomEmail", text);
            } else {
                text = System.getProperty("randomEmail");
            }
        }

        if (text.equals("randomAlertName")){
            text = "House in " + faker.country().name();
        }

        By lab = By.xpath(".//*[contains(text(),'" + label + "')]/following-sibling::input");
        WebElement ele = driver.findElements(lab).get(index);
        ele.clear();
        ele.sendKeys(text);

        System.setProperty(label + Thread.currentThread().getName(), text);
    }

    public void funcClickOnElementByValue(String strText, int index) throws InterruptedException {
        By ele = By.xpath("//*[@value = '" + strText + "']");
        try
        {
            CommonFunctions.funcClickElement(driver, driver.findElements(ele).get(index), intTIMEOUT);
        }
        catch (Exception e)
        {
            try {
                System.out.println("Webpage on loading...");
                //CommonFunctions.waitForPageLoadCompleted(driver);

                CommonFunctions.funcClickElement(driver, driver.findElements(ele).get(index), intTIMEOUT);
            }
            catch (Exception ex)
            {
                System.out.println("Fail!");
            }
        }
    }

    public static String funcGetTextFromElement(WebDriver driver, By by, int timeoutInSecond) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSecond));
        WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getText();
    }

    public void checkAlertMessageIsDisplay(String alertMessage) throws IOException {
        String innerText = CommonFunctions.funcGetTextFromElement(driver, alertMessages, 120);
        assertEquals(alertMessage, innerText);
    }

    public void funcVerifyThePageTitle(WebDriver driver, String strTitle) {
        try {
            Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            String title = driver.getTitle();
            assertEquals(strTitle, title);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void funcCheckTextIsDisplay(String strText) {
        try {
            Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            By by = By.xpath("//*[text()='" + strText + "']");
            //CommonFunctions.funcWaitUntilElementVisibility(driver, by, intTIMEOUT);
            assertTrue(driver.findElement(by).isDisplayed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public static void funcWaitUntilElementVisibility(WebDriver driver, By locator, int timeoutInSecond) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSecond));
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Unable to located element: " + e.getMessage());
        }
    }

    public void funcClickCheckBox(By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void funcSelectCustomDropdown(String strItem, String strLabel) {
        By dropdownLocator = By.xpath("//*[text()='" + strLabel + "']/following-sibling::select");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(intTIMEOUT));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        dropdown.click();
        By itemLocator = By.xpath("//*[text()='" + strItem + "']");

        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(itemLocator));

        item.click();
    }
}
