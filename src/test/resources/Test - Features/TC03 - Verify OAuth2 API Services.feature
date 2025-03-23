# Step 1:- create a step to generate Code
# Step 2:- create a step to generate Token
# Step 3A:- create a step to fetch actual responce
# Step 3B:- create a step to verify the responce
Feature: Verify OAuth2.0 type API Testing Scenarios

  Scenario Outline: Verify OAuth API services
    Given Generate Code and Token for API services
    When Extract the actual responce of course API
    Then Verify the API responce and validate the expected output