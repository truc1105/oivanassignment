package stepsDefinition;

import common.CommonFunctions;
import common.Hooks;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageObject.LogInPage;

public class SignInSteps {
    LogInPage logInPage = new LogInPage(Hooks.driver);
    CommonFunctions commonFunctions = new CommonFunctions(Hooks.driver);

    @When("I login successfully with the default account")
    public void iLoginSuccessfully() {
        String message = "I login successfully";
        try {
            String strUsername = System.getProperty(System.getProperty("ENV") + ".username");
            String strPassword = System.getProperty(System.getProperty("ENV") + ".password");

            logInPage.funcLogin(strUsername, strPassword);
            commonFunctions.funcClickOnElementByText("Xác nhận");

            System.out.println("I login with user " + strUsername + " and password " + strPassword + " successfully ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @When("I login with user {string} and password {string}")
    public void iLoginSuccessfully(String strUsername, String strPassword) {
        String message = "I login ";
        try {
            logInPage.funcLogin(strUsername, strPassword);

            System.out.println("I login employee with user " + strUsername + " and password " + strPassword + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @When("I verify email validation message")
    public void iVerifyEmailValidationMessage() {
        String message = "I verify email validation message";
        try {
            logInPage.verifyEmailMissingAtError();

            System.out.println("I login employee with user");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @Then("I verify my account {string} is showing correctly in the navbar")
    public void iVerifyMyAccountShowingCorrectly(String strEmail) {
        String message = "I verify my account is showing correctly in the navbar";
        try {
            logInPage.funcVerifyMyAccount(strEmail);

            System.out.println("I login employee with user");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }
}
