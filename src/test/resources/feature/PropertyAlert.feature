Feature: Property alert

  Scenario: Create property alert
    Given I open home page
    When I click on the item with text "Sign in"
    Then I verify title page is "Sign in"
    When I login successfully with the default account
    Then I verify title page is "Listings"
    When I click on the item with text "Create alert"
    Then I verify title page is "New alert"
    When I input "Studio in Long An" into the field "Alert name" with index 0
    When I input "Tay Ninh" into the field "City" with index 0
    When I input "3000000" into the field "Min price" with index 0
    When I input "5000000" into the field "Max price" with index 0
    When I input "2" into the field "Minimum bedrooms" with index 0
    When I selected "House" from the dropdown list of "Property type"
    When I click on the button "Create Alert"
    Then I verify title page is "My Alerts"
    Then I verify my new property alert is showing correctly
