package stepsDefinition;

import common.CommonFunctions;
import common.Hooks;
import io.cucumber.java.en.When;
import pageObject.PropertyAlertPage;

public class PropertyAlertSteps {
    CommonFunctions commonFunctions = new CommonFunctions(Hooks.driver);
    PropertyAlertPage propertyAlertPage = new PropertyAlertPage(Hooks.driver);

    @When("I selected {string} from the dropdown list of {string}")
    public void iSelectItemFromDropDownist(String strItem, String strLabel) {
        String message = "I input " + strItem + " into the field ";
        try {
            commonFunctions.funcSelectCustomDropdown(strItem, strLabel);
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @When("I verify my new property alert is showing correctly")
    public void iVerify(String strItem, String strLabel) {
        String message = "I verify my new property alert is showing correctly";
        try {
            propertyAlertPage.funcGetAllDataOfGridView();
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }
}
