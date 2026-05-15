Feature: SignIn

  Scenario: Sign in successfully
    Given I open home page
    When I click on the item with text "Sign in"
    Then I verify title page is "Sign in"
    When I login successfully with the default account
    Then I verify my account "default" is showing correctly in the navbar

    @SmokeTest
  Scenario Outline: Login fails without username or password; Login fail with username or password incorrect
    When I open home page
    When I click on the item with text "Sign in"
    Then I verify title page is "Sign in"
    When I login with user "<username>" and password "<password>"
    Then I verify an alert appears with the message "Invalid Email or password."
    Examples:
      | username         | password      |
      |                  |               |
      | test@gmail.com   |               |
      | demo@example.com |               |
      |                  | 3iwtg[-v      |

  @SmokeTest
  Scenario: Sign in fails with incorrect format email
    Given I open home page
    When I click on the item with text "Sign in"
    Then I verify title page is "Sign in"
    When I input "Marry" into the field "Email" with index 0
    When I click on the button "Sign in"
    When I verify email validation message