Feature: Test Case 01 - Verify all API services of E-Commerce application

  Scenario Outline: Verify all API services of E-commerce appliction like Login to Access server, Get token, Add product, Order the product and Delete the product
    Given Login to access server and create access token for <User ID> and <Password>
    When Add the product in application with <Product Name>,<Product Price>,<Product Description> and <Imgdetails>
    Then Verify the product details with given details
    And Verify the order details with <Product Name>,<Product Price> and <Product Description>
    And Delete the added product from E-commerce service

    Examples: 
      | User ID              | Password  | Product Name | Product Price | Product Description | Imgdetails |
      | apiTesting@gmail.com | ABC123abc | My shoes     |          9944 | Puma Shoes          | pumaShoes  |
      | apiTesting@gmail.com | ABC123abc | S24 Ultra    |         99999 | Better then I Phone | S24Ultra   |