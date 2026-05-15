Feature: Property alert

  Scenario: Create property alert
    Given I open home page
    When I click on the item with text "Sign in"
    Then I verify title page is "Sign in"
    When I login successfully with the default account
    Then I verify an alert appears with the message "Signed in successfully."
    Then I verify title page is "Listings"
    When I click on the item with text "Create alert"
    Then I verify title page is "New alert"
    When I input "randomAlertName" into the field "Alert name" with index 0
    When I input "New York" into the field "City" with index 0
    When I input "3500000" into the field "Min price" with index 0
    When I input "5900500" into the field "Max price" with index 0
    When I input "2" into the field "Minimum bedrooms" with index 0
    When I selected "House" from the dropdown list of "Property type"
    When I click on the button "Create Alert"
    Then I verify title page is "My Alerts"
    Then I verify my new property alert is showing correctly

  Scenario: Delete property alert
    Given I open home page
    When I click on the item with text "Sign in"
    Then I verify title page is "Sign in"
    When I login successfully with the default account
    Then I verify an alert appears with the message "Signed in successfully."
    Then I verify title page is "Listings"
    When I click on the item with text "My Alerts"
    Then I verify title page is "My Alerts"
    When I select the data to delete
    When I delete a property alert
    Then I verify an alert appears with the message "Alert removed"