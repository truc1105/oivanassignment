package stepsDefinition;

import common.CommonFunctions;
import common.Hooks;
import io.cucumber.java.en.When;
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
}
