Feature: SignIn

  Scenario: Sign in successfully
    Given I open home page
    When I click on the item with text "Sign in"
    Then I verify title page is "Sign in"
    When I login successfully with the default account