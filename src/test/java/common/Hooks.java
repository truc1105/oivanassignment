package common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        CommonFunctions.funcReadPropertiesFile("src/main/resources/application.properties");
        String operatingSystem = System.getProperty("os.name").trim().toLowerCase();
        System.out.println("Operating System: " + operatingSystem);

        String ENV = System.getProperty("ENV").trim();
        System.out.println("ENV: " + ENV);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    @After
    public void tearDown() {

        if(driver != null) {
            driver.quit();
        }
    }
}