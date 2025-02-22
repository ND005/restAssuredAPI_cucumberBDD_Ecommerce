Feature: Test Case 01 - Verify all API services of E-Commerce application

  Scenario Outline: Verify all API services of E-commerce appliction like Login to Access server, Get token, Add product, Order the product and Delete the product
    Given Login to access server and create access token for <User ID> and <Password>
    When Add the product in application and Order that product using API services
    Then Verify the Order an Product details with given details
    And Delete the added product from E-commerce service

    Examples: 
      | User ID              | Password  |
      | apiTesting@gmail.com | ABC123abc |