
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @tag2
  Scenario Outline: Submit order in E-commerce App
    Given landed on E-Commerce Page
    When Login to App with username<email> and password<password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | email  									| password 						 | 
      | utkarshthote1@gmail.com | Shoprahulshetty1@#   |
