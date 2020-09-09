Feature: Checking search capabilities of Google
  Scenario: Search for a specific term
    Given I am on the Google homepage
    When I enter "usc trojans" into the search box
    And A click the search button
    Then the first result should be the offical athletics website 
    