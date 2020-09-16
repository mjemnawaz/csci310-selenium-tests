Feature: Hello (https://www.calculatorsoup.com/calculators/math/basic.php, this is not from hw3 as gmail blocks logging in through automation)
  Scenario: Add with calculator
    Given I am on the calculator page
    When I click a number 1
    And I click the addition symbol
    And I click a number 1
    Then I should see the number 2
  Scenario: Subtract with calculator
    Given I am on the calculator page
    When I click a number 1
    And I click the subtraction symbol
    And I click a number 1
    Then I should see the number 0  