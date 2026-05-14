package stepsDefinition;

import common.CommonFunctions;
import common.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObject.MailInboxPage;
import org.openqa.selenium.By;
import static org.testng.AssertJUnit.assertTrue;

public class MailInboxSteps {
    CommonFunctions commonFunctions = new CommonFunctions(Hooks.driver);
    MailInboxPage mailInboxPage = new MailInboxPage(Hooks.driver);

    @When("I open mail inbox page")
    public void iOpenMailInboxPage() {
        String message = "I open mail inbox page";
        try {
            Hooks.driver.get(System.getProperty(System.getProperty("ENV") + ".mailInboxUrl"));

            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @Given("I find my account {string}")
    public void iFindMyAccount(String strText) {
        String message = "I find my account";
        try {
            mailInboxPage.funcFindMyAccount(strText);
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }
}
