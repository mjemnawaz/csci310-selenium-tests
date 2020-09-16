Feature: Hello (https://www.allrecipes.com/recipes/, this feature file is not from hw3 as twitter blocks interaction through automation)
  Scenario: New search for recipes
    Given I am on the home page of allrecipes.com
    When I navigate to the recipes page
    And I enter 'spaghetti' into the search bar
    And I click Enter
    Then I should see a card for 'Spaghetti' recipes
  Scenario: Edit search for recipes
    Given I am on the search results page
    When I click the Edit button
    And I enter 'cheese' into the 'Include Ingredients' bar
    And I click on Enter
    Then I should see 'including "cheese"' at the top of the search results  