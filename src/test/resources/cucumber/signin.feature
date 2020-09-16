Feature: Sign In (https://github.com/login)
  Scenario: Sign in to a valid pre-existing account
    Given that I am on the login page
    When I enter a valid github username into the 'Username' field
    When I enter the valid corresponding address into the 'Password' field
    When I click the 'Sign In' button
    Then I should sign in to a valid pre-existing account
  Scenario: Fail to sign in to a valid pre-existing account
    Given that I am on the login page
    When I enter a valid github username into the 'Username' field
    When I click the 'Sign In' button
    Then I should see an error message displaying 'Incorrect username or password.'
