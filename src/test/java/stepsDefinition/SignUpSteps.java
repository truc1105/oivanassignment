package stepsDefinition;

import common.CommonFunctions;
import common.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class SignUpSteps {
    CommonFunctions commonFunctions = new CommonFunctions(Hooks.driver);

    @Given("I open home page")
    public void iOpenHomePage() {
        String message = "I open home page";
        try {
            Hooks.driver.get(System.getProperty(System.getProperty("ENV") + ".baseUrl"));
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @Then("I verify title page is {string}")
    public void iVerifyTitlePageIs(String strTitlePage) {
        String message = "I verify title page is " + strTitlePage;
        try {
            commonFunctions.funcVerifyThePageTitle(Hooks.driver, strTitlePage);
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @When("I click on the item with text {string}")
    public void iClickOnTheItemWithText(String text) {
        String message = "I click on the item with text " + text;
        try {
            commonFunctions.funcClickOnElementByText(text);
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {

            System.out.println(message + " ==> FAILED");

        }
    }

    @When("I input {string} into the field {string} with index {int}")
    public void iInputValueIntoField(String text, String label, int index) {
        String message = "I input " + text + " into the field " + label;
        try {
            commonFunctions.inputFieldWithLabel(label, text, index);
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @When("I click on the button {string}")
    public void iClickOnTheButton(String text) {
        String message = "I click on the button " + text;
        try {
            commonFunctions.funcClickOnElementByValue(text, 0);
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {

            System.out.println(message + " ==> FAILED");

        }
    }

    @Then("I verify an alert appears with the message {string}")
    public void iVerifyAnAlertAppearsWithTheMessage(String alertMessage) {
        String message = "I verify an alert appears with the message " + alertMessage;
        try {
            commonFunctions.checkAlertMessageIsDisplay(alertMessage);
            System.out.println(message + " ==> PASSED" );

        } catch (AssertionError | Exception e) {

            System.out.println(message + " ==> FAILED");

        }
    }
}
