Feature: Add to Cart (https://jolse.com/category/new/24/)
  Scenario: Add product to cart from new products page
    Given I am on new products page
    And there is a product listing
    When I hover over the product listing
    And I click on the cart icon
    Then the product should be added to cart
  Scenario: Remove product from cart
    Given I am on the cart page
    And there is a product in cart
    When I click on the 'Delete' button by the product
    When I click the 'OK' confirmation button
    Then the product should be removed from the cart
