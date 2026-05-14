Feature: SignUp

  Scenario: Sign up successfully
    Given I open home page
    When I click on the item with text "Sign up"
    Then I verify title page is "Sign up"
    When I input "Marry" into the field "Name" with index 0
    When I input "randomEmail" into the field "Email" with index 0
    When I input "12345678" into the field "Password" with index 0
    When I input "12345678" into the field "Password confirmation" with index 0
    When I click on the button "Sign up"
    Then I verify an alert appears with the message "You have to confirm your email address before continuing."
    Then I verify title page is "Sign in"

    When I open mail inbox page
    Then I verify title page is "LetterOpenerWeb"
    When I click on the item with text "Refresh"
    When I find my account "randomEmail"
    Then I verify an alert appears with the message "Your email address has been successfully confirmed."
    Then I verify title page is "Sign in"

    When I input "randomEmail" into the field "Email" with index 0
    When I input "12345678" into the field "Password" with index 0
    When I click on the button "Sign in"
    Then I verify title page is "Listings"
    Then I verify an alert appears with the message "Signed in successfully."


