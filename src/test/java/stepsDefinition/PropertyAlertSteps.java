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
            System.setProperty(strLabel + Thread.currentThread().getName(), strItem);

            System.out.println(message + " ==> PASSED");

        } catch (Exception e) {
            System.out.println(message + " ==> FAILED");
            e.printStackTrace();
        }
    }

    @When("I verify my new property alert is showing correctly")
    public void iVerifyMyNewPropertyAlertShowingCorrectly() {
        String message = "I verify my new property alert is showing correctly";
        try {
            propertyAlertPage.funcVerifyMyNewPropertyAlert();
            System.out.println(message + " ==> PASSED");

        } catch (Exception e) {
            System.out.println(message + " ==> FAILED");
            e.printStackTrace();
        }
    }

    @When("I select a property alert to edit or delete")
    public void iSelectDataToDelete() {
        String message = "I select a property alert to edit or delete";
        try {
            propertyAlertPage.funcSelectData();
            System.out.println(message + " ==> PASSED");

        } catch (Exception e) {
            System.out.println(message + " ==> FAILED");
            e.printStackTrace();
        }
    }

    @When("I choose to {string} a property alert")
    public void iDeletePropertyAlert(String strAction) {
        String message = "I delete a property alert";
        try {
            propertyAlertPage.funcEditDeletePropertyAlert(strAction);
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }

    @When("I confirm the delete alert")
    public void iConfirmTheAlert() {
        String message = "I confirm the delete alert";
        try {
            propertyAlertPage.funcConfirmDeleteAlert();
            System.out.println(message + " ==> PASSED");

        } catch (AssertionError | Exception e) {
            System.out.println(message + " ==> FAILED");
        }
    }
}
